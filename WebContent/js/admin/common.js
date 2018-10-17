function formSubmit(url, formID) {
	var isValid = $('#' + formID + '').form('validate');
	if (isValid) {
		// var formdata = new FormData(document.getElementById(formID));
		$.ajax({
			type : "post",
			url : url,
			data : $('#' + formID + '').serialize(),
			// data : formdata,
			success : function(data) {
				var obj = eval('(' + data + ')');
				if (obj.status) {
					$.messager.alert("提示", obj.message, "info", function() {
						location.reload();
					});
				} else {
					$.messager.alert("警告", obj.message, "warning");
				}
			}
		})
	} else {
		$.messager.alert("警告", "请按输入框要求填写", "warning");
	}
}

function substepSubmit(url, startFormID, nextFormID) {
	var isValid = $('#' + startFormID + '').form('validate');
	if (isValid) {
		$.ajax({
			type : "post",
			url : url,
			data : $('#' + startFormID + '').serialize(),
			success : function(data) {
				var obj = eval('(' + data + ')');
				if (obj.status) {
					$("#addGoodsWindow").window("close");
					$("#addGoodsImgWindow").window("open");
				} else {
					$.messager.alert("警告", obj.message, "warning");
				}
			}
		})
	} else {
		$.messager.alert("警告", "请按输入框要求填写", "warning");
	}
}

function ajax(url, ajaxData) {
	$.ajax({
		type : "post",
		url : url,
		data : ajaxData,
		success : function(data) {
			var obj = eval('(' + data + ')');
			if (obj.status) {
				$.messager.alert("提示", obj.message, "info", function() {
					location.reload();
				});
			} else {
				$.messager.alert("警告", obj.message, "warning");
			}
		}
	})
}

function initDatagrid(url, idField) {
	// 先将body隐藏，再显示，不会出现页面刷新效果
	$("body").css({
		visibility : "visible"
	});
	$('#grid').datagrid({
		iconCls : 'icon-forward',
		loadMsg : '拼命加载中...',
		fit : true,
		border : false,
		rownumbers : true,
		striped : true,
		pagination : true,
		nowrap : true,
		fitColumns : true,
		pageSize : 20,
		pageNumber : 1,
		pageList : [ 20, 50, 100 ],
		toolbar : toolbar,
		url : url,
		idField : idField,
		columns : columns,
		onDblClickRow : doDblClickRow,
	});
}

function initAdminDatagrid(url, idField, loginManagerId) {
	// 先将body隐藏，再显示，不会出现页面刷新效果
	$("body").css({
		visibility : "visible"
	});
	$('#grid')
			.datagrid(
					{
						iconCls : 'icon-forward',
						loadMsg : '拼命加载中...',
						fit : true,
						border : false,
						rownumbers : true,
						striped : true,
						pagination : true,
						nowrap : true,
						fitColumns : true,
						pageSize : 20,
						pageNumber : 1,
						pageList : [ 20, 50, 100 ],
						toolbar : toolbar,
						url : url,
						idField : idField,
						columns : columns,
						onDblClickRow : doDblClickRow,
						onLoadSuccess : function(data) {// 加载完毕后获取所有的checkbox遍历
							$("input[type='checkbox']").eq(0).attr('style',
									'display:none;');
							if (data.rows.length > 0) {
								// 循环判断操作为新增的不能选择
								for (var i = 0; i < data.rows.length; i++) {
									// 根据isSuperAdmin让某些行不可选
									if (data.rows[i].isSuperAdmin == "Y"
											&& data.rows[i].id != loginManagerId) {
										$("input[type='checkbox']")[i + 1].disabled = true;
									}
								}
							}
						},
						onClickRow : function(rowIndex, rowData) {
							// 加载完毕后获取所有的checkbox遍历
							$("input[type='checkbox']").each(
									function(index, el) {
										// 如果当前的复选框不可选，则不让其选中
										if (el.disabled == true) {
											$('#grid').datagrid('unselectRow',
													index - 1);
										}
									})
						}
					});
}

function initWindow(width, height, title, windowID) {
	$('#' + windowID + '').window({
		width : width,
		height : height,
		modal : true,
		closed : true,
		title : title,
		collapsible : false,
		minimizable : false,
		maximizable : false,
		resizable : false,
		border : true
	});
}

function initSearchbox() {
	$('#searchbox').searchbox({
		searcher : function(key, sort) {
			$('#grid').datagrid("reload", {
				"key" : key,
				"searchSort" : sort
			});
		},
		menu : '#menu',
		prompt : '搜索'
	});

	$('.searchbox').appendTo('.datagrid-toolbar tr');
	var css = $('.searchbox').attr("style");
	$('.searchbox').attr("style", css + "left:20px;");
}
