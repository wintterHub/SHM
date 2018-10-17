<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>index</title>
</head>
<body>
	<jsp:forward
		page="${pageContext.request.contextPath }/goods/goodsAction_homePage.action?page=1&rows=36"></jsp:forward>
</body>
</html>