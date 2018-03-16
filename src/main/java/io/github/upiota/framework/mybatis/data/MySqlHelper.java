package io.github.upiota.framework.mybatis.data;

import static org.springframework.data.repository.query.parser.Part.IgnoreCaseType.ALWAYS;
import static org.springframework.data.repository.query.parser.Part.IgnoreCaseType.WHEN_POSSIBLE;
import static org.springframework.data.repository.query.parser.Part.Type.NOT_CONTAINING;
import static org.springframework.data.repository.query.parser.Part.Type.NOT_IN;
import static org.springframework.data.repository.query.parser.Part.Type.NOT_LIKE;

import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.query.Parameters;
import org.springframework.data.repository.query.parser.Part;
import org.springframework.data.repository.query.parser.Part.IgnoreCaseType;
import org.springframework.data.repository.query.parser.Part.Type;

import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.entity.EntityTable;
import tk.mybatis.mapper.mapperhelper.EntityHelper;
import tk.mybatis.mapper.mapperhelper.SqlHelper;

public class MySqlHelper extends SqlHelper {

	/**
	 * 获取所有查询列带别名，如id as id,name as name,code as code...
	 *
	 * @param entityClass
	 * @return
	 */
	public static String getAllColumnsWithAlias(Class<?> entityClass) {
		Set<EntityColumn> columnList = EntityHelper.getColumns(entityClass);
        StringBuilder sql = new StringBuilder();
        for (EntityColumn entityColumn : columnList) {
            sql.append(entityColumn.getColumn()).append(" as ").append(entityColumn.getProperty()).append(",");
        }
        return sql.substring(0, sql.length() - 1);
	}
	
	
	/**
	 * 获取查询列带别名，如id as id,name as name,code as code...
	 *
	 * @param entityClass
	 * @return
	 */
	public static String getSelectColumnsWithAlias(Class<?> entityClass,PartTree tree) {
		
		if(!tree.hasObjective()){
			return getAllColumnsWithAlias(entityClass);
		}
		
		EntityTable entityTable = EntityHelper.getEntityTable(entityClass);
		Map<String, EntityColumn> propertyMap = entityTable.getPropertyMap();
		
		StringBuilder sql = new StringBuilder();
		for (Iterator<Part> it = tree.objectiveIterator(); it.hasNext();) {
			Part part = it.next();
			EntityColumn entityColumn = propertyMap.get(part.getProperty().getSegment());
			sql.append(entityColumn.getColumn()).append(" as ").append(entityColumn.getProperty()).append(",");
		}
		return sql.substring(0, sql.length() - 1);
	}
	
	 public static String selectColumns(Class<?> entityClass,PartTree tree) {
	        StringBuilder sql = new StringBuilder();
	        sql.append("SELECT ");
	        sql.append(getSelectColumnsWithAlias(entityClass,tree));
	        sql.append(" ");
	        return sql.toString();
	    }
	
	
	public static String getWhereClause(Class<?> entityClass,PartTree tree,Parameters<?,?> parameters){
		
		EntityTable entityTable = EntityHelper.getEntityTable(entityClass);
		Map<String, EntityColumn> propertyMap = entityTable.getPropertyMap();
		
		StringBuilder builder = new StringBuilder();
		builder.append("<trim prefix=\" where \" prefixOverrides=\"and |or \">");
		int c = 0;
		for (Iterator<PartTree.OrPart> iterator = tree.iterator(); iterator.hasNext();) {
			PartTree.OrPart orPart = iterator.next();
			builder.append(" or (");
			builder.append("<trim prefix=\"\" prefixOverrides=\"and |or \">");
			for (Iterator<Part> it = orPart.iterator(); it.hasNext();) {
				Part part = it.next();
				String property = part.getProperty().getSegment();
				EntityColumn entityColumn = propertyMap.get(property);
				
				String columnName = entityColumn.getColumn();
                builder.append(" and ");
				
				IgnoreCaseType ignoreCaseType = part.shouldIgnoreCase();
				if (ignoreCaseType == ALWAYS || ignoreCaseType == WHEN_POSSIBLE) {
					builder.append("upper(").append(columnName).append(")");
				} else {
					builder.append(columnName);
				}
				
				builder.append(buildConditionOperate(part.getType()));
				String[] properties = new String[part.getType().getNumberOfArguments()];
				for (int i = 0; i < properties.length; i++) {
					properties[i] = resolveParameterName(c++,parameters);
				}
				builder.append(buildConditionCaluse(part.getType(), ignoreCaseType, properties));
			}
			
            builder.append("</trim>");
			builder.append(" )");
		}
		builder.append("</trim>");
		return builder.toString();
	}
	
