<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE>
<html lang="zh-CN">
<head>
<jsp:include page="../include/include_head.jsp"></jsp:include>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath }/bootstrap/css/bootstrapValidator.min.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/bootstrap/js/bootstrapValidator.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/bootstrap/js/zh_CN.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/register.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/register.css">
<title>注册</title>
</head>
<body>
<!-- 	<header class="main-header"> -->
<!-- 		<div class="container"> -->
<!-- 			<div class="row"> -->
<!-- 				<div class="col-md-12"> -->
<!-- 					<a -->
<%-- 						href="${pageContext.request.contextPath }/uiAction_common_home.action"> --%>
						<%-- 						<img src="${pageContext.request.contextPath }/images/logo.png"> --%>
						<!-- 						校园二手商城 -->
<!-- 					</a> -->
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	</header> -->
	<section class="content-wrap">
		<div class="container">
			<div class="row">
				<div class="col-md-12 col-xs-12">
					<div class="register-panel">
						<div class="title">
							<h3>注册账号</h3>
						</div>
						<div class="form">
							<form class="form-horizontal" role="form"
								action="${pageContext.request.contextPath }/user/userAction_register.action?backUrl=${backUrl}"
								method="post" id="defaultForm">
								<div class="form-group">
									<input class="form-control input-lg" type="text"
										placeholder="昵称" name="nickName">
								</div>
								<div class="form-group">
									<input class="form-control input-lg" type="password"
										placeholder="密码" name="password">
								</div>
								<div class="form-group">
									<input class="form-control input-lg" type="password"
										placeholder="再次确认密码" name="passwordAgain">
								</div>
								<div id="vcode-form">
									<div class="form-group">
										<input class="form-control input-lg" id="emailinput"
											type="email" placeholder="邮箱" name="email">
									</div>
									<div class="form-group form-inline">
										<button class="btn btn-lg btn-danger" type="button"
											id="vcodebtn">发送验证码至邮箱</button>
										<input class="form-control input-lg" type="text"
											placeholder="验证码" name="vcode" id="vcodeinput">
									</div>
								</div>
								<font color="#D9534F"><s:property
										value="%{registererror}" /></font>
								<div class="form-group">
									<button id="submitbtn" class="btn-block btn btn-lg btn-danger"
										type="submit">立即注册</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<jsp:include page="../include/footer.jsp"></jsp:include>

</body>
</html>