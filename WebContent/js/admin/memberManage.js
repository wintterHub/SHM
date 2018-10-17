function doAdd() {
	$("#addUserForm").form('clear');
	$('#addUserWindow').window("open");
}

function doStart() {
	var rows = $("#grid").datagrid("getSelections");
	if (rows.length == 0) {
		$.messager.alert("提示", "请选择需要启用的会员", "warning");
	} else {
		var ids = '';
		for (var i = 0; i < rows.length; i++) {
			var user = rows[i];
			ids = ids + user.userName + ",";
		}
		url = '${pageContext.request.contextPath}/user/userAdminAction_doStart.action';
		data = {
			"ids" : ids
		};
		ajax(url, data);
	}
}

function doStop() {
	var rows = $("#grid").datagrid("getSelections");
	if (rows.length == 0) {
		$.messager.alert("提示", "请选择需要停用的会员", "warning");
	} else {
		var ids = '';
		for (var i = 0; i < rows.length; i++) {
			var user = rows[i];
			ids = ids + user.userName + ",";
		}
		url = '${pageContext.request.contextPath}/user/userAdminAction_doStop.action';
		data = {
			"ids" : ids
		};
		ajax(url, data);
	}
}

function doPassword() {
	var rows = $("#grid").datagrid("getSelections");
	if (rows.length == 0) {
		$.messager.alert("提示", "请选择需要修改的会员", "warning");
	} else {
		var row = rows[0];
		$('#editPasswordWindow').window("open");
		$("#editPasswordForm").form("load", row);
	}
}

function doSendMessage() {
	var rows = $("#grid").datagrid("getSelections");
	if (rows.length == 0) {
		$.messager.alert("提示", "请选择需要发送消息的会员", "warning");
	} else {
		$('#sendMessageWindow').window("open");
	}
}

function doImport() {
	alert("批量导入");
}

function doOutport() {
	alert("批量导出");
}

function doDblClickRow(rowIndex, rowData) {
	$("#editUserWindow").window("open");
	$("#editUserForm").form("load", rowData);
}

$(function() {
	initDatagrid(
			"${pageContext.request.contextPath}/user/userAdminAction_pageQuery.action",
			'userName')

	initWindow(620, 640, '新建会员', 'addUserWindow');
	initWindow(620, 600, '修改会员信息', 'editUserWindow');
	initWindow(620, 240, '修改会员密码', 'editPasswordWindow');
	initWindow(620, 240, '发送消息', 'sendMessageWindow');

	initSearchbox();

	// 点击保存
	$('#addUserWindow .easyui-linkbutton')
			.click(
					function() {
						var url = "${pageContext.request.contextPath }/user/userAdminAction_save.action";
						var formID = "addUserForm";
						formSubmit(url, formID);
					})

	// 点击修改会员
	$('#editUserWindow .easyui-linkbutton')
			.click(
					function() {
						var url = "${pageContext.request.contextPath }/user/userAdminAction_update.action";
						var formID = "editUserForm";
						formSubmit(url, formID);
					})

	// 点击修改密码
	$('#editPasswordWindow .easyui-linkbutton')
			.click(
					function() {
						var url = "${pageContext.request.contextPath }/user/userAdminAction_updatePassword.action";
						var formID = "editPasswordForm";
						formSubmit(url, formID);
					})

	// 点击发送消息按钮
	$('#send-message-btn')
			.click(
					function() {
						var rows = $("#grid").datagrid("getSelections");
						var ids = '';
						for (var i = 0; i < rows.length; i++) {
							var user = rows[i];
							ids = ids + user.userName + ",";
						}
						url = '${pageContext.request.contextPath}/message/messageAdminAction_sendMessage.action';
						data = $('#sendMessageForm').serialize() + '&ids='
								+ ids;
						ajax(url, data);
					})

});

// 工具栏
var toolbar = [ {
	id : 'button-add',
	text : '新建',
	iconCls : 'icon-add',
	handler : doAdd
}, {
	id : 'button-Start',
	text : '启用',
	iconCls : 'icon-ok',
	handler : doStart
}, {
	id : 'button-Stop',
	text : '停用',
	iconCls : 'icon-cancel',
	handler : doStop
}, {
	id : 'button-Password',
	text : '修改密码',
	iconCls : 'icon-lock',
	handler : doPassword
}, {
	id : 'button-SendMessage',
	text : '发送消息',
	iconCls : 'icon-tip',
	handler : doSendMessage
} ];
// 定义列
var columns = [ [ {
	field : 'userName',
	checkbox : true,
}, {
	sortable : true,
	field : 'userNameCopy',
	title : 'ID',
	width : 320,
	align : 'center'
}, {
	sortable : true,// 是否可排序
	field : 'nickName',
	title : '昵称',
	width : 150,
	align : 'center'
}, {
	sortable : true,
	field : 'studentID',
	title : '学号',
	width : 150,
	align : 'center'
}, {
	sortable : true,
	field : 'email',
	title : '邮箱',
	width : 200,
	align : 'center'
}, {
	sortable : true,
	field : 'gender',
	title : '性别',
	width : 100,
	align : 'center'
}, {
	sortable : true,
	field : 'age',
	title : '年龄',
	width : 100,
	align : 'center'
}, {
	sortable : true,
	field : 'address',
	title : '学校',
	width : 150,
	align : 'center'
}, {
	sortable : true,
	field : 'department',
	title : '专业',
	width : 150,
	align : 'center'
}, {
	sortable : true,
	field : 'qqNumber',
	title : 'QQ',
	width : 150,
	align : 'center'
}, {
	sortable : true,
	field : 'mobile',
	title : '手机',
	width : 150,
	align : 'center'
}, {
	sortable : true,
	field : 'loginTime',
	title : '最后登录时间',
	width : 150,
	align : 'center'
}, {
	sortable : true,
	field : 'lastLoginIP',
	title : '最后登录IP',
	width : 150,
	align : 'center'
}, {
	sortable : true,
	field : 'delFlag',
	title : '账号状态',
	width : 100,
	align : 'center',
	formatter : function(value, row, index) {// 替换状态值，使其更直观
		if (value == 'Y') {
			return "<img src='/SHM/easyui/themes/icons/cancel.png'>";
		} else {
			return "<img src='/SHM/easyui/themes/icons/ok.png'>";
		}
	}
}, {
	sortable : true,
	field : 'memo',
	title : '备注',
	width : 150,
	align : 'center'
}

] ];

// $(function() {
// $('.easyui-layout').attr('style','width: 100%; height: 800px;');
// })

// $(function () {
// $("#button-import").upload({
// action: '${pageContext.request.contextPath}/regionAction_importXls.action',
// name: 'myFile',
// onComlete: function (data) {
// $.messager.alert("提示", "成功", "warning");
// }
// });
// });
