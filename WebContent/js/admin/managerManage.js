var loginManagerId = $('#loginManagerId').val();

function doAdd() {
	$("#addManagerForm").form('clear');
	$('#addManagerWindow').window("open");
}

function doStart() {
	var rows = $("#grid").datagrid("getSelections");
	if (rows.length == 0) {
		$.messager.alert("提示", "请选择需要启用的用户", "warning");
	} else {
		var ids = '';
		for (var i = 0; i < rows.length; i++) {
			var manager = rows[i];
			ids = ids + manager.id + ",";
		}
		url = '${pageContext.request.contextPath}/manager/managerAction_doStart.action';
		data = {
			"ids" : ids
		};
		ajax(url, data);
	}
}

function doStop() {
	var rows = $("#grid").datagrid("getSelections");
	if (rows.length == 0) {
		$.messager.alert("提示", "请选择需要停用的用户", "warning");
	} else {
		var ids = '';
		for (var i = 0; i < rows.length; i++) {
			var manager = rows[i];
			ids = ids + manager.id + ",";
		}
		url = '${pageContext.request.contextPath}/manager/managerAction_doStop.action';
		data = {
			"ids" : ids
		};
		ajax(url, data);
	}
}

function doPassword() {
	var rows = $("#grid").datagrid("getSelections");
	if (rows.length == 0) {
		$.messager.alert("提示", "请选择需要修改的用户", "warning");
	} else {
		var row = rows[0];
		$('#editManagerPasswordWindow').window("open");
		$("#editManagerPasswordForm").form("load", row);
	}
}

function doDblClickRow(rowIndex, rowData) {
	if (rowData.isSuperAdmin == "Y" && rowData.id != loginManagerId) {
		$.messager.alert("警告", "超级管理员不可编辑", "warning");
	} else {
		$("#editManagerWindow").window("open");
		$("#editManagerForm").form("load", rowData);
	}
}

$(function() {
	initAdminDatagrid(
			"${pageContext.request.contextPath}/manager/managerAction_pageQuery.action",
			'id', loginManagerId);

	initWindow(620, 430, '新建用户', 'addManagerWindow');
	initWindow(620, 400, '编辑用户', 'editManagerWindow');
	initWindow(620, 240, '修改密码', 'editManagerPasswordWindow');

	initSearchbox();

	// 点击新建保存管理员按钮
	$('#save-manager-btn')
			.click(
					function() {
						var url = "${pageContext.request.contextPath }/manager/managerAction_save.action";
						var formID = "addManagerForm";
						formSubmit(url, formID);
					})

	// 点击编辑保存管理员按钮
	$('#edit-manager-btn')
			.click(
					function() {
						var url = "${pageContext.request.contextPath }/manager/managerAction_update.action";
						var formID = "editManagerForm";
						formSubmit(url, formID);
					})

	// 点击修改密码
	$('#edit-manager-password-btn')
			.click(
					function() {
						var url = "${pageContext.request.contextPath }/manager/managerAction_updatePassword.action";
						var formID = "editManagerPasswordForm";
						formSubmit(url, formID);
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
} ];
// 定义列
var columns = [ [ {
	field : 'id',
	checkbox : true,
}, {
	sortable : true,
	field : 'userName',
	title : '用户名',
	width : 200,
	align : 'center'
}, {
	sortable : true,
	field : 'email',
	title : '邮箱',
	width : 200,
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
	field : 'isSuperAdmin',
	title : '超级管理员',
	width : 100,
	align : 'center',
	formatter : function(value, row, index) {// 替换状态值，使其更直观
		if (value == 'Y') {
			return "<img src='/SHM/easyui/themes/icons/ok.png'>";
		} else {
			return "<img src='/SHM/easyui/themes/icons/cancel.png'>";
		}
	}
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
} ] ];