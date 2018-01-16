package io.github.upiota.server;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.upiota.server.sys.entity.Resource;
import io.github.upiota.server.sys.entity.Role;
import io.github.upiota.server.sys.entity.User;
import io.github.upiota.server.sys.entity.UserRole;
import io.github.upiota.server.sys.mapper.ResourceMapper;
import io.github.upiota.server.sys.mapper.RoleMapper;
import io.github.upiota.server.sys.mapper.UserMapper;
import io.github.upiota.server.sys.mapper.UserRoleMapper;

@RunWith(SpringRunner.class)
//@WebMvcTest(controllers = UserController.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {
	@Autowired
	private MockMvc mockMvc;

//	@Autowired
//	private WebApplicationContext webApplicationContext;
//	@MockBean
//	private UserRepository userRepository;

//	@Before
//	public void setUp() {
//		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//	}
	
	@Autowired
	private ResourceMapper resourceRepository;
	
	@Autowired
	private UserMapper userRepository;
	
	@Autowired
	private RoleMapper roleRepository;
	
	@Autowired
	private UserRoleMapper userRoleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Test
	public void addUser(){
//		User u = new User();
//		u.setUsername("admin");
//		u.setPassword(passwordEncoder.encode("admin"));
//		u.setCreateAt(new Date());
//		userRepository.save(u);
		
//		Role r = new Role();
//		r.setRoleName("admin");
//		r.setRoleDesc("管理员");
//		roleRepository.save(r);
//		UserRole ur = new UserRole();
		//ur.setId(id);
		
//		Role r = roleRepository.findOne(1l);
//		User u = userRepository.findOne(1l);
		List<String> list = resourceRepository.listResourceByUserId(1l);
		
		System.out.println(userRepository.selectUsernameByUsername("admin"));
		
		
	}
	
	@Test
	public void testAuth() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		Map<String,String> map = new HashMap<String,String>();
		map.put("username", "admin");
		map.put("password", "admin");
		String requestJson = mapper.writeValueAsString(map);
		this.mockMvc.perform(
				post("/auth")
				.contentType(MediaType.APPLICATION_JSON_UTF8).content(requestJson)			
				)
		.andExpect(status().isOk()).andDo(print());
	}
	
	@Test
	public void testUserList() throws Exception {
		this.mockMvc.perform(
				get("/user/list")
				.header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1ZGllbmNlIjoid2ViIiwiY3JlYXRlZCI6MTUxMjAyMTQ0MTg0MCwiZXhwIjoxNTEyNjI2MjQxfQ.pY2YeKeU83hVaRvRn1sIsV92lMtQDokGGc0KYZO2ZXvpXPWzLej376HjsAlc3N4sG3ldXoxhKPsfEgcCv4c21w")
				)
		.andExpect(status().isOk()).andDo(print());
	}
	
	@Test
	public void testDictList() throws Exception {
		//ObjectMapper mapper = new ObjectMapper();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("page", 1);
		map.put("size", 2);
		//map.put("type", "SEX");
		//String requestJson = mapper.writeValueAsString(map);
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("page", "1");
		params.add("size", "2");
		params.add("type", "SEX");
		this.mockMvc.perform(
				get("/dict/list")
				.header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1ZGllbmNlIjoid2ViIiwiY3JlYXRlZCI6MTUxMjAyMTQ0MTg0MCwiZXhwIjoxNTEyNjI2MjQxfQ.pY2YeKeU83hVaRvRn1sIsV92lMtQDokGGc0KYZO2ZXvpXPWzLej376HjsAlc3N4sG3ldXoxhKPsfEgcCv4c21w")
				.params(params)
				//.contentType(MediaType.APPLICATION_JSON_UTF8).content(requestJson)
				)
		.andExpect(status().isOk()).andDo(print());
	}
}
