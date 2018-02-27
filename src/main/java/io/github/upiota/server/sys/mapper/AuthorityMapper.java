package io.github.upiota.server.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import io.github.upiota.server.sys.entity.Authority;
import io.github.upiota.server.util.MyMapper;

public interface AuthorityMapper extends MyMapper<Authority>{
	@Select("select a.authority_code from up_authority a left join up_role_authority ra on a.id = ra.authority_id left join up_user_role ur on ra.role_id = ur.role_id where ur.user_id = #{userId}")
	List<String> listAuthorityByUserId(Long userId);
}
