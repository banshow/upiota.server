package io.github.upiota.server;

import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;


@Component
public class MyTest implements CommandLineRunner,EnvironmentAware,ApplicationContextAware {

	// @Autowired
	// private UserRepository userRepository;

	// @Autowired
	// private RoleRepository roleRepository;

	@Autowired
	private RequestMappingHandlerMapping handlerMapping;
	
	@Autowired
	List<RequestMappingInfoHandlerMapping> handlerMappings;

	@SuppressWarnings("unchecked")
	@Override
	public void run(String... arg0) throws Exception {
//		List<User> list = userRepository.findAll();
//		list.forEach(System.out::print);
//		User user = userRepository.findOne(1l);
//		System.out.println(user);
//		Role role = roleRepository.findOne(1l);
//		System.out.println(role);
		
//	    Map<?, HandlerMethod> map =  this.handlerMapping.getHandlerMethods();
//      Iterator<?> iterator = map.entrySet().iterator();
//      while(iterator.hasNext()){
//          Map.Entry<?, HandlerMethod> entry = (Map.Entry<?, HandlerMethod>) iterator.next();
//          System.out.println(entry.getKey() +"\n" + entry.getValue());
//      }
//		handlerMappings.forEach((s)->{
//			System.out.println(s);
//		});
		
//		System.out.println(LocaleContextHolder.getLocale());
		
//		ClassPool pool = ClassPool.getDefault();
//	    // 获取需要修改的类  
//	    CtClass ct = pool.get("com.XXX.XXX.XXX.XXXX");  
//	    // 获取类里的所有方法  
//	    CtMethod[] cms = ct.getDeclaredMethods();  
//	    for (CtMethod cm : cms) {
//	        System.out.println("方法名称====" + cm.getName());  
//	  
//	        MethodInfo methodInfo = cm.getMethodInfo();  
//	  
//	        AnnotationsAttribute attribute = (AnnotationsAttribute) methodInfo  
//	                .getAttribute(AnnotationsAttribute.visibleTag);  
//	        System.out.println(attribute);  
//	  
//	        ConstPool cPool = methodInfo.getConstPool();  
//	  
//	        AnnotationsAttribute attribute2 = new AnnotationsAttribute(cPool, AnnotationsAttribute.visibleTag);  
//	        Annotation[] anns= attribute2.getAnnotations();  
//	        for(Annotation ann:anns){  
//	            System.out.println(ann.getTypeName());  
//	        }  
//	            Annotation annotation = new Annotation("org.testng.annotations.Test", cPool);  
//	            annotation.addMemberValue("invocationCount", new LongMemberValue(10L, cPool));  
//	            attribute2.setAnnotation(annotation);  
//	            methodInfo.addAttribute(attribute2);  
//	  
//	            Annotation annotation2 = attribute2.getAnnotation("org.testng.annotations.Test");  
//	            long text = ((LongMemberValue) annotation2.getMemberValue("invocationCount")).getValue();  
//	            attribute = (AnnotationsAttribute) methodInfo.getAttribute(AnnotationsAttribute.visibleTag);  
//	  
//	            System.out.println(attribute);  
//	            System.out.println(text);  
//		
//		
//		
//	    }
		
	}

	@Override
	public void setEnvironment(Environment environment) {
		
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//		Map<String, Object> cls = applicationContext.getBeansWithAnnotation(Controller.class);
//		cls.values().forEach(b->{
//			try {
//				b = AopTargetUtils.getTarget(b);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			Class<?> c = b.getClass();
//			try {
//				c = Class.forName(c.getName());
//			} catch (ClassNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			Method[] ms = c.getDeclaredMethods();
//			
//			for(Method m : ms) {
//				System.out.println(m.getName());
//				System.out.println(m.getDeclaredAnnotations());
//			}
//		});
	}
}
