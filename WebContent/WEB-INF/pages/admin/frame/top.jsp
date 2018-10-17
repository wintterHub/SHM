<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<style>
.north-panel {
	font-family: "PingFang SC", "Helvetica Neue", Helvetica, Arial,
		"Hiragino Sans GB", "Microsoft Yahei", 微软雅黑, STHeiti, 华文细黑, sans-serif;
	height: 40px;
	background: #3C8DBC;
	padding: 10px;
	color: #ffffff;
	line-height: 1.75em;
	height: 40px;
	border-left: 225px solid #151A1A;
	padding: 10px;
}

.north-body {
	
}

.north-item1 {
	float: left;
}

.north-item2 {
	margin-right: 30px;
	line-height: 3em;
	float: right;
}

.north-panel a {
	color: #ffffff;
}

.north-panel a:hover {
	color: #D2D2D2;
}

.north-title {
	position: absolute;
	font-size: 20px;
	top: 10px;
	left: 30px;
	line-height: 2em;
	font-size: 20px;
}

.north-username {
	font-size: 14px;
}

.north-username img {
	vertical-align: middle;
	margin-right: 5px;
}
</style>

<div data-options="region:'north',border:false" class="north-panel">
	<div class="north-body">
		<div class="north-item1">
			<span class="north-title">商城后台管理系统</span>
		</div>
		<div class="north-item2">
			<input type="hidden" value="${loginManager.id}" id="loginManagerId">
			<span class="north-username"><img
				src="${pageContext.request.contextPath}/images/icons/loginManager.png"><span
				class="admin-type">管理员</span>：${loginManager.userName}</span>&nbsp;&nbsp;|&nbsp;&nbsp;<a
				class="north-logout"
				href="${pageContext.request.contextPath}/manager/managerAction_logout.action">注销账户</a>
		</div>
	</div>
</div>

<script>
	$(function() {
		var isSuperAdmin = '${loginManager.isSuperAdmin}';
		if (isSuperAdmin == 'Y') {
			$('.admin-type').html('超级管理员');
		}
	})

	
</script>