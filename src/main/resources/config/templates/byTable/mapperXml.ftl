<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" 
"http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="${packageName}.${tableInfo.upperCamelTabName}Mapper">
	<resultMap id="${tableInfo.camelTabName}ModelResultMap" type="${basePackageName}.model.${tableInfo.upperCamelTabName}Model"/>
	<sql id="select">
		<![CDATA[
			SELECT 
			<#list columnInfos as columnInfo>
			<#if columnInfo_has_next>
				T.${columnInfo.colName},
			<#else>
				T.${columnInfo.colName}
			</#if>
			</#list>
		]]>
	</sql>
	
	<sql id="findByExample">
		WHERE 1=1
		<#list columnInfos as columnInfo>
		<if test="${columnInfo.camelColName} != null and ${columnInfo.camelColName} != ''"> AND ${columnInfo.colName} = ${'#'}{${columnInfo.camelColName}}</if>
		</#list>
	</sql>
	
	<select id="get" resultMap="${tableInfo.camelTabName}ModelResultMap">
		<include refid="select"/>
		FROM  ${tableInfo.tabName} T
		<include refid="findByExample"/>
	</select>
	
	<insert id="insert">
		<#list columnInfos as columnInfo>
			<#if columnInfo.primary>
		<selectKey resultType="${columnInfo.javaColType}" order="AFTER" keyProperty="${columnInfo.camelColName}">
            </#if>
		</#list>
            SELECT LAST_INSERT_ID();        
        </selectKey>
		INSERT INTO ${tableInfo.tabName} (
				<#list columnInfos as columnInfo>
				<#if columnInfo_has_next>
					${columnInfo.colName},
				<#else>
					${columnInfo.colName}
				</#if>
				</#list>
		) VALUES (
				<#list columnInfos as columnInfo>
				<#if columnInfo_has_next>
					${'#'}{${columnInfo.camelColName}},
				<#else>
					${'#'}{${columnInfo.camelColName}}
				</#if>
				</#list>
		);
	</insert>
	
	<update id="update">
		UPDATE ${tableInfo.tabName} SET
				<#list columnInfos as columnInfo>
				<#if columnInfo_has_next>
				<if test="${columnInfo.camelColName} != null and ${columnInfo.camelColName} != ''"> ${columnInfo.colName} = ${'#'}{${columnInfo.camelColName}},</if>
				<#else>
				<if test="${columnInfo.camelColName} != null and ${columnInfo.camelColName} != ''"> ${columnInfo.colName} = ${'#'}{${columnInfo.camelColName}},</if>
				</#if>
				</#list>
				MODIFY_TIME = ${'#'}{modifyTime}
		WHERE 1=1
		<#list columnInfos as columnInfo>
			   <#if columnInfo.primary>
               AND ${columnInfo.colName} = ${'#'}{${columnInfo.camelColName}}
               </#if>
		</#list>
	</update>
	<delete id="delete" parameterType="java.util.List">
        DELETE FROM ${tableInfo.tabName} WHERE 
         <choose>
            <when test="null!=list and list.size!=0">
            <#list columnInfos as columnInfo>
			   <#if columnInfo.primary>
                ${columnInfo.colName} IN
               </#if>
		</#list>
                <foreach collection="list" item="item" index="index" open="(" separator="," close=")">
                    ${'#'}{item}
                </foreach>
            </when>
            <otherwise>
               1 != 1
            </otherwise>
        </choose>
    </delete>
</mapper>
