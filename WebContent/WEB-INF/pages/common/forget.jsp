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
	src="${pageContext.request.contextPath }/js/forget.js"></script>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath }/css/forget.css">
<title>忘记密码</title>
</head>
<body>
<!-- 	<header class="main-header"> -->
<!-- 		<div class="container"> -->
<!-- 			<div class="row"> -->
<!-- 				<div class="col-md-12"> -->
<!-- 					<a -->
<%-- 						href="${pageContext.request.contextPath }/uiAction_common_home.action"> --%>
						<%--<img src="${pageContext.request.contextPath }/images/logo.png"> --%>
						<!--校园二手商城-->
<!-- 					</a> -->
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	</header> -->
	<section class="content-wrap">
		<div class="container">
			<div class="row">
				<div class="col-md-12 col-xs-12">
					<div class="forget-panel">
						<div class="title">
							<h3>重置密码</h3>
						</div>
						<div class="form">
							<form class="form-horizontal" method="post"
								action="${pageContext.request.contextPath }/user/userAction_forget.action?backUrl=${backUrl}">
								<div class="form-group">
									<input type="email" class="form-control input-lg"
										id="emailinput" placeholder="邮箱" name="email">
								</div>
								<div class="form-group form-inline">
									<button class="btn btn-lg btn-danger" type="button"
										id="vcodebtn">发送邮箱验证码</button>
									<input class="form-control input-lg" type="text"
										placeholder="验证码" name="vcode" id="vcodeinput">
								</div>
								<div class="form-group">
									<button id="submitbtn" type="submit"
										class="btn-block btn btn-lg btn-danger">发送新密码至邮箱</button>
								</div>
							</form>
							<s:set name="error">
								<s:property value="forgeterror" />
							</s:set>
							<s:if test="#error!=''">
								<div class="alert alert-danger">
									<button class="close" data-dismiss="alert">&times;</button>
									<s:property value="#error" />
								</div>
							</s:if>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

	<jsp:include page="../include/footer.jsp"></jsp:include>

</body>
</html>