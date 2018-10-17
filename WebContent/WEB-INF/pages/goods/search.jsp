<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE>
<html lang="zh-CN">
<head>
<jsp:include page="../include/include_head.jsp"></jsp:include>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath }/css/home.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/background.js"></script>
<title>搜索结果</title>
</head>
<body>
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
								<form class="navbar-form"
									action="${pageContext.request.contextPath }/goods/goodsAction_search.action?page=1&rows=36"
									method="post">
									<div class="form-group">
										<input type="text" class="form-control input-sm" name="prop1"
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

	<header class="main-header">
		<div class="container">
			<div class="row">
				<div class="col-md-12 col-sm-12 col-xs-12">
					<div class="head-img">
						<a href=""> <img src="">

						</a>
					</div>
				</div>
			</div>
		</div>
	</header>

	<section class="content-wrap">

		<div class="container">
			<div class="row">
				<s:iterator value="#DataRows" status="vs">
					<main class="col-md-3 col-sm-6 col-xs-12 main-content">
					<article
						class="post tag-about-ghost tag-ghost-in-depth tag-zhu-shou-han-shu">
						<div class="post-head">
							<a 
								href="${pageContext.request.contextPath }/goods/goodsAction_detailedPage.action?id=${id}"><img 
								 id="goods-image" src="${images[0] }"></a>
						</div>

						<div class="post-content">
							<div class="post-title">
								<a
									href="${pageContext.request.contextPath }/goods/goodsAction_detailedPage.action?id=${id}">
									<h4 class="post-title">${title}</h4>
								</a>
							</div>
							<div class="post-price">
								<span class="ng-scope">¥</span> <span class="ng-binding">${price}</span>
							</div>
						</div>
						<div class="post-footer">
							<div class="publishTime">
								<span>${publishTime}发布 </span>
							</div>
							<div class="viewCount">
								<span class="glyphicon glyphicon-eye-open"></span> &nbsp;
								${viewCount}
								人浏览过
							</div>
						</div>

					</article>
					<br>
					</main>
				</s:iterator>
			</div>

			<div class="row">
				<div class="col-md-12 col-sm-12 col-xs-12">
					<nav class="pagination" role="navigation">

						<a class="btn btn-danger older-posts" id="prev"
							href="${pageContext.request.contextPath }/goods/goodsAction_search.action?page=${CurrentPage-1}&row=36">
							<i class="fa fa-angle-right glyphicon glyphicon-chevron-left"></i>
						</a> <span class="page-number">第 ${CurrentPage} 页 &frasl; 共
							${PageCount} 页</span> <a class="btn btn-danger older-posts" id="next"
							href="${pageContext.request.contextPath }/goods/goodsAction_search.action?page=${CurrentPage+1}&row=36">
							<i class="fa fa-angle-right glyphicon glyphicon-chevron-right"></i>
						</a>

					</nav>
				</div>
			</div>

		</div>

	</section>

	<jsp:include page="../include/footer.jsp"></jsp:include>

	<jsp:include page="../include/backToTop.jsp"></jsp:include>

	<jsp:include page="../include/goPublish.jsp"></jsp:include>

<%-- 	<s:debug></s:debug> --%>

	<script type="text/javascript"
		src="${pageContext.request.contextPath }/js/home.js"></script>

	<script>
		$(window).load(function() {
			var CurrentPage = "${CurrentPage}";
			var PageCount = "${PageCount}";
			if (CurrentPage >= PageCount) {
				$("#next").attr("class", "btn btn-danger older-posts disabled");
			}
			if (CurrentPage <= 1) {
				$("#prev").attr("class", "btn btn-danger older-posts disabled");
			}
		})
	</script>

</body>
</html>