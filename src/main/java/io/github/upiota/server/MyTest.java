package io.github.upiota.server;

import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import io.github.upiota.server.repository.UserRepository;

@Component
public class MyTest implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	//@Autowired
	//private RoleRepository roleRepository;
	
	@Autowired
    private RequestMappingHandlerMapping handlerMapping;
	
	@Override
	public void run(String... arg0) throws Exception {
//		List<User> list = userRepository.findAll();
//		list.forEach(System.out::print);
		
//		User user = userRepository.findOne(1l);
//		System.out.println(user);
//		Role role = roleRepository.findOne(1l);
//		System.out.println(role);
		
	  Map<?, HandlerMethod> map =  this.handlerMapping.getHandlerMethods();
      Iterator<?> iterator = map.entrySet().iterator();
      while(iterator.hasNext()){
          Map.Entry<?, HandlerMethod> entry = (Map.Entry<?, HandlerMethod>) iterator.next();
          System.out.println(entry.getKey() +"\n" + entry.getValue());
      }
		
		
		
	}

}
