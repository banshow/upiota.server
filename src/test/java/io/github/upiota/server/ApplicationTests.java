package io.github.upiota.server;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit4.SpringRunner;

import io.github.upiota.server.mycoder.model.Config;
import io.github.upiota.server.mycoder.util.DBUtil;
import io.github.upiota.server.mycoder.util.TemplateUtil;
import io.github.upiota.server.sys.entity.Dict;
import io.github.upiota.server.sys.mapper.DictMapper;
import io.github.upiota.server.sys.mapper.UserMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Autowired
	private DictMapper dictRepository;
	
//	@Autowired
//	private UserRepository userRepository;
	
	@Test
	public void contextLoads() throws Exception {	
//		Dict d = new Dict();
//		d.setType("SEX");
//		Iterable<Dict> list = dictRepository.findAll(Example.of(d));
//		
//		list.forEach(System.out::println);
		
		Config c = new Config();
		c.setTableName("up_menu");
		c.setModelName("Menu");
		c.setGenService(true);
		c.setGenController(true);
		c.setSqlPackage("sys");
		TemplateUtil.gen(c);
		
		
	}
	@Test
	public void save() {			
//		Dict dict = new Dict();
//		dict.setLabel("aaa");
//		dict.setType("A");
//		dict.setSort(10);
//		dict.setValue("ddd");
//		
//		dictRepository.saveIgnoreNull(dict);
		
//		Dict d = dictRepository.selectTypeById(1l);
//		
//		System.out.println(d);
	}

}
