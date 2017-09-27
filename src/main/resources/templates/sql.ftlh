<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${config.mapperPackage}.${config.modelName}Mapper">
  <resultMap id="BaseResultMap" type="${config.modelPackage}.${config.modelName}">
  <#list table.columns as c>
  	<#if c.primaryKey>
  	  <id column="${c.columnName}" jdbcType="${c.jdbcType}" property="${c.propertyName}" />     
  	<#else>
  	  <result column="${c.columnName}" jdbcType="${c.jdbcType}" property="${c.propertyName}" />     
  	</#if>
  </#list>
  </resultMap>
</mapper>