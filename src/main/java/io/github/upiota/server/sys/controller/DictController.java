package io.github.upiota.server.sys.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.upiota.server.base.BaseController;
import io.github.upiota.server.base.ResponseResult;
import io.github.upiota.server.base.RestResultGenerator;
import io.github.upiota.server.sys.entity.Dict;
import io.github.upiota.server.sys.repository.DictRepository;

@RestController
@RequestMapping("dict")
public class DictController extends BaseController{

	//@Autowired
	//private DictService dictSevice;
	
	@Autowired
	private DictRepository dictRepository;
	
	//@Autowired
    //private RequestMappingHandlerMapping handlerMapping;

	
	@GetMapping("list")
	//@PreAuthorize("hasAuthority('systemManager')")
	public ResponseResult list(Dict dict){
//		Map<?, HandlerMethod> map =  this.handlerMapping.getHandlerMethods();
//      Iterator<?> iterator = map.entrySet().iterator();
//      while(iterator.hasNext()){
//         Map.Entry<?, HandlerMethod> entry = (Map.Entry<?, HandlerMethod>) iterator.next();
//         System.out.println(entry.getKey() +"\n" + entry.getValue());
//        }
		List<Dict> page = dictRepository.selectAll();
		return RestResultGenerator.genResult("成功!").putData("list", page);
	}
	
}
