package io.github.upiota.server.repository;


import java.util.List;

import org.apache.ibatis.annotations.Select;

import io.github.upiota.server.entity.User;
import io.github.upiota.server.util.MyMapper;


public interface UserRepository extends MyMapper<User>{
	
	@Select("select id,username,password from up_user where username = #{username}")
	User findByUsername(String username);
	User findByUsername1(String username);
	List<String> selectUsernameByUsername(String username);
//	User findUsername(String username);
//	User findUsernameById(Long id);
}
