<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/admin/goodsManage.js"></script>
<title>商品管理</title>
</head>
<body class="easyui-layout">
	<div data-options="region:'center'" id="center-panel">
		<table id="grid"></table>
	</div>

	<input id="searchbox"
		style="width: 250px; height: 26px; margin-right: 500px;">
	<div id="menu" style="width: 120px;">
		<div data-options="name:'title'">&nbsp;标&nbsp;题</div>
		<div data-options="name:'publisher'">&nbsp;发&nbsp;布&nbsp;者ID</div>
		<div data-options="name:'price'">&nbsp;价&nbsp;格</div>
		<div data-options="name:'detailed'">&nbsp;详&nbsp;细&nbsp;描&nbsp;述</div>
	</div>

	<div class="datetime_box">
		<input type="text" class="easyui-datetimebox" prompt="起始日期" id="start" />
		<input type="text" class="easyui-datetimebox" prompt="结束日期" id="end" />
		<a href="javascript:void(0);" class="easyui-linkbutton"
			id="datebox-btn"
			style="width: 40px; height: 24px; text-align: center;">搜索</a>
	</div>

	<div id="addGoodsWindow">
		<div class="" style="width: 280px; padding: 30px 160px">
			<form id="addGoodsForm">
				<div style="margin-bottom: 8px">
					<select id="user" class="easyui-combogrid"
						style="width: 100%; height: 32px"
						data-options="label:'发布人',required:true" name="publishUser">
					</select>
				</div>
				<div style="margin-bottom: 8px">
					<input class="easyui-textbox" style="width: 100%; height: 32px"
						data-options="label:'标题',required:true" name="title">
				</div>
				<div style="margin-bottom: 8px">
					<input class="easyui-numberbox" style="width: 100%; height: 32px"
						data-options="label:'价格',required:true" name="price">
				</div>
				<div style="margin-bottom: 8px">
					<input class="easyui-textbox" style="width: 100%; height: 32px"
						data-options="label:'详细描述',required:true" name="detailed">
				</div>
				<div style="margin-bottom: 8px">
					<select class="easyui-combobox" style="width: 100%; height: 32px"
						data-options="label:'分类',required:true" name="categroy">
						<option value=""></option>
						<option value="1">数码电器</option>
						<option value="2">衣鞋伞帽</option>
						<option value="3">图书教材</option>
						<option value="4">运功健身</option>
						<option value="5">租赁生活</option>
						<option value="6">其他</option>
					</select>
				</div>
				<div style="margin-bottom: 8px">
					<input class="easyui-textbox" style="width: 100%; height: 32px"
						data-options="label:'交易地点',required:true" name="loco">
				</div>
				<div style="margin-bottom: 8px">
					<select class="easyui-combobox" style="width: 100%; height: 32px"
						data-options="label:'可否讲价',required:true" name="chafferFlag">
						<option value=""></option>
						<option value="Y">可以</option>
						<option value="N">不可以</option>
					</select>
				</div>
				<div style="margin-bottom: 8px">
					<input class="easyui-numberbox" style="width: 100%; height: 32px"
						data-options="label:'QQ',validType:'length[4,15]'" name="qqNumber">
				</div>
				<div style="margin-bottom: 8px">
					<input class="easyui-numberbox" style="width: 100%; height: 32px"
						data-options="label:'手机',validType:'length[11,11]'" name="mobile">
				</div>
				<div>
					<a href="javascript:void(0);" class="easyui-linkbutton"
						iconCls="icon-ok"
						style="width: 100%; height: 32px; margin-top: 20px;">下一步</a>
				</div>
			</form>
		</div>
	</div>

	<div id="addGoodsImgWindow">
		<div class="" style="text-align: center;">
			<jsp:include page="/WEB-INF/pages/include/uploadImg.jsp">
				<jsp:param value="uploadify1" name="inputId" />
				<jsp:param value="fileQueue1" name="fileQueue" />
			</jsp:include>
			<a href="javascript:void(0);" class="easyui-linkbutton ok-button"
				iconCls="icon-ok"
				style="width: 60%; height: 32px; margin-top: 20px;">完成</a>
		</div>
	</div>

	<div id="editGoodsWindow">
		<div class="" style="width: 280px; padding: 30px 160px">
			<form id="editGoodsForm">
				<input type="hidden" name="id"> <input type="hidden"
					name="publishUserId">
				<div style="margin-bottom: 8px">
					<input class="easyui-textbox" style="width: 100%; height: 32px"
						data-options="label:'ID'" name="idCopy" readonly disabled>
				</div>
				<div style="margin-bottom: 8px">
					<select id="user1" class="easyui-combogrid"
						style="width: 100%; height: 32px"
						data-options="label:'发布人',required:true" name="publishUser">
					</select>
				</div>
				<div style="margin-bottom: 8px">
					<input class="easyui-textbox" style="width: 100%; height: 32px"
						data-options="label:'标题',required:true" name="title">
				</div>
				<div style="margin-bottom: 8px">
					<input class="easyui-numberbox" style="width: 100%; height: 32px"
						data-options="label:'价格',required:true" name="price">
				</div>
				<div style="margin-bottom: 8px">
					<input class="easyui-textbox" style="width: 100%; height: 32px"
						data-options="label:'详细描述',required:true" name="detailed">
				</div>
				<div style="margin-bottom: 8px">
					<select class="easyui-combobox" style="width: 100%; height: 32px"
						data-options="label:'分类',required:true" name="categroy">
						<option value=""></option>
						<option value="1">数码电器</option>
						<option value="2">衣服伞帽</option>
						<option value="3">图书教材</option>
						<option value="4">运功健身</option>
						<option value="5">租赁生活</option>
						<option value="6">其他</option>
					</select>
				</div>
				<div style="margin-bottom: 8px">
					<input class="easyui-textbox" style="width: 100%; height: 32px"
						data-options="label:'交易地点',required:true" name="loco">
				</div>
				<div style="margin-bottom: 8px">
					<select class="easyui-combobox" style="width: 100%; height: 32px"
						data-options="label:'可否讲价',required:true" name="chafferFlag">
						<option value=""></option>
						<option value="Y">可以</option>
						<option value="N">不可以</option>
					</select>
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
					<input class="easyui-textbox" style="width: 80%; height: 32px"
						data-options="label:'图片'" name="imagePath" readonly> <a
						href="javascript:void(0);" class="easyui-linkbutton"
						id="edit-img-btn" style="width: 20%; height: 32px; float: right;">编辑</a>
				</div>
				<div class="edit-next-btn">
					<a href="javascript:void(0);" class="easyui-linkbutton"
						iconCls="icon-ok" id="edit-save-btn"
						style="width: 100%; height: 32px; margin-top: 20px;">保存</a>
				</div>
			</form>
		</div>
	</div>

	<div id="editGoodsImgWindow">
		<div class="" style="text-align: center;">
			<jsp:include page="/WEB-INF/pages/include/uploadImg.jsp">
				<jsp:param value="uploadify2" name="inputId" />
				<jsp:param value="fileQueue2" name="fileQueue" />
			</jsp:include>
			<a href="javascript:void(0);" class="easyui-linkbutton ok-button"
				iconCls="icon-ok" id="imageUpload-complete-btn"
				style="width: 60%; height: 32px; margin-top: 20px;">完成</a>
		</div>
	</div>

	<div id="clearImageWindow">
		<form id="clearImageForm">
			<div class="" style="width: 280px; padding: 30px 160px">
				<div style="margin-bottom: 8px">
					<input type="text" class="easyui-datebox" prompt="" id=""
						style="width: 100%; height: 32px" name="startClearDate"
						data-options="label:'起始时间',required:true" />
				</div>
				<div style="margin-bottom: 8px">
					<input type="text" class="easyui-datebox" prompt="" id=""
						style="width: 100%; height: 32px" name="endClearDate"
						data-options="label:'结束时间',required:true" />
				</div>
				<a href="javascript:void(0);" class="easyui-linkbutton ok-button"
					iconCls="icon-ok" id="clearOk-btn"
					style="width: 100%; height: 32px; margin-top: 20px;">立即清理</a>
			</div>
		</form>
	</div>



</body>
</html>