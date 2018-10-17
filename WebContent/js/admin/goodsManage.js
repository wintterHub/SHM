function doAdd() {
	$("#addGoodsForm").form('clear');
	$('#addGoodsWindow').window("open");
}

function doStart() {
	var rows = $("#grid").datagrid("getSelections");
	if (rows.length == 0) {
		$.messager.alert("提示", "请选择需要上架的商品", "warning");
	} else {
		var ids = '';
		for (var i = 0; i < rows.length; i++) {
			var goods = rows[i];
			ids = ids + goods.id + ",";
		}
		url = '${pageContext.request.contextPath}/goods/goodsAdminAction_doStart.action';
		data = {
			"ids" : ids
		};
		ajax(url, data);
	}
}

function doStop() {
	var rows = $("#grid").datagrid("getSelections");
	if (rows.length == 0) {
		$.messager.alert("提示", "请选择需要下架的商品", "warning");
	} else {
		var ids = '';
		for (var i = 0; i < rows.length; i++) {
			var goods = rows[i];
			ids = ids + goods.id + ",";
		}
		url = '${pageContext.request.contextPath}/goods/goodsAdminAction_doStop.action';
		data = {
			"ids" : ids
		};
		ajax(url, data);
	}
}

function doClear(){
	$('#clearImageWindow').window("open");
}

function doImport() {
	alert("批量导入");
}

function doOutport() {
	alert("批量导出");
}

function doDblClickRow(rowIndex, rowData) {
	$("#editGoodsWindow").window("open");
	$("#editGoodsForm").form("load", rowData);
}

$(function() {
	initDatagrid(
			"${pageContext.request.contextPath}/goods/goodsAdminAction_pageQuery.action",
			'id')

	initWindow(620, 570, '发布商品', 'addGoodsWindow');
	initWindow(620, 570, '添加图片', 'addGoodsImgWindow');
	initWindow(620, 620, '编辑商品', 'editGoodsWindow');
	initWindow(620, 570, '编辑图片', 'editGoodsImgWindow');
	initWindow(620, 260, '清理无商品关联的图片', 'clearImageWindow');

	initUserCombogrid('user');
	initUserCombogrid('user1');

	initSearchbox();

	// $('#addGoodsWindow,#editGoodsWindow')
	// .window(
	// {
	// onBeforeClose : function() {
	// var url = '${pageContext.request.contextPath
	// }/goods/goodsAdminAction_clear.action';
	// ajax(url, null);
	// }
	// });

	// 点击下一步
	$('#addGoodsWindow .easyui-linkbutton')
			.click(
					function() {
						var url = "${pageContext.request.contextPath }/goods/goodsAdminAction_saveForm.action";
						var startFormID = "addGoodsForm";
						var nextFormID = "addGoodsImgForm";
						substepSubmit(url, startFormID, nextFormID);
					})
	// 点击完成
	$('#addGoodsImgWindow .ok-button')
			.click(
					function() {
						var url = "${pageContext.request.contextPath }/goods/goodsAdminAction_save.action";
						ajax(url, null);
					})

	// 点击商品图片编辑按钮
	$('#edit-img-btn').click(function() {
		$('#editGoodsImgWindow').window("open");
	})

	$('#imageUpload-complete-btn')
			.click(
					function() {
						var url = "${pageContext.request.contextPath }/goods/goodsAdminAction_checkUpload.action";
						completeUpload(url, null);
					})
	// 保存编辑按钮
	$('#edit-save-btn')
			.click(
					function() {
						var url = "${pageContext.request.contextPath }/goods/goodsAdminAction_updateForm.action";
						formSubmit(url, 'editGoodsForm');
					})
					
	$('#clearOk-btn').click(function(){
			var url = "${pageContext.request.contextPath }/goods/goodsAdminAction_clearImage.action";
			var isValid = $('#clearImageForm').form('validate');
			if(isValid){
				$.messager.alert("提示", "正在清理，请稍后...", "info");
				formSubmit(url, 'clearImageForm');
			}else{
				$.messager.alert("警告", "请按输入框要求填写", "warning");
			}
			
			
	})

	$('.datetime_box .datebox').appendTo('.datagrid-toolbar tr');
	var css = $('.datetime_box .datebox').attr("style");
	$('.datetime_box .datebox').attr("style", css + "margin-left:50px;");

	$('#datebox-btn').appendTo('.datagrid-toolbar tr');

	$('#datebox-btn').click(function() {
		var startTime = $('#start').val();
		var endTime = $('#end').val();
//		alert(startTime + "" + endTime);
		$('#grid').datagrid("reload", {
			"startTime" : startTime,
			"endTime" : endTime,
			"searchSort" : "addTime"
		});
	})

})

