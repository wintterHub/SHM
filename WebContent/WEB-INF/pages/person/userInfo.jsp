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
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath }/css/user.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/user.js"></script>
<title>校园二手商城 - 个人信息</title>
</head>
<body>
	<jsp:include page="../include/top.jsp"></jsp:include>

	<div class="container">

		<jsp:include page="../include/userTop.jsp"></jsp:include>

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

					<div class="collapse navbar-collapse"
						id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav">
							<li class="active"><a
								href="${pageContext.request.contextPath }/user/userAction_myInfo.action">个人信息</a></li>
							<li><a href="${pageContext.request.contextPath }/goods/goodsAction_myPublish.action?page=1&rows=36">我发布的商品</a></li>
							<li><a href="${pageContext.request.contextPath }/collection/collectionAction_myCollection.action?page=1&rows=50">我的收藏</a></li>
							<li><a href="${pageContext.request.contextPath }/message/messageAction_myMessage.action?page=1&rows=36">消息中心</a></li>
						</ul>
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
						<div class="panel-heading">个人信息</div>
					</blockquote>
					<div class="user-info-panel-body">
						<div class="change-headimage">
							<button class="btn btn-default change" id="change">更换头像</button>
						</div>
						<form id="form1" class="form-horizontal"
							action="${pageContext.request.contextPath }/user/userAction_update.action"
							method="post">
							<input type="hidden" name="userName"
								value="${loginUser.userName }">
							
							<div class="form-group form-inline">
								<div class="info-item">
									<span class="info-key">昵&nbsp;&nbsp;称&nbsp;&nbsp;</span> <input
										class="form-control" name="nickName"
										value="${loginUser.nickName }">
								</div>
							</div>
							<div class="form-group form-inline">
								<div class="info-item">
									<span class="info-key">邮&nbsp;&nbsp;箱&nbsp;&nbsp;</span> <input
										class="form-control" name="email" value="${loginUser.email }"
										disabled>
								</div>
							</div>
							<div class="form-group form-inline">
								<div class="info-item">
									<span class="info-key">年&nbsp;&nbsp;龄&nbsp;&nbsp;</span> <input
										class="form-control" name="age" value="${loginUser.age }">
								</div>
							</div>
							<div class="form-group form-inline">
								<div class="info-item">
									<span class="info-key">性&nbsp;&nbsp;别&nbsp;&nbsp;</span> <input
										class="form-control" name="gender"
										value="${loginUser.gender }">
								</div>
							</div>
							<div class="form-group form-inline">
								<div class="info-item">
									<span class="info-key">学&nbsp;&nbsp;校&nbsp;&nbsp;</span> <input
										class="form-control" name="address"
										value="${loginUser.address } " disabled>
									<button id="edit-school" class="btn btn-default">修改</button>
								</div>
							</div>
							<div class="form-group form-inline">
								<div class="info-item">
									<span class="info-key">专&nbsp;&nbsp;业&nbsp;&nbsp;</span> <input
										class="form-control" name="department"
										value="${loginUser.department }">
								</div>
							</div>
							<div class="form-group form-inline">
								<div class="info-item">
									<span class="info-key">学&nbsp;&nbsp;号&nbsp;&nbsp;</span> <input
										class="form-control" name="studentID"
										value="${loginUser.studentID }">
								</div>
							</div>
							<div class="form-group form-inline">
								<div class="info-item">
									<span class="info-key">手&nbsp;&nbsp;机&nbsp;&nbsp;</span> <input
										class="form-control" name="mobile"
										value="${loginUser.mobile }">
								</div>
							</div>
							<div class="form-group form-inline">
								<div class="info-item">
									<span class="info-key">QQ号&nbsp;&nbsp;</span> <input
										class="form-control" name="qqNumber"
										value="${loginUser.qqNumber }">
								</div>
							</div>
							<br>
							<div class="form-group form-inline">
								<input type="submit" id="btn-submit" class="btn btn-danger"
									value="&nbsp;&nbsp;&nbsp;&nbsp;保存&nbsp;&nbsp;&nbsp;&nbsp;">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<a target="_black" href="${pageContext.request.contextPath }/uiAction_common_changePwd.action" 
								class="btn btn-default">修改密码</a>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="myModal" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">修改学校信息</h4>
				</div>
				<div class="modal-body">
					<div class="text-area">
						<div class="form-group form-inline">
							<div class="info-item">
								<span class="info-key">学&nbsp;&nbsp;校&nbsp;&nbsp;</span> <select
									required="" class="form-control" name="address" id="address1">
								</select> <select required="" class="form-control" name="address"
									id="address2">
								</select>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button class="btn btn-primary" id="btn-submit"
						data-dismiss="modal">确认</button>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="myModal2" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">提示</h4>
				</div>
				<div class="modal-body">
					<div class="text-area">
						<div class="form-group form-inline">
							<div class="info-item">修改成功</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button class="btn btn-primary" data-dismiss="modal">确认</button>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="../include/footer.jsp"></jsp:include>

	<script type="text/javascript">

	</script>

	<script type="text/javascript">
		$(function(){
			var changeok = '${changeok}'
			if(changeok==1){
				$('#myModal2').modal("show");
			}
		})
		$(function(){
		    $("#change").upload({
		        action:'${pageContext.request.contextPath}/user/userAction_uploadHeadImage.action',
		        name:'headImage',
		        enctype : 'multipart/form-data',
				autoSubmit : true,
		        onComplete: function (data) {
			        	if (data == -1) {
							alert("不支持给类型文件");
						} else if(data == 0){
							alert("操作失败");
						}else{
							location.reload();
						}
		        }
			});
		});
		
		$(function() {
			var isUpdate = "${isUpdate}";
			if (isUpdate == 1) {
				$('#myModal2').modal("show");
			}
		})

		$(function() {
			$("button[id='btn-submit']").click(
				function() {
					var addressT1 = $('#address1 option:selected')
							.text();
					var addressT2 = $('#address2 option:selected')
							.text();
					var addressV1 = $('#address1 option:selected')
							.val();
					var addressV2 = $('#address2 option:selected')
							.val();
					if (addressV1 != "" && addressV2 != "") {
						$.ajax({
							type : "post",
							url : "${pageContext.request.contextPath }/user/userAction_saveSchool.action",
							data : {
								"address" : addressT2
							},
							success : function(data) {
								if (data == 1) {
									location.reload();
								}
							}
						})
					}
				})
		})
		$(function() {
			$("#edit-school").click(
					function() {
						$('#myModal').modal("show");
						$("#address1").html('');
						$("#address1").append(
								"<option value=''>--请选择你的省份--</option>");
						$("#address2").html('');
						$("#address2").append(
								"<option value=''>--请选择你的学校--</option>");
						$.getJSON("../json/school.json", function(data) {
							$.each(data, function(infoIndex, info) {
								$("#address1").append(
										"<option value='" + info['id'] + "'>"
												+ info['name'] + "</option>");
							})
						})
					})
			})
		$(function() {
			$("#address1").change(
				function() {
					$("#address2").html('');
					$("#address2").append(
							"<option value=''>--请选择你的学校--</option>");
					var chooseitem = $("#address1").val();
					$.getJSON("../json/school.json", function(data) {
						$.each(data, function(infoIndex, info) {
							if (info['id'] == chooseitem) {
								$.each(info['school'],
										function(infoIndex, info) {
											$("#address2").append(
													"<option value='" + info['id'] + "'>"
															+ info['name']
															+ "</option>");
										})
							}

						})
					})
				})
		})
	</script>
<%-- 	<s:debug></s:debug> --%>
</body>
</html>