	private static String resolveParameterName(int position,Parameters<?,?> parameters) {
		Optional<String> paramName = Optional.empty();
		if (parameters.hasParameterAt(position)) {
			paramName = parameters.getParameter(position).getName();
		}
		return paramName.orElse("p" + position);
	}
	private static String buildConditionCaluse(Type type, IgnoreCaseType ignoreCaseType, String[] properties) {
        StringBuilder builder = new StringBuilder();
        switch (type) {
            case CONTAINING:
            case NOT_CONTAINING:
                if (ignoreCaseType == ALWAYS || ignoreCaseType == WHEN_POSSIBLE) {
                    builder.append("concat('%',upper(#{" + properties[0] + "}),'%')");
                } else {
                    builder.append("concat('%',#{" + properties[0] + "},'%')");
                }
                break;
            case STARTING_WITH:
                if (ignoreCaseType == ALWAYS || ignoreCaseType == WHEN_POSSIBLE) {
                    builder.append("concat(upper(#{" + properties[0] + "}),'%')");
                } else {
                    builder.append("concat(#{" + properties[0] + "},'%')");
                }
                break;
            case ENDING_WITH:
                if (ignoreCaseType == ALWAYS || ignoreCaseType == WHEN_POSSIBLE) {
                    builder.append("concat('%',upper(#{" + properties[0] + "}))");
                } else {
                    builder.append("concat('%',#{" + properties[0] + "})");
                }
                break;
            case IN:
            case NOT_IN:
                builder.append("<foreach item=\"item\" index=\"index\" collection=\"" + properties[0] + "\" open=\"(\" separator=\",\" close=\")\">#{item}</foreach>");
                break;
            case IS_NOT_NULL:
                builder.append(" is not null");
                break;
            case IS_NULL:
                builder.append(" is null");
                break;

            case TRUE:
                builder.append("=true");
                break;
            case FALSE:
                builder.append("=false");
                break;

            default:
                if (ignoreCaseType == ALWAYS || ignoreCaseType == WHEN_POSSIBLE) {
                    builder.append("upper(#{" + properties[0] + "})");
                } else {
                    builder.append("#{" + properties[0] + "}");
                }
                break;
        }

        return builder.toString();
    }

	private static String buildConditionOperate(Type type) {
        StringBuilder builder = new StringBuilder();
        switch (type) {
            case SIMPLE_PROPERTY:
                builder.append("=");
                break;
            case NEGATING_SIMPLE_PROPERTY:
                builder.append("<![CDATA[<>]]>");
                break;
            case LESS_THAN:
            case BEFORE:
                builder.append("<![CDATA[<]]>");
                break;
            case LESS_THAN_EQUAL:
                builder.append("<![CDATA[<=]]>");
                break;
            case GREATER_THAN:
            case AFTER:
                builder.append("<![CDATA[>]]>");
                break;
            case GREATER_THAN_EQUAL:
                builder.append("<![CDATA[>=]]>");
                break;

            case LIKE:
            case NOT_LIKE:
            case STARTING_WITH:
            case ENDING_WITH:
                if (type == NOT_LIKE) {
                    builder.append(" not");
                }
                builder.append(" like ");
                break;
            case CONTAINING:
            case NOT_CONTAINING:
                if (type == NOT_CONTAINING) {
                    builder.append(" not");
                }
                builder.append(" like ");
                break;
            case IN:
            case NOT_IN:
                if (type == NOT_IN) {
                    builder.append(" not");
                }
                builder.append(" in ");
                break;
		default:
			break;

        }
        return builder.toString();
    }

	
}
