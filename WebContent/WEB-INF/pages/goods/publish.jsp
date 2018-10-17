<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE>
<html lang="zh-CN">
<head>
<jsp:include page="../include/include_head.jsp"></jsp:include>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath }/bootstrap/css/bootstrapValidator.min.css">
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath }/css/publish.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/bootstrap/js/bootstrapValidator.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/bootstrap/js/zh_CN.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery.ocupload-1.1.2.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/publish.js"></script>
<title>发布商品</title>
</head>
<body>
	<!-- 	<button class="btn btn-danger button-import">上传</button> -->
	<!-- 	<div class="image"></div> -->
	<!-- 	<a -->
	<%-- 		href="${pageContext.request.contextPath }/goods/goodsAction_clearImage.action" --%>
	<!-- 		class="btn btn-danger">清除</a> -->

	<jsp:include page="../include/top.jsp"></jsp:include>

	<nav class="main-navigation nav nav-divider">
		<div class="container">
			<div class="row clearfix">
				<div class="col-md-12">
					<div class="navbar-header ">
						<button class="navbar-toggle collapsed" data-toggle="collapse"
							data-target="#main-menu">
							<span class="icon-bar glyphicon glyphicon-th-list"></span>
						</button>
<!-- 						<a class="navbar-brand" href="#"><img -->
<%-- 							src="${pageContext.request.contextPath }/images/logo.png"></a> --%>
					</div>
					<div class="collapse navbar-collapse" id="main-menu">
						<ul class="menu">
							<li role="presentation"><a
								href="${pageContext.request.contextPath }/index.jsp">首页</a></li>
							<li role="presentation"><a
								href="${pageContext.request.contextPath }/goods/goodsAction_categroyPage.action?categroy=1&page=1&rows=36">数码电器</a></li>
							<li role="presentation"><a
								href="${pageContext.request.contextPath }/goods/goodsAction_categroyPage.action?categroy=2&page=1&rows=36">衣服伞帽</a></li>
							<li role="presentation"><a
								href="${pageContext.request.contextPath }/goods/goodsAction_categroyPage.action?categroy=3&page=1&rows=36">图书教材</a></li>
							<li role="presentation"><a
								href="${pageContext.request.contextPath }/goods/goodsAction_categroyPage.action?categroy=4&page=1&rows=36">运功健身</a></li>
							<li role="presentation"><a
								href="${pageContext.request.contextPath }/goods/goodsAction_categroyPage.action?categroy=5&page=1&rows=36">租赁生活</a></li>
							<li role="presentation"><a
								href="${pageContext.request.contextPath }/goods/goodsAction_categroyPage.action?categroy=6&page=1&rows=36">其他</a></li>
							<li>
								<form class="navbar-form">
									<div class="form-group">
										<input type="text" class="form-control input-sm"
											placeholder="搜索">
										<button type="submit" class="btn btn-default hidden-xs btn-sm">搜索</button>
									</div>
								</form>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</nav>

	<section class="content-wrap">
		<div class="container">
			<div class="row">
				<main class="col-md-12 col-sm-12 col-xs-12 main-content">
				<div class="publish-panel">
					<div class="release-title">发布商品</div>

					<div class="upload-wr">
						<div class="panel panel-default">
							<div class="panel-body">
								<label class="form-key"><span>上传图片&nbsp;&nbsp;</span></label> <a
									href="${pageContext.request.contextPath }/goods/goodsAction_goPublish.action"
									type="button" class="btn btn-default btn-sm clear-btn">清空</a>
								<div class="row">
									<div class="col-md-4 col-sm-4 col-xs-4 image1"></div>
									<div class="col-md-4 col-sm-4 col-xs-4 image2"></div>
									<div class="col-md-4 col-sm-4 col-xs-4 image3"></div>
								</div>
								<button type="button"
									class="btn btn-primary btn-sm button-import">上传</button>
							</div>
						</div>
					</div>

					<div class="modal fade" id="myModal" role="dialog">
						<div class="modal-dialog modal-sm">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">&times;</button>
									<h4 class="modal-title">提示</h4>
								</div>
								<div class="modal-body">
									<p>
										<font color="#D9534F"><span
											class="glyphicon glyphicon-exclamation-sign">&nbsp;</span></font>至少上传一张图片
									</p>
								</div>
								<div class="modal-footer">
									<button class="btn btn-primary" data-dismiss="modal">好的</button>
								</div>
							</div>
						</div>
					</div>

					<form id="myForm"
						action="${pageContext.request.contextPath }/goods/goodsAction_publishGoods.action"
						class="form-horizontal" method="post">
						<div class="form-wr">
							<div class="form-must-wr">
								<div class="form-item l goods-title">
									<div class="form-value">
										<div class="form-group form-inline">
										<label class="form-key"><span>商品名称：</span></label>
											<input type="text" class="form-control input-lg " id="title"
												name="title" placeholder="最多25个字" />
										</div>
									</div>
								</div>
								<div class="form-item xl goods-desc">
									<div class="form-value">
										<div class="form-group form-inline">
										<label class="form-key"><span>商品详情：</span></label>
											<textarea name="detailed" class="form-control input-lg "
												id="desc" placeholder="建议填写物品用途、新旧程度、原价等信息，至少15个字"></textarea>
										</div>
									</div>
								</div>
								<div class="form-item l goods-title">
									<div class="form-value">
										<div class="form-group form-inline">
										<label class="form-key"><span>交易地点：</span></label>
											<input type="text" class="form-control input-lg "
												id="trade_place" name="loco" placeholder="宿舍、教学楼、食堂等"
												value="" />
										</div>
									</div>
								</div>
								<div class="form-item m goods-price">
									<div class="form-value">
										<!--<div class="input-group"> -->
										<%--<span class="input-group-addon">￥</span> --%>
										<div class="form-group form-inline">
										<label class="form-key"><span>价&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;格：</span></label>
											<input class="price form-control input-lg" type="text"
												id="price" name="price" placeholder="￥" />
										</div>
										<!--</div> -->
									</div>
								</div>
								<div class="form-item m goods-cat">
									<div class="form-value">
										<div class="form-group form-inline">
										<label class="form-key"><span>分&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;类：</span></label>
											<select class="form-control input-lg" name="categroy">
												<option value="1">数码电器</option>
												<option value="2">衣服伞帽</option>
												<option value="3">图书教材</option>
												<option value="4">运功健身</option>
												<option value="5">租赁生活</option>
												<option value="6">其他</option>
											</select>
										</div>
									</div>
								</div>
								<div class="form-item m goods-discount">
									<div class="form-value">
										<div class="form-group list-inline form-inline">
										<label class="form-key"><span>讲&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;价：</span></label>
											<select class="form-control input-lg" name="chafferFlag">
												<option value="N">不可刀</option>
												<option value="Y">可小刀</option>
											</select>
										</div>
									</div>
								</div>
							</div>
							<div class="form-contact">联系方式（至少选填一项）</div>
							<div class="form-select">
								<div class="form-item m">
									<div class="form-value">
										<div class="form-group form-inline">
										<label class="form-key"><span>手&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机：</span></label>
											<input data-toggle="popover" data-trigger="focus"
												data-content="至少填一个" data-placement="left" type="tel"
												class="form-control input-lg " id="tel" name="mobile"
												value="${loginUser.mobile}" />
										</div>
									</div>
								</div>
								<div class="form-item m">
									<div class="form-value">
										<div class="form-group form-inline">
										<label class="form-key"><span>Q&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Q：</span></label>
											<input data-toggle="popover" data-trigger="focus"
												data-content="至少填一个" data-placement="left" type="text"
												class="form-control input-lg " id="qq" name="qqNumber"
												value="${loginUser.qqNumber}" />
										</div>
									</div>
								</div>
							</div>
							<div class="form-share-wr">
								<input data-toggle="popover" data-trigger="focus"
									data-content="只有同意才能发布" data-placement="left" id="form-share"
									name="share" type="checkbox" checked="on" /> <span>我同意&nbsp;<a
									href="${pageContext.request.contextPath }/uiAction_common_role.action" target="_blank">商品发布规则</a></span>
							</div>
							<input type="hidden" id="school_id" value="1" /> <input
								type="hidden" id="user_school_id" value="358" />
							<button id="btn-submit" class="btn-block btn btn-danger btn-lg"
								type="button">马上发布</button>
						</div>
					</form>
				</div>
				</main>
			</div>
		</div>
	</section>

	<jsp:include page="../include/footer.jsp"></jsp:include>
	
	<script type="text/javascript">
		$(function() {
			$(".button-import").upload({
				action : '${pageContext.request.contextPath }/goods/goodsAction_uploadImg.action',
				name : 'myImage',
				enctype : 'multipart/form-data',
				autoSubmit : true,
				onComplete : function(data) {
					if (data == 0) {
						alert("只能上传3张图片");
					} else if (data == -1) {
						alert("不支持给类型文件");
					} else {
						var obj = eval('(' + data + ')');
						if (obj.index == 3) {
							$(".button-import").attr("style",
									"display:none");
						}
						$(".image" + obj.index + "").append("<img src=" + obj.cutImagePath+ " style='z-index:9999;'/>");
					}
				},
			});
		});
	</script>
<%-- 	<s:debug></s:debug> --%>
</body>

</html>