<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE>
<html lang="zh-CN">
<head>
<jsp:include page="../include/include_head.jsp"></jsp:include>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath }/css/user.css">
<title>校园二手商城 - 消息中心</title>
</head>
<body>
	<jsp:include page="../include/top.jsp"></jsp:include>

	<div class="container">
		<!-- 		<div class="row"> -->
		<!-- 			<div class="wrapper"> -->
		<!-- 				<div class="col-md-12 col-sm-12 col-xs-12"> -->
		<!-- 					<div class="head-img"> -->
		<!-- 						<img -->
		<%-- 							src="${pageContext.request.contextPath }/images/noavatar_small.gif"> --%>
		<!-- 					</div> -->
		<!-- 				</div> -->
		<!-- 			</div> -->
		<!-- 		</div> -->
		<jsp:include page="../include/userTop.jsp"></jsp:include>

		<div class="row">
			<nav class="navbar navbar-default">
				<div class="col-md-12 col-sm-12 col-xs-12">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle collapsed"
							data-toggle="collapse"
							data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
							<span class="icon-bar"></span> <span class="icon-bar"></span> <span
								class="icon-bar"></span>
						</button>
						<a class="navbar-brand"
							href="${pageContext.request.contextPath }/index.jsp">首页&nbsp;&nbsp;&nbsp;</a>
					</div>

					<div class="collapse navbar-collapse"
						id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav">
							<li><a
								href="${pageContext.request.contextPath }/user/userAction_myInfo.action">个人资料</a></li>
							<li><a
								href="${pageContext.request.contextPath }/goods/goodsAction_myPublish.action?page=1&rows=36">我发布的商品</a></li>
							<li><a
								href="${pageContext.request.contextPath }/collection/collectionAction_myCollection.action?page=1&rows=50">我的收藏</a></li>
							<li class="active"><a
								href="${pageContext.request.contextPath }/message/messageAction_myMessage.action?page=1&rows=36">消息中心</a></li>
						</ul>
						<!-- 						<form class="navbar-form" -->
						<%-- 							action="${pageContext.request.contextPath }/goods/goodsAction_search.action" --%>
						<!-- 							method="post"> -->
						<!-- 							<div class="form-group"> -->
						<!-- 								<input type="text" class="form-control input-sm" name="prop1" -->
						<!-- 									placeholder="搜索"> -->
						<!-- 								<button type="submit" class="btn btn-default hidden-xs btn-sm">搜索</button> -->
						<!-- 							</div> -->
						<!-- 						</form> -->
					</div>
				</div>
			</nav>
		</div>
	</div>

	<div class="container">
		<div class="row mybox">
			<div class="detailed-panel">
				<div class="col-md-12 col-sm-12 col-xs-12">
					<blockquote>
						<div class="panel-heading">消息中心</div>
					</blockquote>
					<div class="user-publish-panel-body">
						<div class="table-responsive">
							<table class="table table-hover" id="goods-table">
								<thead>
									<tr>
										<td><strong>状态</strong></td>
										<!-- 	                            <td>内容</td> -->
										<td><strong>时间</strong></td>
										<td><strong>发送人</strong></td>
										<td><strong>消息类型</strong></td>
										<td><strong>操作</strong></td>
									</tr>
								</thead>
								<tbody>
									<s:iterator value="#DataRows" status="status">
										<tr onclick="messageDialog('${content}','${id}','${type}','${toGoods.id}')">
											<s:if test='readFlag=="N"'>
												<td id="unRead"><font color="red">未读</font></td>
											</s:if>
											<s:if test='readFlag=="Y"'>
												<td>已读</td>
											</s:if>
											<%--<td><s:property value='#status.count'/></td> --%>
											<td>${publishTime}</td>
											<td>${fromUser.nickName}</td>
											<s:if test='type=="P"'>
												<td>买家评论</td>
											</s:if>
											<s:if test='type=="H"'>
												<td>用户回复</td>
											</s:if>
											<s:if test='type=="T"'>
												<td>系统通知</td>
											</s:if>
											<td>
												<button onclick="del('${id}')" id="btn-del"
													class="btn btn-default">删除</button>
											</td>
										</tr>
									</s:iterator>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-md-12 col-sm-12 col-xs-12">
			<nav class="pagination" role="navigation">

				<a class="btn btn-danger older-posts" id="prev"
					href="${pageContext.request.contextPath }/message/messageAction_myMessage.action?page=${CurrentPage-1}&rows=36">
					<i class="fa fa-angle-right glyphicon glyphicon-chevron-left"></i>
				</a> <span class="page-number">第 ${CurrentPage} 页 &frasl; 共
					${PageCount} 页</span> <a class="btn btn-danger older-posts" id="next"
					href="${pageContext.request.contextPath }/message/messageAction_myMessage.action?page=${CurrentPage+1}&rows=36">
					<i class="fa fa-angle-right glyphicon glyphicon-chevron-right"></i>
				</a>

			</nav>
		</div>
	</div>

	<div class="modal fade" id="myModal" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">消息内容</h4>
				</div>
				<div class="modal-body">
					<div class="text-area">
						<div class="form-group form-inline">
							<div class="info-item"></div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button class="btn btn-primary" data-dismiss="modal">确定</button>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="../include/footer.jsp"></jsp:include>

	<script>
		$(window).load(
				function() {
					var CurrentPage = "${CurrentPage}";
					var PageCount = "${PageCount}";
					if (CurrentPage >= PageCount) {
						$("#next").attr("class",
								"btn btn-danger older-posts disabled");
					}
					if (CurrentPage <= 1) {
						$("#prev").attr("class",
								"btn btn-danger older-posts disabled");
					}
				})
		function del(id) {
			event.stopPropagation();//阻止事件冒泡
			$(".modal-title").text("提示");
			$(".modal-body").text("确定要删除吗");
			$('#myModal').modal("show");
			$('.modal-footer button').click(
							function() {
								$.ajax({
											type : "post",
											url : "${pageContext.request.contextPath }/message/messageAction_del.action",
											data : {
												"id" : id
											},
											success : function(data) {
												if (data == 1) {
													location.reload();
												} else {
													$(".modal-title")
															.text("提示");
													$(".modal-body").text(
															"操作失败");
													$('#myModal').modal("show");
													$('.modal-footer button').click(
																	function() {
																		location.reload();
																	})
												}
											}
										})
							})
		}
		function messageDialog(content, id, type, goodsId) {
			$(".modal-body").text(content);
			$('.modal-footer').html('');
			if (type == 'P' || type == 'H') {
				$('.modal-footer').append('<a href="javascript:void(0);" onclick="gotolook('+"'"+goodsId+"'"+')" class="btn btn-default" data-dismiss="modal">去看看</a>');
			}
			$('.modal-footer').append('<button class="btn btn-primary" data-dismiss="modal">确定</button>');
			$('#myModal').modal("show");
			$.ajax({
				type : "post",
				url : "${pageContext.request.contextPath }/message/messageAction_read.action",
				data : {
					"id" : id
				},
				success : function(data) {
					if (data == 1) {
						$('.modal-footer button').click(function() {
							location.reload();
						})
					}
				}
			})
		}
		
		$('#myModal').on('hidden.bs.modal', function (e) {
			location.reload();
		})
		
		function gotolook(id){
			document.location ="${pageContext.request.contextPath }/SHM/goods/goodsAction_detailedPage.action?id="+id;
		}
	</script>
	<%-- 	<s:debug></s:debug> --%>
</body>
</html>