// 工具栏
var toolbar = [ {
	id : 'button-add',
	text : '新建',
	iconCls : 'icon-add',
	handler : doAdd
}, {
	id : 'button-Start',
	text : '上架',
	iconCls : 'icon-ok',
	handler : doStart
}, {
	id : 'button-Stop',
	text : '下架',
	iconCls : 'icon-cancel',
	handler : doStop
}, {
	id : 'button-Clear',
	text : '清理',
	iconCls : 'icon-reload',
	handler : doClear
}
// , {
// id : 'button-Import',
// text : '批量导入',
// iconCls : 'icon-redo',
// handler : doImport
// }, {
// id : 'button-Outport',
// text : '批量导出',
// iconCls : 'icon-undo',
// handler : doOutport
// }
];
// 定义列
var columns = [ [ {
	field : 'id',
	checkbox : true,
}, {
	sortable : true,
	field : 'idCopy',
	title : 'ID',
	width : 250,
	align : 'center'
}, {
	sortable : true,
	field : 'title',
	title : '标题',
	width : 200,
	align : 'center'
}, {
	field : 'publishUser',
	title : '发布者',
	width : 100,
	align : 'center'
}, {
	sortable : true,
	field : 'price',
	title : '价格',
	width : 80,
	align : 'center'
}, {
	sortable : true,
	field : 'detailed',
	title : '详细描述',
	width : 250,
	align : 'center'
}, {
	sortable : true,
	field : 'categroy',
	title : '分类',
	width : 100,
	align : 'center',
	formatter : function(value, row, index) {// 替换状态值，使其更直观
		if (value == '1') {
			return "数码电器";
		} else if (value == '2') {
			return "衣服伞帽";
		} else if (value == '3') {
			return "图书教材";
		} else if (value == '4') {
			return "运动健身";
		} else if (value == '5') {
			return "租赁生活";
		} else if (value == '6') {
			return "其他";
		}
	}
}, {
	sortable : true,
	field : 'loco',
	title : '交易地点',
	width : 110,
	align : 'center'
}, {
	sortable : true,
	field : 'chafferFlag',
	title : '可否讲价',
	width : 80,
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
	field : 'viewCount',
	title : '查看次数',
	width : 80,
	align : 'center'
}, {
	sortable : true,
	field : 'collectionCount',
	title : '收藏次数',
	width : 80,
	align : 'center'
}, {
	sortable : true,
	field : 'publishTime',
	title : '发布时间',
	width : 80,
	align : 'center'
}, {
	sortable : true,
	field : 'delFlag',
	title : '上架状态',
	width : 80,
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
	width : 80,
	align : 'center'
} ] ];

function initUserCombogrid(id) {
	$('#' + id)
			.combogrid(
					{
						panelWidth : 350,
						idField : 'userName', // ID字段
						textField : 'nickName', // 显示的字段
						url : "${pageContext.request.contextPath}/user/userAdminAction_pageQuery.action",
						fitColumns : true,
						editable : true,
						pagination : true,// 是否分页
						rownumbers : true,// 序号
						collapsible : false,// 是否可折叠的
						pageSize : 20,
						pageList : [ 20, 50, 100 ],
						method : 'post',
						columns : [ [ {
							field : 'nickName',
							title : '用户名',
							width : 100
						}, {
							field : 'qqNumber',
							title : 'QQ',
							width : 100
						}, {
							field : 'mobile',
							title : '手机',
							width : 100
						} ] ]
					});
}

function completeUpload(url, data) {
	$.ajax({
		type : "post",
		url : url,
		data : data,
		success : function(data) {
			var obj = eval('(' + data + ')');
			if (obj.status) {
				$.messager.alert("提示", obj.message, "info", function() {
					$("#editGoodsImgWindow").window("close");
					if (obj.data != '') {
						$("#_easyui_textbox_input7").val(obj.data);
					}
				});
			} else {
				$.messager.alert("提示", obj.message, "warning");
			}
		}
	})
}

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
