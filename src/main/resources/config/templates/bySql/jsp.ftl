<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="com.alan.system.model.Subforum"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/jsp/common/header.jsp"></jsp:include>

<div class="container">
	<div class="row">
		<div class="span12">
			<table class="table table-bordered table-hover">
				<tr><#list columnInfos as columnInfo>
					<th>${columnInfo.colComment}</th>
					</#list>
				</tr>
				
				<c:forEach items="${tableInfo.camelTabName}"  var="var">
					<tr>
					<#list columnInfos as columnInfo>
					<td>${columnInfo.camelColName}</td>
					</#list>
					<td>
						<a onclick="edit()">编辑</a>|
						<a onclick="del()">删除</a>|
						<a onclick="copy()">复制</a>
					</td>
				</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	<div class="row">
		<div class="span12" style="display:none">
			<form id="form" action="${tableInfo.camelTabName}/add.shtml" class="form-horizontal"
				method="post">
				<input name="id" type="hidden" id="id">
				<!-- <div class="control-group">
					<div class="controls">
						<label
							style="font-size: 28px; font-weight: bolder; margin-top: 10px;"></label>
					</div>
				</div> -->
				<#list columnInfos as columnInfo>
				<div class="control-group">
					<label class="control-label">${columnInfo.colComment}</label>
					<div class="controls">
						<input name="${columnInfo.camelColName}" type="text" id="${columnInfo.camelColName}" placeholder="">
					</div>
				</div>
				</#list>
				
				<div class="control-group">
					<div class="controls">
						<input type="submit" class="btn" value="保存" /> <input
							type="reset" class="btn" value="重填" />
					</div>
				</div>

			</form>
		</div>
	</div>
</div>
<jsp:include page="/jsp/common/footer.jsp"></jsp:include>
<script type="text/javascript" src="js/${tableInfo.camelTabName}.js"></script>
<script type="text/javascript" src="js/PopUtil.js"></script>