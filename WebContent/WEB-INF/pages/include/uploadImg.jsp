<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link
	href="${pageContext.request.contextPath}/js/uploadify/uploadify.css"
	rel="stylesheet" type="text/css" />
<script
	src="${pageContext.request.contextPath}/js/uploadify/jquery.uploadify.min.js"
	type="text/javascript"></script>

<style>
.easyui-linkbutton {
	margin: 8px;
}

.upload-body {
	padding: 50px 110px;
}

.upload-body img {
	vertical-align: middle;
}
</style>

<script type="text/javascript">
	var inputId;
	$(function() {
		inputId = '${param.inputId}';
	});
	$(function() {
		$('#' + inputId)
				.uploadify(
						{
							'debug' : false, //开启调试  
							'auto' : false, //是否自动上传     
							'swf' : '${pageContext.request.contextPath}/js/uploadify/uploadify.swf', //引入uploadify.swf    
							'uploader' : '${pageContext.request.contextPath}/goods/goodsAdminAction_uploadImg.action',//请求路径    
							'queueID' : '${param.fileQueue}',//队列id,用来展示上传进度的    
							'width' : '75', //按钮宽度    
							'height' : '24', //按钮高度  
							'queueSizeLimit' : 3, //同时上传文件的个数    
							'uploadLimit' : 3,//最大上传文件数量
							'fileTypeDesc' : '图片文件', //可选择文件类型说明  
							'fileTypeExts' : '*.jpg;*.gif;*.png;*.bmp;*.jpeg;', //控制可上传文件的扩展名    
							'multi' : true, //允许多文件上传    
							'buttonText' : '选择图片',//按钮上的文字    
							'fileSizeLimit' : '5MB', //设置单个文件大小限制     
							'fileObjName' : 'goodsImgs', //<input type="file"/>的name    
							'method' : 'post',
							'removeCompleted' : false,//上传完成后自动删除队列    
							'onFallback' : function() {
								$.messager.alert("提示",
										"您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试。",
										"warning");
							},
							'onQueueComplete' : function(data) {//所有文件上传完成    
								$.messager.alert("提示", "上传成功!", "info");
							},
							'overrideEvents' : [ 'onDialogClose',
									'onUploadSuccess', 'onUploadError',
									'onSelectError' ],
							'onSelect' : uploadify_onSelect,
							'onSelectError' : uploadify_onSelectError,
							'onUploadError' : uploadify_onUploadError,
						});
	});

	var uploadify_onSelectError = function(file, errorCode, errorMsg) {
		var msgText = "上传失败,";
		switch (errorCode) {
		case SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED:
			msgText += "每次最多上传 " + this.settings.queueSizeLimit + "个文件";
			break;
		case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:
			msgText += "文件大小超过限制( " + this.settings.fileSizeLimit + " )";
			break;
		case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:
			msgText += "文件大小为0";
			break;
		case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE:
			msgText += "文件格式不正确，仅支持 " + this.settings.fileTypeExts;
			break;
		default:
			msgText += "错误代码：" + errorCode + "\n" + errorMsg;
		}
		alert(msgText);
	};

	var uploadify_onUploadError = function(file, errorCode, errorMsg,
			errorString) {
		// 手工取消不弹出提示
		if (errorCode == SWFUpload.UPLOAD_ERROR.FILE_CANCELLED
				|| errorCode == SWFUpload.UPLOAD_ERROR.UPLOAD_STOPPED) {
			return;
		}
		var msgText = "上传失败,";
		switch (errorCode) {
		case SWFUpload.UPLOAD_ERROR.HTTP_ERROR:
			msgText += "HTTP 错误\n" + errorMsg;
			break;
		case SWFUpload.UPLOAD_ERROR.MISSING_UPLOAD_URL:
			msgText += "上传文件丢失，请重新上传";
			break;
		case SWFUpload.UPLOAD_ERROR.IO_ERROR:
			msgText += "IO错误";
			break;
		case SWFUpload.UPLOAD_ERROR.SECURITY_ERROR:
			msgText += "安全性错误\n" + errorMsg;
			break;
		case SWFUpload.UPLOAD_ERROR.UPLOAD_LIMIT_EXCEEDED:
			msgText += "每次最多上传 " + this.settings.uploadLimit + "个";
			break;
		case SWFUpload.UPLOAD_ERROR.UPLOAD_FAILED:
			msgText += errorMsg;
			break;
		case SWFUpload.UPLOAD_ERROR.SPECIFIED_FILE_ID_NOT_FOUND:
			msgText += "找不到指定文件，请重新操作";
			break;
		case SWFUpload.UPLOAD_ERROR.FILE_VALIDATION_FAILED:
			msgText += "参数错误";
			break;
		default:
			msgText += "文件:" + file.name + "\n错误码:" + errorCode + "\n"
					+ errorMsg + "\n" + errorString;
		}
		alert(msgText);
		return parameters;
	}

	var uploadify_onSelect = function() {

	};

	var uploadify_onUploadSuccess = function(file, data, response) {
		alert(file.name + "\n\n" + response + "\n\n" + data);
	};

	// 	$(function() {
	// 		$('#cancel-btn').click(function() {
	// 							var url = '${pageContext.request.contextPath }/goods/goodsAdminAction_cancelFirstImg.action';
	// 							ajax(url, null);
	// 		});
	// 	});
</script>

<div class="upload-body">
	<%--用来作为文件队列区域--%>
	<input type="file" name="${param.inputId}" id="${param.inputId}" />
	<div id="${param.fileQueue}"></div>
	<p>
		<a class="easyui-linkbutton"
			href="javascript:$('#${param.inputId}').uploadify('upload','*')"><img
			src='/SHM/easyui/themes/icons/ok.png' style="">上传</a> <a
			class="easyui-linkbutton" id="cancel-btn"
			href="javascript:$('#${param.inputId}').uploadify('cancel')"><img
			src='/SHM/easyui/themes/icons/cancel.png' style="">取消</a>
	</p>
</div>
