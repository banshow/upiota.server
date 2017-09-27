package ${config.mapperPackage};

<#list config.mapperImportPackages as p>
import ${p};
</#list>


@Mapper
public interface ${config.modelName}Mapper extends MyMapper<${config.modelName}> {
}