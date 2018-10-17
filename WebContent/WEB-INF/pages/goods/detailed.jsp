<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE>
<html lang="zh-CN">
<head>
<jsp:include page="../include/include_head.jsp"></jsp:include>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath }/css/detailed.css">
<title>商品详情</title>
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
							<div class="title">${title }</div>
							<div class="publish-time">
								<span class="glyphicon glyphicon-eye-open"></span>&nbsp;${viewCount}人浏览过
							</div>
							<div class="params-price">
								<span class="ng-scope">¥</span> <span class="ng-binding">${price }</span>
							</div>
						</div>
						<div class="params-panel">
							<div class="info-item">
								<span class="info-key">交易地点</span><span class="info-value">${loco}</span>
							</div>
							<div class="info-item">
								<span class="info-key">
									卖&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;家</span><span
									class="info-value">
									<s:if test="#session.loginUser==null">${publisher.nickName}</s:if>
									<s:else><a onclick="userInfo('${publisher.userName}')"
									href="javascript:void(0);"> ${publisher.nickName}</a></s:else>
									</span>
							</div>
							<div class="info-item">
								<span class="info-key">讲&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;价</span><span
									class="info-value"> <s:set name="chafferFlag"> ${chafferFlag} </s:set>
									<s:if test='#chafferFlag=="Y"'>可小刀</s:if> <s:else>不可刀</s:else></span>
							</div>
							<div class="info-item">
								<span class="info-key">QQ&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号</span><span
									class="info-value"><s:if test="#session.loginUser==null">登录后可查看</s:if>
									<s:elseif test="%{publisher.qqNumber==''}">暂无QQ号码</s:elseif> <s:else>${publisher.qqNumber}</s:else></span>
							</div>
							<div class="info-item">
								<span class="info-key">手&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机</span><span
									class="info-value"><s:if test="#session.loginUser==null">登录后可查看</s:if>
									<s:elseif test="%{publisher.mobile==''}">暂无手机号码</s:elseif> <s:else>${publisher.mobile}</s:else></span>
							</div>
							<div class="info-item">
								<span class="info-key">发布时间</span><span class="info-value">${publishTime}</span>
							</div>
						</div>
						<div class="footer-panel">
							<s:if test="#isCollection==true">
								<button id="collection" class="btn btn-danger btn-lg"
									disabled="disabled">
									<span class="glyphicon glyphicon-heart-empty"></span>&nbsp;已收藏
								</button>
							</s:if>
							<s:else>
								<button id="collection" class="btn btn-default btn-lg"
									onclick="coll();">
									<span class="glyphicon glyphicon-heart-empty"></span>&nbsp;收藏
								</button>
							</s:else>
						</div>
					</div>
				</div>
			</div>

			<div class="row mybox">
				<div class="detailed-panel">
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="panel-heading">物品描述</div>
						<div class="panel-body">${detailed }</div>
					</div>
				</div>
			</div>

			<div class="row mybox">
				<div class="my-comment">
					<div class="col-md-1 col-sm-1 hidden-xs">
						<div class="user-panel">
							<div class="user-head">
								<s:if test='#session.loginUser.headImagePath!=null'>
									<img src="${loginUser.headImagePath }">
								</s:if>
								<s:else>
									<img
										src="${pageContext.request.contextPath }/images/noavatar_small.gif">
								</s:else>
							</div>
						</div>
					</div>
					<div class="col-md-11 col-sm-11 col-xs-12">
						<div class="text-area">
							<textarea id="textarea1" name="content" placeholder="赶快给卖家留言吧"></textarea>
						</div>
						<button id="btn-publish" class="btn btn-danger">发布</button>
					</div>
				</div>
			</div>

			<div class="row mybox">
				<div class="col-md-12 col-sm-12 col-xs-12">
					<div class="comment-panel">
						<div class="panel-heading">用户评论</div>
						<s:iterator value="messages" var="message">
							<s:if test='#message.type=="P"'>
								<div class="panel-body">
									<a onclick="userInfo('${message.fromUser.userName}')"
										href="javascript:void(0);"> ${message.fromUser.nickName}</a>：<span>${message.content}</span>&nbsp;
									<a href="javascript:void(0);" id="back">回复</a> <span
										id="back-time">&nbsp;&nbsp;[${message.publishTime}]</span><br>
								</div>
							</s:if>
							<s:if test='#message.type=="H"'>
								<div class="panel-body">
									<a onclick="userInfo('${message.fromUser.userName}')"
										href="javascript:void(0);"> ${message.fromUser.nickName}</a>
									回复 <a onclick="userInfo('${message.toUser.userName}')"
										href="javascript:void(0);"> ${message.toUser.nickName}</a>：<span>${message.content}</span>
									<span id="back-time">&nbsp;&nbsp;[${message.publishTime}]</span><br>
								</div>
							</s:if>
						</s:iterator>
					</div>
				</div>
			</div>

		</div>
	</section>

	<div class="modal fade" id="myModal" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">回复</h4>
				</div>
				<div class="modal-body">
					<div class="text-area">
						<textarea id="textarea2" placeholder="想回复些什么..."></textarea>
					</div>
				</div>
				<div class="modal-footer">
					<button class="btn btn-primary" onclick="reply()"
						data-dismiss="modal">确定</button>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="../include/footer.jsp"></jsp:include>

	<script type="text/javascript">
		function userInfo(username) {
			location.href = "${pageContext.request.contextPath }/user/userAction_findUserByName2.action?userName="
					+ username;
		}

		$(function() {
			$('[data-toggle="popover"]').popover()
		})

		$("#btn-publish").click(
						function() {
							var content = $("#textarea1").val();
							var toUserStr = "${publisher.userName}";
							var fromUserStr = "${loginUser.userName}";
							var prop1 = "${id}";//传递GoodsID
							if (fromUserStr == null || fromUserStr == "") {
								window.open("${pageContext.request.contextPath }/user/userAction_goToLogin.action");
							} else if (content == "" || content == null) {
								return;
							} else if (toUserStr == fromUserStr) {
								alert("不能给自己评论");
							} else {
								$.ajax({
									type : "post",
									url : "${pageContext.request.contextPath }/message/messageAction_sendMessage.action",
									data : {
										"content" : content,
										"toUserStr" : toUserStr,
										"fromUserStr" : fromUserStr,
										"type" : "P",
										"prop1" : prop1
									},
									success : function(data) {
										if (data == 1) {
											location.reload();
										}
									}
								});
							}
						});
		$("#back").click(function() {
			var loginUser = "${loginUser.userName}";
			if (loginUser == null || loginUser == "") {
				window.open("${pageContext.request.contextPath }/user/userAction_goToLogin.action");
			}else{
				$(".modal-title").text("回复");
				$('#myModal').modal("show");
			}
		})

		function reply() {
			var fromUserStr = "${message.fromUser.userName}";
			var userName = "${loginUser.userName}";
			var prop1 = "${id}";//传递GoodsID
			var content = $("#textarea2").val();
			if (fromUserStr == userName) {
				alert("不能给自己回复");
			} else if (content == "" || content == null) {
				return;
			} else {
				$.ajax({
							type : "post",
							url : "${pageContext.request.contextPath }/message/messageAction_sendMessage.action",
							data : {
								"content" : content,
								"toUserStr" : fromUserStr,
								"fromUserStr" : userName,
								"type" : "H",
								"prop1" : prop1
							},
							success : function(data) {
								if (data == 1) {
									location.reload();
								}
							}
						});
			}
		}
		function coll() {
			var user = "${loginUser.userName}";
			if (user == null || user == "") {
				window
						.open("${pageContext.request.contextPath}/user/userAction_goToLogin.action");
			} else {
				$.ajax({
							type : "post",
							url : "${pageContext.request.contextPath}/collection/collectionAction_isCollFull.action",
							success : function(data) {
								if (data == 1) {
									$(".modal-title").text("提示");
									$(".modal-body").text("收藏物品超过50件，请删除一些");
									$(".modal-footer button").removeAttr(
											"onclick");
									$('#myModal').modal("show");
								} else {
									var collGoods = "${id}";//传递GoodsID
									$.ajax({
												type : "post",
												url : "${pageContext.request.contextPath}/collection/collectionAction_collection.action",
												data : {
													"collGoods" : collGoods
												},
												success : function(data) {
													if (data == 1) {
														$("#collection")
																.addClass(
																		"btn-danger");
														$("#collection").attr(
																"disabled",
																"disabled");
														$("#collection").text(
																"");
														$("#collection").append(
																		"<span class='glyphicon glyphicon-heart-empty'></span>&nbsp;已收藏");
														$(".modal-footer button").removeAttr(
																		"onclick");
													} else {
														$(".modal-title").text(
																"提示");
														$(".modal-body").text(
																"收藏失败");
														$(".modal-footer button").removeAttr("onclick");
														$('#myModal').modal("show");
													}
												}
											});
								}
							}
						});
			}
		}
	</script>
	<%-- 	<s:debug></s:debug> --%>
</body>
</html>