<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE>
<html lang="zh-CN">
<head>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery.cookie.js"></script>
<script>
	$(document).ready(function(){
		var school = $.cookie("User_School");
		if(school!=''){
			$('#school').text(school);
			$('#school').append("&nbsp;<span class='caret'></span>");
		}
	})
	
	$(document).ready(function(){
		if($.cookie("SHM_isLogin")!="true"){			
			$.ajax({
				type : "post",
				url : "${pageContext.request.contextPath }/user/userAction_cookieLogin.action",
				success : function(data) {
					if (data == 1) {
						location.reload();
					}
				}
			})
		}
	})

	$(function() {
		$.ajax({
			type : "post",
			async : true,
			url : "${pageContext.request.contextPath }/message/messageAction_getUserMessageCount.action",
// 			success : function(data) {
// 				if (data == 1) {
// 					location.reload();
// 				}
// 			}
		})
	})
	
	function changeSchool(){
		$('#loaclSchoolModal').modal("show");
		$("#localAddress1").html('');
		$("#localAddress1").append(
				"<option value=''>--请选择你的省份--</option>");
		$("#localAddress2").html('');
		$("#localAddress2").append(
				"<option value=''>--请选择你的学校--</option>");
		$.getJSON("${pageContext.request.contextPath }/json/school.json", function(data) {
			$.each(data, function(infoIndex, info) {
				$("#localAddress1").append(
						"<option value='" + info['id'] + "'>"
								+ info['name'] + "</option>");
			})
		})
	}
	$(function (){
		$("#localAddress1").change(
				function() {
					$("#localAddress2").html('');
					$("#localAddress2").append(
							"<option value=''>--请选择你的学校--</option>");
					var chooseitem = $("#localAddress1").val();
					$.getJSON("${pageContext.request.contextPath }/json/school.json", function(data) {
						$.each(data, function(infoIndex, info) {
							if (info['id'] == chooseitem) {
								$.each(info['school'],
										function(infoIndex, info) {
											$("#localAddress2").append(
													"<option value='" + info['id'] + "'>" + info['name'] + "</option>");
										})
							}
			
						})
					})
				})
	})
	
	$(function() {
		$("#btn-submit2").click(function(){
			var addressT1 = $('#localAddress1 option:selected').text();
			var addressT2 = $('#localAddress2 option:selected').text();
			var addressV1 = $('#localAddress1 option:selected').val();
			var addressV2 = $('#localAddress2 option:selected').val();
			if (addressV1 != "" && addressV2 != "") {				
	 			$.cookie("User_School",addressT2,{ path: '/SHM', expires: 9999});
	 			$.cookie("User_School_Code",addressV2,{ path: '/SHM', expires: 9999});
	 			location.reload();
			}
		})
	})
					
	
</script>
<style>
.topbar {
	background: #333333;
	padding: 6px 0;
	border-bottom: 3px solid #D9534F;
}

.topbar a {
	color: #fff;
}

.topbar a:hover {
	color: #f0f0f0;
}

.topbar .dropdown, .login-register {
	color: #fff;
	float: right;
}
</style>
</head>
<body>
<div class="topbar">
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-12">
				<a href="${pageContext.request.contextPath }/index.jsp">校园二手商城</a>&nbsp;&nbsp;
				<a href="javascript:void(0);" onclick="changeSchool()" id="school">请选择您所在的大学</a>
				<s:set name="user"> ${loginUser} </s:set>
				<s:if test="#user!=''">
					<div class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">${loginUser.nickName}
							&nbsp;<s:if test='#session.messageCount!=0'>
								<span class="badge">${messageCount}</span>&nbsp;</s:if><span
							class="caret"></span>
						</a>
						<ul class="dropdown-menu">
							<li><a
								href="${pageContext.request.contextPath}/user/userAction_myInfo.action"
								class="" target="_black">个人信息</a></li>
							<li><a
								href="${pageContext.request.contextPath }/goods/goodsAction_myPublish.action?page=1&rows=36"
								class="" target="_black">我的发布</a></li>
							<li><a
								href="${pageContext.request.contextPath }/collection/collectionAction_myCollection.action?page=1&rows=50"
								class="" target="_black">我的收藏</a></li>
							<li><a
								href="${pageContext.request.contextPath }/message/messageAction_myMessage.action?page=1&rows=36"
								class="" target="_black">消息中心</a></li>
							<li role="separator" class="divider"></li>
							<li><a
								href="${pageContext.request.contextPath}/user/userAction_logout.action"
								class="">退出登录</a></li>
						</ul>
					</div>
				</s:if>
				<s:else>
					<div class="login-register">
						<a
							href="${pageContext.request.contextPath}/user/userAction_goToLogin.action">登录</a>
						&nbsp;|&nbsp; <a
							href="${pageContext.request.contextPath}/user/userAction_goToRegister.action">注册</a>
					</div>
				</s:else>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="loaclSchoolModal" role="dialog">
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
 									required="" class="form-control" name="address" id="localAddress1">
 								</select> <select required="" class="form-control" name="address" 
 									id="localAddress2"> 
								</select> 
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button class="btn btn-primary" id="btn-submit2"
						data-dismiss="modal">确认</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>