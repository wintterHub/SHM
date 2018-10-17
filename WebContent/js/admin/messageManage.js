function doDelete() {
	var rows = $("#grid").datagrid("getSelections");
	if (rows.length == 0) {
		$.messager.alert("提示", "请选择需要删除的评论", "warning");
	} else {
		$('#delMessageWindow').window("open");
	}
}

function doDblClickRow(rowIndex, rowData) {
	$("#messageWindow").window("open");
	$("#messageForm").form("load", rowData);
}

$(function() {
	initDatagrid(
			"${pageContext.request.contextPath}/message/messageAdminAction_pageQuery.action",
			'id');

	initWindow(620, 400, '查看评论', 'messageWindow');
	initWindow(500, 200, '删除评论', 'delMessageWindow');

	initSearchbox();

	$('#del-btn')
			.click(
					function() {
						var rows = $("#grid").datagrid("getSelections");
						var ids = '';
						for (var i = 0; i < rows.length; i++) {
							var messages = rows[i];
							ids = ids + messages.id + ",";
						}
						url = '${pageContext.request.contextPath}/message/messageAdminAction_doDelete.action';
						data = {
							"ids" : ids
						};
						ajax(url, data);
					});

	$('#cancel-btn').click(function() {
		$('#delMessageWindow').window('close');
	});

})

// 工具栏
var toolbar = [ {
	id : 'button-Start',
	text : '删除',
	iconCls : 'icon-cancel',
	handler : doDelete
} ];
// 定义列
var columns = [ [ {
	field : 'id',
	checkbox : true,
}, {
	sortable : true,
	field : 'content',
	title : '内容',
	width : 300,
	align : 'center'
}, {
	sortable : true,
	field : 'type',
	title : '类型',
	width : 100,
	align : 'center',
	formatter : function(value, row, index) {// 替换状态值，使其更直观
		if (value == 'H') {
			return "回复";
		} else if (value == 'P') {
			return "评论";
		}
	}
}, {
	field : 'toUserName',
	title : '接收者',
	width : 100,
	align : 'center'
}, {
	field : 'fromUserName',
	title : '发送者',
	width : 100,
	align : 'center'
}, {
	field : 'toGoodsId',
	title : '所属商品ID',
	width : 100,
	align : 'center',
}, {
	sortable : true,
	field : 'publishTime',
	title : '发表时间',
	width : 100,
	align : 'center'
} ] ];