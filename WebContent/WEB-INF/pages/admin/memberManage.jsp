<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/admin/memberManage.js"></script>
<title>会员管理</title>
</head>
<body class="easyui-layout">
	<div data-options="region:'center'" id="center-panel">
		<table id="grid"></table>
	</div>

	<input id="searchbox" style="width: 250px; height: 26px;">
	<div id="menu" style="width: 120px;">
		<div data-options="name:'nickName'">&nbsp;昵&nbsp;称</div>
		<div data-options="name:'studentID'">&nbsp;学&nbsp;号</div>
		<div data-options="name:'email'">&nbsp;邮&nbsp;箱</div>
		<div data-options="name:'age'">&nbsp;年&nbsp;龄</div>
		<div data-options="name:'address'">&nbsp;学&nbsp;校</div>
		<div data-options="name:'department'">&nbsp;专&nbsp;业</div>
		<div data-options="name:'qqNumber'">&nbsp;Q&nbsp;Q</div>
		<div data-options="name:'mobile'">&nbsp;手&nbsp;机</div>
	</div>

	<div id="addUserWindow">
		<div class="" style="width: 280px; padding: 30px 160px">
			<form id="addUserForm">
				<div style="margin-bottom: 8px">
					<input class="easyui-textbox" style="width: 100%; height: 32px"
						data-options="label:'昵称',required:true" name="nickName">
				</div>
				<div style="margin-bottom: 8px">
					<input class="easyui-textbox" style="width: 100%; height: 32px"
						data-options="label:'邮箱',validType:'email',required:true"
						name="email">
				</div>
				<div style="margin-bottom: 8px">
					<input class="easyui-textbox" style="width: 100%; height: 32px"
						data-options="label:'密码',required:true" name="password">
				</div>
				<div style="margin-bottom: 8px">
					<input class="easyui-numberbox" style="width: 100%; height: 32px"
						data-options="label:'学号'" name="studentID">
				</div>
				<div style="margin-bottom: 8px">
					<select class="easyui-combobox" style="width: 100%; height: 32px"
						data-options="label:'性别'" name="gender">
						<option value=""></option>
						<option value="男">男</option>
						<option value="女">女</option>
					</select>
				</div>
				<div style="margin-bottom: 8px">
					<input class="easyui-numberbox" style="width: 100%; height: 32px"
						data-options="label:'年龄',validType:'length[1,3]'" name="age">
				</div>
				<div style="margin-bottom: 8px">
					<input class="easyui-textbox" style="width: 100%; height: 32px"
						data-options="label:'学校'" name="address">
				</div>
				<div style="margin-bottom: 8px">
					<input class="easyui-textbox" style="width: 100%; height: 32px"
						data-options="label:'专业'" name="department">
				</div>
				<div style="margin-bottom: 8px">
					<input class="easyui-numberbox" style="width: 100%; height: 32px"
						data-options="label:'QQ',validType:'length[4,15]'" name="qqNumber">
				</div>
				<div style="margin-bottom: 8px">
					<input class="easyui-numberbox" style="width: 100%; height: 32px"
						data-options="label:'手机',validType:'length[11,11]'" name="mobile">
				</div>
				<div style="margin-bottom: 8px">
					<input class="easyui-numberbox" style="width: 100%; height: 32px"
						data-options="label:'备注'" name="memo">
				</div>
				<div>
					<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
						style="width: 100%; height: 32px; margin-top: 20px;">保存</a>
				</div>
			</form>
		</div>
	</div>

	<div id="editUserWindow">
		<div class="" style="width: 280px; padding: 30px 160px">
			<form id="editUserForm">
				<input type="hidden" name="userName">
				<div style="margin-bottom: 8px">
					<input class="easyui-textbox" style="width: 100%; height: 32px"
						data-options="label:'ID'" name="userNameCopy" readonly disabled>
				</div>
				<div style="margin-bottom: 8px">
					<input class="easyui-textbox" style="width: 100%; height: 32px"
						data-options="label:'昵称',required:true" name="nickName">
				</div>
				<div style="margin-bottom: 8px">
					<input class="easyui-textbox" style="width: 100%; height: 32px;"
						data-options="label:'邮箱',validType:'email',required:true"
						name="email">
				</div>
				<div style="margin-bottom: 8px">
					<input class="easyui-numberbox" style="width: 100%; height: 32px"
						data-options="label:'学号'" name="studentID">
				</div>
				<div style="margin-bottom: 8px">
					<select class="easyui-combobox" style="width: 100%; height: 32px"
						data-options="label:'性别'" name="gender">
						<option value=""></option>
						<option value="男">男</option>
						<option value="女">女</option>
					</select>
				</div>
				<div style="margin-bottom: 8px">
					<input class="easyui-numberbox" style="width: 100%; height: 32px"
						data-options="label:'年龄',validType:'length[1,3]'" name="age">
				</div>
				<div style="margin-bottom: 8px">
					<input class="easyui-textbox" style="width: 100%; height: 32px"
						data-options="label:'学校'" name="address">
				</div>
				<div style="margin-bottom: 8px">
					<input class="easyui-textbox" style="width: 100%; height: 32px"
						data-options="label:'专业'" name="department">
				</div>
				<div style="margin-bottom: 8px">
					<input class="easyui-numberbox" style="width: 100%; height: 32px"
						data-options="label:'QQ',validType:'length[4,15]'" name="qqNumber">
				</div>
				<div style="margin-bottom: 8px">
					<input class="easyui-numberbox" style="width: 100%; height: 32px"
						data-options="label:'手机',validType:'length[11,11]'" name="mobile">
				</div>
				<div style="margin-bottom: 8px">
					<input class="easyui-numberbox" style="width: 100%; height: 32px"
						data-options="label:'备注'" name="memo">
				</div>
				<div>
					<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
						style="width: 100%; height: 32px; margin-top: 20px;">修改</a>
				</div>
			</form>
		</div>
	</div>

	<div id="editPasswordWindow">
		<div class="" style="width: 280px; padding: 30px 160px">
			<form id="editPasswordForm">
				<input type="hidden" name="userName"> <input type="hidden"
					name="email">
				<div style="margin-bottom: 8px">
					<input class="easyui-textbox" style="width: 100%; height: 32px"
						data-options="label:'新密码',required:true" name="password">
				</div>
				<div>
					<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
						style="width: 100%; height: 32px; margin-top: 20px;">修改</a>
				</div>
			</form>
		</div>
	</div>

	<div id="sendMessageWindow">
		<div class="" style="width: 280px; padding: 30px 160px">
			<form id="sendMessageForm">
				<div style="margin-bottom: 8px">
					<input class="easyui-textbox" style="width: 100%; height: 32px"
						data-options="label:'内容',required:true" name="content">
				</div>
				<div>
					<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
						id="send-message-btn"
						style="width: 100%; height: 32px; margin-top: 20px;">发送</a>
				</div>
			</form>
		</div>
	</div>
</body>
</html>