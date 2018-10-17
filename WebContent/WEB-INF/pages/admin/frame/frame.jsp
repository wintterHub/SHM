<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/WEB-INF/pages/include/include_admin_head.jsp"></jsp:include>
<title>商城后台管理系统</title>
</head>
<body>
	<jsp:include page="/WEB-INF/pages/admin/frame/top.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/pages/admin/frame/left.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/pages/admin/${flag}.jsp"></jsp:include>
</body>
</html>