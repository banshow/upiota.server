package io.github.upiota.server.repository;


import org.apache.ibatis.annotations.Select;

import io.github.upiota.server.entity.User;
import io.github.upiota.server.util.MyMapper;


public interface UserRepository extends MyMapper<User>{
	
	@Select("select id,username,password from up_user where username = #{username}")
	User findByUsername(String username);
//	User findUsernameById(Long id);
}
