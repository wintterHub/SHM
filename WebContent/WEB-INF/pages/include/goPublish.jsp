<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE>
<html lang="zh-CN">
<head>
<style>
#go-publish {
	position: fixed;
/* 	right: 10px; */
/* 	top: 100px; */
	right: 50px;
	bottom: 10px;
	background: rgba(217, 83, 79, 0.6);
	color: #ffffff;
	text-align: center;
	border-radius: 2px;
	z-index: 1;
}

#go-publish span:hover {
	background: #D9534F;
	transition: all 0.2s ease-in-out;
	-webkit-transition: all 0.2s ease-in-out;
	border-radius: 2px;
}

#go-publish span {
	display: block;
	width: 80px;
	height: 30px;
	line-height: 30px;
}

.fa {
	display: inline-block;
	font-size: inherit;
	text-rendering: auto;
	-webkit-font-smoothing: antialiased;
	-moz-osx-font-smoothing: grayscale;
	transform: translate(0, 0);
}

@media screen and (max-width: 768px) {
	#go-publish{
		display: none;
	}
}
</style>
<script>
$(function() {
	$("#go-publish").click(function() {
		var address = "${loginUser.address}";
		var userName = "${loginUser.userName}";
		
		if(userName==""||userName==null){
			window.open("${pageContext.request.contextPath}/user/userAction_goToLogin.action?backUrl=${pageContext.request.contextPath}/goods/goodsAction_goPublish.action");
		}else if(address==""||address==null){
			$('#myModal').modal("show");
		}else{
			window.open("${pageContext.request.contextPath }/goods/goodsAction_goPublish.action");
		}
	})
})

$(function() {
	$("#address1").html('');
	$("#address1").append("<option value=''>--请选择你的省份--</option>");
	$("#address2").html('');
	$("#address2").append("<option value=''>--请选择你的学校--</option>");
	$.getJSON("${pageContext.request.contextPath }/json/school.json", function(data) {
		$.each(data, function(infoIndex, info) {
			$("#address1").append(
					"<option value='" + info['id'] + "'>" + info['name']
							+ "</option>");
		})
	})
})

$(function() {
	$("#address1").change(
		function() {
			$("#address2").html('');
			$("#address2").append("<option value=''>--请选择你的学校--</option>");
			var chooseitem = $("#address1").val();
			$.getJSON("${pageContext.request.contextPath }/json/school.json", function(data) {
				$.each(data, function(infoIndex, info) {
					if (info['id'] == chooseitem) {
						$.each(info['school'], function(infoIndex, info) {
							$("#address2").append(
									"<option value='" + info['id'] + "'>"
											+ info['name'] + "</option>");
						})
					}

				})
			})
		})
})

$(function(){
	$("#btn-submit").click(function(){
		var addressT1 = $('#address1 option:selected').text();
		var addressT2 = $('#address2 option:selected').text();
		var addressV1 = $('#address1 option:selected').val();
		var addressV2 = $('#address2 option:selected').val();
		if(addressV1!=""&&addressV2!=""){
			$.ajax({
				type: "post",
				url: "${pageContext.request.contextPath }/user/userAction_saveSchool.action",
				data: {"address":addressT2},
				success: function(data){
					if(data==1){
						window.location.href="${pageContext.request.contextPath }/goods/goodsAction_goPublish.action";
					}
				}
			})
		}
	})
})

</script>
</head>
<body>
<div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">所在学校<font color="red">(仅需填写一次)</font></h4>
            </div>
            <div class="modal-body">
                <div class="text-area">
					<div class="form-group form-inline">
						<div class="info-item">
							<span class="info-key">学&nbsp;&nbsp;校&nbsp;&nbsp;</span> <select
								required="" class="form-control" name="address" id="address1">
							</select> <select required="" class="form-control" name="address"
								id="address2">
							</select>
						</div>
					</div>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary" id="btn-submit" data-dismiss="modal">好的</button>
            </div>
        </div>
    </div>
</div>

<%-- 	href="${pageContext.request.contextPath }/goods/goodsAction_goPublish.action" --%>
<a
	href="javascript:void(0);"
	id="go-publish"><span class="fa">我要发布</span></a>
</body>
</html>









