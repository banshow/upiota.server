<#assign modelClass="${config.modelName}">
<#assign modelName="${modelClass?uncap_first}">
<#assign mapperClass="${modelClass}Mapper">
<#assign mapperName="${mapperClass?uncap_first}">
package ${config.servicePackage};

<#list config.serviceImportPackages as p>
import ${p};
</#list>


@Service
public class ${modelClass}Service{

	@Autowired
	private ${mapperClass} ${mapperName};
	
	<#if config.listApi>
	public List<${modelClass}> list(${modelClass} ${modelName}){
	    return ${mapperName}.select(${modelName});
	} 
	</#if>
	
	<#if config.pageApi>
	public PageResult<${modelClass}> page(${modelClass} ${modelName},PageInfo pageInfo){
		return PageUtil.doSelectPage(pageInfo, ()-> ${mapperName}.selectAll());
	}
	</#if>
	
	<#if config.detailApi>
	public ${modelClass} detail(${modelClass} ${modelName}){
		return ${mapperName}.selectOne(${modelName});
	}
	</#if>
	
	<#if config.insertApi>
	public void insert(${modelClass} ${modelName}){
	    ${modelName}.set${table.pkPropertyName?cap_first}(UID.getInstanse().getUID());
		${mapperName}.insertSelective(${modelName});
	}
	</#if>
	
	<#if config.deleteApi>
	public void delete(${modelClass} ${modelName}){
		${mapperName}.delete(${modelName});
	}
	</#if>
	
	<#if config.updateApi>
	public void update(${modelClass} ${modelName}){
		${mapperName}.updateByPrimaryKeySelective(${modelName});
	}
	</#if>
	
}