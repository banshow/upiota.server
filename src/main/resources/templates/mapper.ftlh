package ${config.mapperPackage};

<#list config.mapperImportPackages as p>
import ${p};
</#list>


public interface ${config.modelName}Repository extends MyMapper<${config.modelName}> {
}