<#assign modelClass="${config.modelName}">
<#assign modelName="${modelClass?uncap_first}">
<#assign serviceClass="${modelClass}Service">
<#assign serviceName="${serviceClass?uncap_first}">
package ${config.controllerPackage};

<#list config.controllerImportPackages as p>
import ${p};
</#list>


@RestController
@RequestMapping("${modelName}")
public class ${modelClass}Controller{

	@Autowired
	private ${serviceClass} ${serviceName};
	
	<#if config.listApi>
	@GetMapping(value = "list")
	public ResponseResult list(${modelClass} ${modelName}){
	   List<${modelClass}> list = ${serviceName}.list(${modelName});
	   return RestResultGenerator.genResult("成功!")
				                 .putData("${modelName}s", list);
	} 
	</#if>
	
	<#if config.pageApi>
	@GetMapping(value = "page")
	public ResponseResult page(PageInfo pageInfo,${modelClass} ${modelName}){
	   PageResult<${modelClass}> page = ${serviceName}.page(${modelName}, pageInfo);
	   return RestResultGenerator.genResult("成功!")
				                 .putPageData("${modelName}s", page);
	} 
	</#if>
	
	<#if config.detailApi>
	@GetMapping(value = "detail")
	public ResponseResult detail(${modelClass} ${modelName}){
	   ${modelName} = ${serviceName}.detail(${modelName});
	   return RestResultGenerator.genResult("成功!")
				                 .putData("${modelName}", ${modelName});
	} 
	</#if>
	
	<#if config.insertApi>
	@PostMapping(value = "insert")
	public ResponseResult insert(${modelClass} ${modelName}){
	   ${serviceName}.insert(${modelName});
	   return RestResultGenerator.genResult("成功!");
	} 
	</#if>
	
	<#if config.deleteApi>
	@PostMapping(value = "delete")
	public ResponseResult delete(${modelClass} ${modelName}){
	   ${serviceName}.delete(${modelName});
	   return RestResultGenerator.genResult("成功!");
	} 
	</#if>
	
	<#if config.updateApi>
	@PostMapping(value = "update")
	public ResponseResult update(${modelClass} ${modelName}){
	   ${serviceName}.update(${modelName});
	   return RestResultGenerator.genResult("成功!");
	} 
	</#if>
	
}