<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/admin/messageManage.js"></script>
<title>评论管理</title>
</head>
<body class="easyui-layout">
	<div data-options="region:'center'" id="center-panel">
		<table id="grid"></table>
	</div>

	<input id="searchbox" style="width: 250px; height: 26px;">
	<div id="menu" style="width: 120px;">
		<div data-options="name:'content'">&nbsp;内&nbsp;容</div>
		<div data-options="name:'toUser'">&nbsp;接&nbsp;收&nbsp;者</div>
		<div data-options="name:'fromUser'">&nbsp;发&nbsp;送&nbsp;者</div>
		<div data-options="name:'toGoods'">&nbsp;所&nbsp;属&nbsp;商&nbsp;品&nbsp;ID</div>
	</div>

	<div id="messageWindow">
		<div class="" style="width: 280px; padding: 30px 160px">
			<form id="messageForm">
				<div style="margin-bottom: 8px">
					<input class="easyui-textbox" style="width: 100%; height: 32px"
						data-options="label:'内容'" name="content" readonly>
				</div>
				<div style="margin-bottom: 8px">
					<select class="easyui-combobox" style="width: 100%; height: 32px"
						data-options="label:'类型'" name="type" readonly>
						<option value="P">评论</option>
						<option value="H">回复</option>
					</select>
				</div>
				<div style="margin-bottom: 8px">
					<input class="easyui-textbox" style="width: 100%; height: 32px"
						data-options="label:'接收者'" name="toUserName" readonly>
				</div>
				<div style="margin-bottom: 8px">
					<input class="easyui-textbox" style="width: 100%; height: 32px"
						data-options="label:'发送者'" name="fromUserName" readonly>
				</div>
				<div style="margin-bottom: 8px">
					<input class="easyui-textbox" style="width: 100%; height: 32px"
						data-options="label:'所属商品ID'" name="toGoodsId" readonly>
				</div>
				<div style="margin-bottom: 8px">
					<input class="easyui-textbox" style="width: 100%; height: 32px"
						data-options="label:'发表时间'" name="publishTime" readonly>
				</div>
			</form>
		</div>
	</div>

	<div id="delMessageWindow">
		<div class="" style="width: 280px; padding: 30px 100px">
			<div
				style="margin-bottom: 8px; color: red; font-size: 15px; text-align: center;">确定要删除吗？删除后将不可恢复</div>
			<div style="text-align: center;">
				<a href="javascript:void(0);" class="easyui-linkbutton"
					iconCls="icon-cancel" id="cancel-btn"
					style="width: 40%; height: 32px; margin-top: 20px;">取消</a>
				&nbsp;&nbsp; <a href="javascript:void(0);" class="easyui-linkbutton"
					iconCls="icon-ok" id="del-btn"
					style="width: 40%; height: 32px; margin-top: 20px;">删除</a>
			</div>
		</div>
	</div>
</body>
</html>