package io.github.upiota.server.mycoder.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import io.github.upiota.server.mycoder.model.CoderProperties;
import io.github.upiota.server.mycoder.model.Config;
import io.github.upiota.server.mycoder.model.Table;

@Component
public class TemplateUtil {
	
    private static CoderProperties coderProperties;
	
	@Autowired
	public void setCoderProperties(CoderProperties coderProperties) {  
		TemplateUtil.coderProperties = coderProperties;  
	} 
	
	private static void replenishConfig(Config config){
		
		String basePackage = coderProperties.getBasePackage();
		
		
		if(StringUtils.isBlank(config.getModelPackage())){
			config.setModelPackage(basePackage+".entity");
		}
		
		if(StringUtils.isBlank(config.getMapperPackage())){
			config.setMapperPackage(basePackage+".repository");
		}
		
		if(StringUtils.isBlank(config.getServicePackage())){
			config.setServicePackage(basePackage+".service");
		}
		
		if(StringUtils.isBlank(config.getControllerPackage())){
			config.setControllerPackage(basePackage+".controller");
		}
		if(StringUtils.isBlank(config.getSqlPackage())){
			config.setSqlPackage("product");
		}
		
		config.setUtilPackage(coderProperties.getUtilPackage());
	}
	
	public static void gen(Config config) throws Exception {
		replenishConfig(config);
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_25);
		cfg.setClassForTemplateLoading(TemplateUtil.class, "/templates");
		cfg.setDefaultEncoding("UTF-8");
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		cfg.setLogTemplateExceptions(false);

		String tableName = config.getTableName();

		Map<String, Object> root = new HashMap<String, Object>();
		Table table = DBUtil.getTableInfo(tableName, coderProperties.getDriver(), coderProperties.getUrl(), coderProperties.getUser(),
				coderProperties.getPassword());

		if (table == null) {
			return;
		}

		root.put("table", table);
		root.put("config", config);

		genModel(config, cfg, root);
		genMapper(config, cfg, root);
		genSql(config, cfg, root);
		genService(config, cfg, root);
		genController(config, cfg, root);
		
	}

	private static void genController(Config config, Configuration cfg, Map<String, Object> root) throws Exception{
		
		if(!config.isGenService()||!config.isGenController()){
			return;
		}
		
		String modelName = config.getModelName();
		String javaTargetProject = coderProperties.getJavaTargetProject();
		String controllerPackage = config.getControllerPackage();

		String controllerFileName = modelName + "Controller.java";
		String basePath = javaTargetProject + "/" + controllerPackage.replaceAll("\\.", "/");
		File pathFile = new File(basePath);
		if (!pathFile.exists()) {
			pathFile.mkdirs();
		}

		String outfullFileName = basePath + "/" + controllerFileName;

		Template temp = cfg.getTemplate("controller.ftlh","UTF-8");
		Writer out = new OutputStreamWriter(new FileOutputStream(outfullFileName),"UTF-8");
		temp.process(root, out);

		out.close();
		
	}
	
	private static void genService(Config config, Configuration cfg, Map<String, Object> root) throws Exception{
		
		if(!config.isGenService()){
			return;
		}
		
		String modelName = config.getModelName();
		String javaTargetProject = coderProperties.getJavaTargetProject();
		String servicePackage = config.getServicePackage();
		
		String serviceFileName = modelName + "Service.java";
		String basePath = javaTargetProject + "/" + servicePackage.replaceAll("\\.", "/");
		File pathFile = new File(basePath);
		if (!pathFile.exists()) {
			pathFile.mkdirs();
		}
		
		String outfullFileName = basePath + "/" + serviceFileName;
		
		Template temp = cfg.getTemplate("service.ftlh","UTF-8");
		Writer out = new OutputStreamWriter(new FileOutputStream(outfullFileName),"UTF-8");
		temp.process(root, out);
		
		out.close();
		
	}

	private static void genModel(Config config, Configuration cfg, Map<String, Object> root) throws Exception {
		String modelName = config.getModelName();
		String javaTargetProject = coderProperties.getJavaTargetProject();
		String modelPackage = config.getModelPackage();

		String modelFileName = modelName + ".java";
		String basePath = javaTargetProject + "/" + modelPackage.replaceAll("\\.", "/");
		File pathFile = new File(basePath);
		if (!pathFile.exists()) {
			pathFile.mkdirs();
		}

		String outfullFileName = basePath + "/" + modelFileName;

		Template temp = cfg.getTemplate("model.ftlh","UTF-8");

		Writer out = new OutputStreamWriter(new FileOutputStream(outfullFileName),"UTF-8");
		temp.process(root, out);

		out.close();
	}

	private static void genMapper(Config config, Configuration cfg, Map<String, Object> root) throws Exception {
		
		String modelName = config.getModelName();
		String javaTargetProject = coderProperties.getJavaTargetProject();
		String mapperPackage = config.getMapperPackage();

		String mapperFileName = modelName + "Mapper.java";
		String basePath = javaTargetProject + "/" + mapperPackage.replaceAll("\\.", "/");
		File pathFile = new File(basePath);
		if (!pathFile.exists()) {
			pathFile.mkdirs();
		}

		String outfullFileName = basePath + "/" + mapperFileName;

		Template temp = cfg.getTemplate("mapper.ftlh","UTF-8");

		Writer out = new OutputStreamWriter(new FileOutputStream(outfullFileName),"UTF-8");
		temp.process(root, out);

		out.close();
	}

	private static void genSql(Config config, Configuration cfg, Map<String, Object> root) throws Exception {
		
		String modelName = config.getModelName();
		String resourcesTargetProject = coderProperties.getResourcesTargetProject();
		String sqlPackage = config.getSqlPackage();
		
		String sqlFileName = modelName + "Mapper.xml";
		String basePath = resourcesTargetProject + "/" + sqlPackage.replaceAll("\\.", "/");
		File pathFile = new File(basePath);
		if (!pathFile.exists()) {
			pathFile.mkdirs();
		}
		
		String outfullFileName = basePath + "/" + sqlFileName;
		
		Template temp = cfg.getTemplate("sql.ftlh","UTF-8");
		
		Writer out = new OutputStreamWriter(new FileOutputStream(outfullFileName),"UTF-8");
		temp.process(root, out);
		
		out.close();
	}
	
	public static void main(String[] args) throws Exception {
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_25);
		cfg.setClassForTemplateLoading(TemplateUtil.class, "/templates");
		Template temp = cfg.getTemplate("test.ftlh");
		Writer out = new FileWriter("E:/stsproject/mycoder/src/main/resources/templates/aa.java");
		temp.process(new HashMap<String, Object>(), out);
		
		out.close();
		//System.out.println(temp);
	}

}
