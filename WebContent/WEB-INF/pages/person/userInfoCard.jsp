<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE>
<html lang="zh-CN">
<head>
<jsp:include page="../include/include_head.jsp"></jsp:include>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath }/css/user.css">
<title>校园二手商城 - 用户信息</title>
<style>
.wrapper {
	height: 250px;
	width: 100%;
	background-image: url("../images/thomas-griesbeck-149034.jpg");
	background-position: center 40%;
	background-size: cover;
	padding-top: 100px;
}

.wrapper .head-img {
	text-align: center;
}

.wrapper img {
	border: 3px solid #FFFFFF;
	width: 90px;
	height: 90px;
	-webkit-border-radius: 50%;
	-moz-border-radius: 50%;
	border-radius: 50%;
}

.modal-body {
	text-align: center;
}

.modal-body img {
	border: 3px solid #FFFFFF;
	width: 262px;
	height: 262px;
}

.modal-footer div {
	overflow: visible;
}
</style>
</head>
<body>
	<jsp:include page="../include/top.jsp"></jsp:include>

	<div class="container">

		<div class="row">
			<div class="wrapper">
				<div class="col-md-12 col-sm-12 col-xs-12">
					<div class="head-img">
						<s:if test='#userInfo.headImagePath!=null'>
							<img src="${userInfo.headImagePath }">
						</s:if>
						<s:else>
							<img
								src="${pageContext.request.contextPath }/images/noavatar_small.gif">
						</s:else>
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<nav class="navbar navbar-default">
				<div class="col-md-12 col-sm-12 col-xs-12">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle collapsed"
							data-toggle="collapse"
							data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
							<span class="icon-bar"></span> <span class="icon-bar"></span> <span
								class="icon-bar"></span>
						</button>
						<a class="navbar-brand"
							href="${pageContext.request.contextPath }/index.jsp">首页&nbsp;&nbsp;&nbsp;</a>
					</div>
				</div>
			</nav>
		</div>
	</div>

	<div class="container">
		<div class="row mybox">
			<div class="detailed-panel">
				<div class="col-md-12 col-sm-12 col-xs-12">
					<blockquote>
						<div class="panel-heading">用户信息</div>
					</blockquote>
					<div class="user-info-panel-body">
						<form id="form1" class="form-horizontal"
							action="${pageContext.request.contextPath }/user/userAction_update.action"
							method="post">
							<input type="hidden" name="userName"
								value="${userInfo.userName }">

							<div class="form-group form-inline">
								<div class="info-item">
									<span class="info-key">昵&nbsp;&nbsp;称&nbsp;&nbsp;</span> <input
										class="form-control" name="nickName"
										value="${userInfo.nickName }" disabled>
								</div>
							</div>
							<div class="form-group form-inline">
								<div class="info-item">
									<span class="info-key">年&nbsp;&nbsp;龄&nbsp;&nbsp;</span> <input
										class="form-control" name="age" value="${userInfo.age }"
										disabled>
								</div>
							</div>
							<div class="form-group form-inline">
								<div class="info-item">
									<span class="info-key">性&nbsp;&nbsp;别&nbsp;&nbsp;</span> <input
										class="form-control" name="gender"
										value="${userInfo.gender }" disabled>
								</div>
							</div>
							<div class="form-group form-inline">
								<div class="info-item">
									<span class="info-key">学&nbsp;&nbsp;校&nbsp;&nbsp;</span> <input
										class="form-control" name="address"
										value="${userInfo.address } " disabled>
								</div>
							</div>
							<div class="form-group form-inline">
								<div class="info-item">
									<span class="info-key">专&nbsp;&nbsp;业&nbsp;&nbsp;</span> <input
										class="form-control" name="department"
										value="${userInfo.department }" disabled>
								</div>
							</div>
							<div class="form-group form-inline">
								<div class="info-item">
									<span class="info-key">学&nbsp;&nbsp;号&nbsp;&nbsp;</span> <input
										class="form-control" name="studentID"
										value="${userInfo.studentID }" disabled>
								</div>
							</div>
							<br>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="../include/footer.jsp"></jsp:include>

	<s:debug></s:debug>
	<div class="modal fade" id="myModal" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">头像</h4>
			</div>
			<div class="modal-body">
				<s:if test='#session.loginUser.headImagePath!=null'>
					<img src="${loginUser.headImagePath }">
				</s:if>
				<s:else>
					<img
						src="${pageContext.request.contextPath }/images/noavatar_small.gif">
				</s:else>
			</div>
			<div class="modal-footer">
				<button class="btn btn-default" data-dismiss="modal">关闭</button>
			</div>
		</div>
	</div>
</div>

<script>
	$(function() {
		$(".wrapper img").click(function() {
			$('#myModal').modal("show");
		})
	})

	
</script>
</body>
</html>