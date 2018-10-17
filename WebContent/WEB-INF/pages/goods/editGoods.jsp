<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE>
<html lang="zh-CN">
<head>
<jsp:include page="../include/include_head.jsp"></jsp:include>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath }/css/detailed.css">
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath }/bootstrap/css/bootstrapValidator.min.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/bootstrap/js/bootstrapValidator.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/bootstrap/js/zh_CN.js"></script>
<title>编辑商品</title>

<style type="text/css">
.glyphicon-pencil {
	font-size: 20px;
	color: #777777;
}
</style>
</head>
<body>

	<jsp:include page="../include/top.jsp"></jsp:include>

	<section class="content-wrap">
		<div class="container">
			<div class="row mybox">

				<div class="col-md-6 col-sm-6 col-xs-12">
					<div class="content-left">

						<div id="carousel-example-generic" data-interval="false"
							class="carousel slide" data-ride="carousel">

							<ol class="carousel-indicators">
								<s:iterator value="images" var="image" status="sta">
									<s:if test='#sta.index=="0"'>
										<li data-target="#carousel-example-generic"
											data-slide-to="<s:property value="#sta.index"/>"
											class="active"></li>
									</s:if>
									<s:else>
										<li data-target="#carousel-example-generic"
											data-slide-to="<s:property value="#sta.index"/>"></li>
									</s:else>
								</s:iterator>
							</ol>

							<div class="carousel-inner" role="listbox">
								<s:iterator value="images" var="image" status="sta">
									<s:if test='#sta.index=="0"'>
										<div class="item active">
											<img src='<s:property value="image"/>'>
										</div>
									</s:if>
									<s:else>
										<div class="item">
											<img src='<s:property value="image"/>'>
										</div>
									</s:else>
								</s:iterator>
							</div>

							<a class="carousel-control" href="#carousel-example-generic"
								role="button" data-slide="prev"> <span
								class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
								<span class="sr-only">Previous</span>
							</a> <a class="right_ carousel-control"
								href="#carousel-example-generic" role="button" data-slide="next">
								<span class="glyphicon glyphicon-chevron-right"
								aria-hidden="true"></span> <span class="sr-only">Next</span>
							</a>
						</div>
					</div>
				</div>

				<div class="col-md-6 col-sm-6 col-xs-12">
					<div class="content-right">
						<div class="custom-title">
							<div class="title">${title}&nbsp;<a
									onclick="edit('title','修改商品标题','${id}');"
									href="javascript:void(0);"><span
									class="glyphicon glyphicon-pencil"></span></a>
							</div>
							<div class="publish-time">
								<span class="glyphicon glyphicon-eye-open"></span>&nbsp;${viewCount}人浏览过
							</div>
							<div class="params-price">
								<span class="ng-scope">¥</span> <span class="ng-binding">${price }</span>&nbsp;<a
									onclick="edit('price','修改商品价格','${id}');"
									href="javascript:void(0);"><span
									class="glyphicon glyphicon-pencil"></span></a>
							</div>
						</div>
						<div class="params-panel">
							<div class="info-item">
								<span class="info-key">交易地点</span><span class="info-value">${loco}</span>&nbsp;<a
									onclick="edit('loco','修改商品交易地点','${id}');"
									href="javascript:void(0);"><span
									class="glyphicon glyphicon-pencil"></span></a>
							</div>
							<div class="info-item">
								<span class="info-key">
									卖&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;家</span><span
									class="info-value"> ${publisher.nickName}</span>
							</div>
							<div class="info-item">
								<span class="info-key">讲&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;价</span><span
									class="info-value"> <s:set name="chafferFlag"> ${chafferFlag} </s:set>
									<s:if test='#chafferFlag=="Y"'>可小刀</s:if> <s:else>不可刀</s:else></span>&nbsp;<a
									onclick="editChaffer('${id}');"
									href="javascript:void(0);"><span
									class="glyphicon glyphicon-pencil"></span></a>
							</div>
							<div class="info-item">
								<span class="info-key">QQ&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号</span><span
									class="info-value"><s:if test="#session.loginUser==null">登录后可查看</s:if>
									<s:else>${publisher.qqNumber}</s:else></span>&nbsp;<a
									onclick="goToEditUserInfo();" href="javascript:void(0);"><span
									class="glyphicon glyphicon-pencil"></span></a>
							</div>
							<div class="info-item">
								<span class="info-key">手&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机</span><span
									class="info-value"><s:if test="#session.loginUser==null">登录后可查看</s:if>
									<s:else>${publisher.mobile}</s:else></span>&nbsp;<a
									onclick="goToEditUserInfo();" href="javascript:void(0);"><span
									class="glyphicon glyphicon-pencil"></span></a>
							</div>
							<div class="info-item">
								<span class="info-key">发布时间</span><span class="info-value">${publishTime}</span>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="row mybox">
				<div class="detailed-panel">
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="panel-heading">
							物品描述&nbsp;<a onclick="edit('detailed','修改详细描述','${id}');"
								href="javascript:void(0);"><span
								class="glyphicon glyphicon-pencil"></span></a>
						</div>
						<div class="panel-body">${detailed }</div>
					</div>
				</div>
			</div>
		</div>
	</section>

	<div class="modal fade" id="editGoodsModal" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">修改商品信息</h4>
				</div>
				<div class="modal-body">
					<form class="editGoodsForm">
						<div class="form-group">
							<input type="hidden" value="" name="id"> <input
								type="text" class="form-control" placeholder="请输入修改内容" name=""></input>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button class="btn btn-primary" id="edit-goods-btn">确定</button>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="goToEditUserInfoModal" role="dialog">
		<div class="modal-dialog modal-sx">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">提示信息</h4>
				</div>
				<div class="modal-body">
					<span>是否跳转到个人信息页面修改 </span>
				</div>
				<div class="modal-footer">
					<button class="btn btn-default" data-dismiss="modal">取消</button>
					<button class="btn btn-primary" id="edit-userinfo-btn"
						data-dismiss="modal">确定</button>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="../include/footer.jsp"></jsp:include>

	<script type="text/javascript">
		$(function() {
			$('[data-toggle="popover"]').popover()
		})

		function edit(prop, title, id) {
			$('.editGoodsForm div').attr('class','form-group');
			$('.editGoodsForm .form-group').html('<input type="hidden" value="" name="id"> <input type="text" class="form-control" placeholder="请输入修改内容" name=""></input>');
			$('.modal-title').html(title);
			$('.editGoodsForm input[type="hidden"]').attr('value', id);
			$('.editGoodsForm input[type="text"]').attr('name', prop);
			bootstrapValidate();
			$('#editGoodsModal').modal("show");
		}
		
		function editChaffer(id){
			$('.modal-title').html('修改讲价规则');
			$('.editGoodsForm input[type="hidden"]').attr('value', id);
			$('.editGoodsForm input[type="text"]').hide();
			$('.editGoodsForm .form-group').append('<select class="form-control" name="chafferFlag"><option value="N">不可刀</option><option value="Y">可小刀</option></select>');
			$('#editGoodsModal').modal("show");
		}
		
