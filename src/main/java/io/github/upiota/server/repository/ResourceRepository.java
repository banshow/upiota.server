package io.github.upiota.server.repository;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import io.github.upiota.server.entity.Resource;
import io.github.upiota.server.util.MyMapper;

public interface ResourceRepository extends MyMapper<Resource>{
	@Select("select rs.resource_code from up_resource rs left join up_role_resource rr on rs.id = rr.resource_id left join up_user_role ur on rr.role_id = ur.role_id where ur.user_id = #{userId}")
	List<String> listResourceByUserId(Long userId);
}
