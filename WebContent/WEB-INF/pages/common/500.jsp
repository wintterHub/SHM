<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath }/bootstrap/css/bootstrap.min.css">
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath }/css/common.css">
<title>服务器错误</title>
<style>
.alert {
	margin-top: 100px;
	text-align: center;
}
</style>
<script>
	function Countdown(timer, id) {
		if (timer >= 1) {
			$("#" + id + "").text(timer);
			timer -= 1;
			setTimeout(function() {
				Countdown(timer, id);
			}, 1000);
		}
		if (timer <= 0) {
			document.location = "/SHM/index.jsp";
		}
	}
	// 	Countdown(3, "jump");
</script>
</head>
<body>
	<header class="main-header">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<a
						href="${pageContext.request.contextPath }/uiAction_common_home.action">
						<%--<img src="${pageContext.request.contextPath }/images/logo.png"> --%>
						<!--校园二手商城-->
					</a>
				</div>
			</div>
		</div>
	</header>
	<div class="container">
		<div class="row">
			<div class="col-md-4"></div>
			<div class="col-md-4">
				<div class="alert alert-danger">
					<h2>500</h2>
					<%--<span id="jump">3</span>秒后跳转到首页  --%>
					<a href="${pageContext.request.contextPath }/index.jsp">返回首页</a>
				</div>
			</div>
			<div class="col-md-4"></div>
		</div>
	</div>
	<%-- 	<jsp:include --%>
	<%-- 		page="${pageContext.request.contextPath }/WEB-INF/pages/include/footer.jsp"></jsp:include> --%>

</body>
</html>