// 		$(function(){
// 			$('#edit-goods-btn').click(function(){
// 				$.ajax({
// 					type : "post",
// 					url : "${pageContext.request.contextPath }/goods/goodsAction_editGoods.action",
// 					data : $('.editGoodsForm').serialize(),
// 					success : function(data) {
// 						if (data == 1) {
// 							location.reload();
// 						}else{
// 							alert("操作失败");
// 						}
// 					}
// 				})
// 			})
// 		})
		
		function editGoods(){
			$.ajax({
				type : "post",
				url : "${pageContext.request.contextPath }/goods/goodsAction_editGoods.action",
				data : $('.editGoodsForm').serialize(),
				success : function(data) {
					if (data == 1) {
						location.reload();
					}else{
						alert("操作失败");
					}
				}
			})
		}

		function goToEditUserInfo() {
			$('.modal-title').html('提示信息');
			$('#goToEditUserInfoModal').modal("show");
		}

		$(function() {
			$('#edit-userinfo-btn').click(function() {
				window.open("${pageContext.request.contextPath}/user/userAction_myInfo.action");
			})
		})

		function bootstrapValidate(){
			$(".editGoodsForm").bootstrapValidator({
				message : 'This value is not valid',
				feedbackIcons : {
					valid : 'glyphicon glyphicon-ok',
					invalid : 'glyphicon glyphicon-remove',
					validating : 'glyphicon glyphicon-refresh'
				},
			fields : {
				title : {
					verbose : false,
					validators : {
						notEmpty : {
							message : '商品名称不能为空'
						},
						stringLength : {
							max : 25,
							message : '商品名称不能超过25个字'
						}
					}
				},
				detailed : {
					validators : {
						notEmpty : {
							message : '商品详情不能为空'
						},
						stringLength : {
							min : 15,
							message : '商品详情至少要15 字'
						}
					}
				},
				loco : {
					validators : {
						notEmpty : {
							message : '交易地点不能为空'
						},
						stringLength : {
							max : 25,
							message : '商品名称不能超过25个字'
						}
					}
				},
				price : {
					validators : {
						notEmpty : {
							message : '价格不能为空'
						},
						regexp : {
							regexp : /^[0-9]+$/,
							message : '价格只能是数字'
						},
						stringLength : {
							max : 9,
							message : '商品名称不能超过9位数'
						}
					}
				}
			}
			});
		}
		
		$(function() {
			$("#edit-goods-btn").on("click", function() {
				var bootstrapValidator = $(".editGoodsForm").data('bootstrapValidator');
				bootstrapValidator.validate();
				if (bootstrapValidator.isValid()) {
					editGoods();
				} else {
					return;
				}
			});
		});
	</script>
</body>
</html>