<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/admin/managerManage.js"></script>
<title>用户管理</title>
</head>
<body class="easyui-layout">
	<div data-options="region:'center'" id="center-panel">
		<table id="grid"></table>
	</div>

	<input id="searchbox" style="width: 250px; height: 26px;">
	<div id="menu" style="width: 120px;">
		<div data-options="name:'userName'">&nbsp;用&nbsp;户&nbsp;名</div>
		<div data-options="name:'email'">&nbsp;邮&nbsp;箱</div>
		<div data-options="name:'mobile'">&nbsp;手&nbsp;机</div>
	</div>

	<div id="addManagerWindow">
		<div class="" style="width: 280px; padding: 30px 160px">
			<form id="addManagerForm">
				<div style="margin-bottom: 8px">
					<input class="easyui-textbox" style="width: 100%; height: 32px"
						data-options="label:'用户名',required:true" name="userName">
				</div>
				<div style="margin-bottom: 8px">
					<input class="easyui-textbox" style="width: 100%; height: 32px"
						data-options="label:'邮 箱',validType:'email',required:true"
						name="email">
				</div>
				<div style="margin-bottom: 8px">
					<input class="easyui-textbox" style="width: 100%; height: 32px"
						data-options="label:'密 码',required:true" name="password">
				</div>
				<div style="margin-bottom: 8px">
					<select class="easyui-combobox" style="width: 100%; height: 32px"
						data-options="label:'角 色',required:true" name="isSuperAdmin">
						<option value=""></option>
						<option value="Y">超级管理员</option>
						<option value="N">普通管理员</option>
					</select>
				</div>
				<div style="margin-bottom: 8px">
					<input class="easyui-numberbox" style="width: 100%; height: 32px"
						data-options="label:'手 机',validType:'length[11,11]'" name="mobile">
				</div>
				<div style="margin-bottom: 8px">
					<input class="easyui-numberbox" style="width: 100%; height: 32px"
						data-options="label:'备 注'" name="memo">
				</div>
				<div>
					<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
						id="save-manager-btn"
						style="width: 100%; height: 32px; margin-top: 20px;">保存</a>
				</div>
			</form>
		</div>
	</div>

	<div id="editManagerWindow">
		<div class="" style="width: 280px; padding: 30px 160px">
			<form id="editManagerForm">
				<input type="hidden" name="id">
				<div style="margin-bottom: 8px">
					<input class="easyui-textbox" style="width: 100%; height: 32px"
						data-options="label:'用户名',required:true" name="userName">
				</div>
				<div style="margin-bottom: 8px">
					<input class="easyui-textbox" style="width: 100%; height: 32px"
						data-options="label:'邮 箱',validType:'email',required:true"
						name="email">
				</div>
				<div style="margin-bottom: 8px">
					<select class="easyui-combobox" style="width: 100%; height: 32px"
						data-options="label:'角 色',required:true" name="isSuperAdmin">
						<option value=""></option>
						<option value="Y">超级管理员</option>
						<option value="N">普通管理员</option>
					</select>
				</div>
				<div style="margin-bottom: 8px">
					<input class="easyui-numberbox" style="width: 100%; height: 32px"
						data-options="label:'手 机',validType:'length[11,11]'" name="mobile">
				</div>
				<div style="margin-bottom: 8px">
					<input class="easyui-numberbox" style="width: 100%; height: 32px"
						data-options="label:'备 注'" name="memo">
				</div>
				<div>
					<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
						id="edit-manager-btn"
						style="width: 100%; height: 32px; margin-top: 20px;">保存</a>
				</div>
			</form>
		</div>
	</div>

	<div id="editManagerPasswordWindow">
		<div class="" style="width: 280px; padding: 30px 160px">
			<form id="editManagerPasswordForm">
				<input type="hidden" name="id"> <input type="hidden"
					name="email">
				<div style="margin-bottom: 8px">
					<input class="easyui-textbox" style="width: 100%; height: 32px"
						data-options="label:'新密码',required:true" name="password">
				</div>
				<div>
					<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
						id="edit-manager-password-btn"
						style="width: 100%; height: 32px; margin-top: 20px;">修改</a>
				</div>
			</form>
		</div>
	</div>
</body>
</html>