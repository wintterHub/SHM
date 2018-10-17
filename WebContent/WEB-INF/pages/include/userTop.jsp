<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery.ocupload-1.1.2.js"></script>
<style>
.wrapper {
	height: 250px;
	width: 100%;
	background-image: url("/userTop_bg.jpg");
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

<div class="row">
	<div class="wrapper">
		<div class="col-md-12 col-sm-12 col-xs-12">
			<div class="head-img">
				<s:if test='#session.loginUser.headImagePath!=null'>
					<img src="${loginUser.headImagePath }">
				</s:if>
				<s:else>
					<img
						src="${pageContext.request.contextPath }/images/noavatar_small.gif">
				</s:else>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="myModal3" role="dialog">
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
			$('#myModal3').modal("show");
		})
	})

	
</script>