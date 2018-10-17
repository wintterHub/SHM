<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<style>
.west-panel {
	width: 225px;
	background: #1A2225;
	border: 0;
}

.west-panel .item-list {
	
}

.west-panel .item-list .item {
	font-size: 14px;
	text-align: left;
	padding: 15px 0;
	padding-left: 30px;
}

.west-panel .item-list .item:hover {
	background: #1F282D;
}

.item a {
	color: #fff;
}

.item img {
	vertical-align: middle;
	margin-right: 8px;
}

#active {
	background: #2C3B42;
	border-left: 3px solid #3C8DBC;
}
</style>
<html>
<body>
	<div data-options="region:'west'" class="west-panel">
		<div class="item-list">
<!-- 			<div class="item baseInfo" onclick="active('baseInfo')"> -->
<!-- 				<a href="#"><img -->
<%-- 					src="${pageContext.request.contextPath}/images/icons/base.png">基本信息</a> --%>
<!-- 			</div> -->
			<div class="item memberManage" onclick="active('memberManage')">
				<a href=""><img
					src="${pageContext.request.contextPath}/images/icons/member.png">会员管理</a>
			</div>
			<div class="item goodsManage" onclick="active('goodsManage')">
				<a href="#"><img
					src="${pageContext.request.contextPath}/images/icons/goods.png">商品管理</a>
			</div>
			<div class="item messageManage" onclick="active('messageManage')">
				<a href="#"><img
					src="${pageContext.request.contextPath}/images/icons/message.png">评论管理</a>
			</div>
<!-- 			<div class="item collectionManage" -->
<!-- 				onclick="active('collectionManage')"> -->
<!-- 				<a href="#"><img -->
<%-- 					src="${pageContext.request.contextPath}/images/icons/collection.png">收藏管理</a> --%>
<!-- 			</div> -->
<!-- 			<div class="item pageManage" onclick="active('pageManage')"> -->
<!-- 				<a href="#"><img -->
<%-- 					src="${pageContext.request.contextPath}/images/icons/page.png">页面管理</a> --%>
<!-- 			</div> -->
			<s:set name="user"> ${loginManager.isSuperAdmin} </s:set>
			<s:if test='#user=="Y"'>
				<div class="item managerManage" onclick="active('managerManage')">
					<a href="#"><img
						src="${pageContext.request.contextPath}/images/icons/manager.png">用户管理</a>
				</div>
			</s:if>
		</div>
	</div>
	<script>
		function active(clazz) {
			window.location.href = "${pageContext.request.contextPath}/manager/managerAction_navJump.action?flag="
					+ clazz
		};
		$(function() {
			$(".item").removeAttr('id');
			var flag = '${flag}';
			$('.' + flag + '').attr('id', 'active');
		})
	</script>
</body>
</html>
