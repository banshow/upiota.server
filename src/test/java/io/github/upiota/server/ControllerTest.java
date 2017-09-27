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

import io.github.upiota.server.entity.Resource;
import io.github.upiota.server.entity.Role;
import io.github.upiota.server.entity.User;
import io.github.upiota.server.entity.UserRole;
import io.github.upiota.server.repository.ResourceRepository;
import io.github.upiota.server.repository.RoleRepository;
import io.github.upiota.server.repository.UserRepository;
import io.github.upiota.server.repository.UserRoleRepository;

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
	private ResourceRepository resourceRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRoleRepository userRoleRepository;
	
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
		
		System.out.println(list);
		
		
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
				.header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1ZGllbmNlIjoid2ViIiwiY3JlYXRlZCI6MTQ5NjQ5NTU4MjE1MiwiZXhwIjoxNDk3MTAwMzgyfQ.g9lCduWQyX6xZPG7yv0kseFHJZPumgP_mBus-Ecm0c31g-f6bNdxFFNrPVl01fFq3HknuI4LWP2o10aZsb5_TQ")
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
				.header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1ZGllbmNlIjoid2ViIiwiY3JlYXRlZCI6MTUwNTk4NTI4MzA5NiwiZXhwIjoxNTA2NTkwMDgzfQ.ODX_lD7Q8YhE0Uz8UZdCtgSkIGLbd4Gg1eGOWwhBFyj_2Nt8yki9XjRF9vNHk3aJLJT_jqv2Dmr8HPxwNMbXCQ")
				.params(params)
				//.contentType(MediaType.APPLICATION_JSON_UTF8).content(requestJson)
				)
		.andExpect(status().isOk()).andDo(print());
	}
}
