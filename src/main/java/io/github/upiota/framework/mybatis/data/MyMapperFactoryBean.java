package io.github.upiota.framework.mybatis.data;

import java.lang.reflect.Method;

import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.mapper.MapperFactoryBean;

import tk.mybatis.mapper.mapperhelper.MapperHelper;

public class MyMapperFactoryBean<T> extends MapperFactoryBean<T> {

	private static final MapperHelper mapperHelper = new MapperHelper();
	
	public MyMapperFactoryBean() {
	}

	public MyMapperFactoryBean(Class<T> mapperInterface) {
		super(mapperInterface);
	}

	@Override
	protected void checkDaoConfig() {
		super.checkDaoConfig();
		
		Class<T> mi = getMapperInterface();
		Method[] ms = mi.getDeclaredMethods();
		Configuration config = this.getSqlSession().getConfiguration();
		for(Method m : ms){
			String msId = mi.getName()+"."+m.getName();
			if(config.hasStatement(msId,false)){
				continue;
			}
			MyMapperBuilder parser = new MyMapperBuilder(config, mi);
			parser.parse(m);
			
			
			Class<?>[] cls = mi.getInterfaces();
			MyProvider mp = new MyProvider(cls[0],mapperHelper);
			try {
				mp.setSqlSource(config.getMappedStatement(msId,false),m);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
}
