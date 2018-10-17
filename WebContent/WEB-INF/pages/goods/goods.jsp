<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE>
<html lang="zh-CN">
<head>
<jsp:include page="../include/include_head.jsp"></jsp:include>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath }/css/home.css">
<title>校园二手商城 - 商品列表</title>
<style type="text/css">
.sort {
	/* 	text-align: center; */
	margin-bottom: 16px;
}

.sort .dropdown {
	flaot: left;
}

.sort .chaffer {
	color: #333;
	background-color: #fff;
	vertical-align: middle;
	border-radius: 2px;
	padding: 8px 12px;
	border: 1px solid #CCCCCC;
	line-height: 1.42857143;
	font-weight: 400;
	font-size: 14px;
}
</style>
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
							<li categroy="1" role="presentation"><a
								href="${pageContext.request.contextPath }/goods/goodsAction_categroyPage.action?categroy=1&page=1&rows=36">数码电器</a></li>
							<li categroy="2" role="presentation"><a
								href="${pageContext.request.contextPath }/goods/goodsAction_categroyPage.action?categroy=2&page=1&rows=36">衣鞋伞帽</a></li>
							<li categroy="3" role="presentation"><a
								href="${pageContext.request.contextPath }/goods/goodsAction_categroyPage.action?categroy=3&page=1&rows=36">图书教材</a></li>
							<li categroy="4" role="presentation"><a
								href="${pageContext.request.contextPath }/goods/goodsAction_categroyPage.action?categroy=4&page=1&rows=36">运功健身</a></li>
							<li categroy="5" role="presentation"><a
								href="${pageContext.request.contextPath }/goods/goodsAction_categroyPage.action?categroy=5&page=1&rows=36">租赁生活</a></li>
							<li categroy="6" role="presentation"><a
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
						<span id="header-title">物尽其用 换你所需</span>
					</div>
				</div>
			</div>
		</div>
	</header>

	<div class="container">
		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
				<nav class="navbar navbar-default">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle collapsed"
							data-toggle="collapse"
							data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
							<span class="icon-bar"></span> <span class="icon-bar"></span> <span
								class="icon-bar"></span>
						</button>
						<a class="navbar-brand">&nbsp;&nbsp;排序方式&nbsp;&nbsp;</a>
					</div>
					<div class="collapse navbar-collapse"
						id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav">
							<li class="dropdown"><a href="javascript:void(0);"
								class="dropdown-toggle" data-toggle="dropdown">价格&nbsp;<span
									class="caret"></span>
							</a>
								<ul class="dropdown-menu">
									<li><a
										href="${pageContext.request.contextPath }/goods/goodsAction_categroyPage.action?categroy=${Categroy}&page=1&rows=36&sort=price&order=asc">从低到高<span
											class="glyphicon glyphicon-arrow-up"></span></a></li>
									<li><a
										href="${pageContext.request.contextPath }/goods/goodsAction_categroyPage.action?categroy=${Categroy}&page=1&rows=36&sort=price&order=desc">从高到低<span
											class="glyphicon glyphicon-arrow-down"></span></a></li>
								</ul></li>
							<li class="dropdown"><a href="javascript:void(0);"
								class="dropdown-toggle" data-toggle="dropdown">浏览次数&nbsp;<span
									class="caret"></span>
							</a>
								<ul class="dropdown-menu">
									<li><a
										href="${pageContext.request.contextPath }/goods/goodsAction_categroyPage.action?categroy=${Categroy}&page=1&rows=36&sort=viewCount&order=asc">从低到高<span
											class="glyphicon glyphicon-arrow-up"></span></a></li>
									<li><a
										href="${pageContext.request.contextPath }/goods/goodsAction_categroyPage.action?categroy=${Categroy}&page=1&rows=36&sort=viewCount&order=desc">从高到低<span
											class="glyphicon glyphicon-arrow-down"></span></a></li>
								</ul></li>
							<li class="dropdown"><a href="javascript:void(0);"
								class="dropdown-toggle" data-toggle="dropdown">收藏次数&nbsp;<span
									class="caret"></span>
							</a>
								<ul class="dropdown-menu">
									<li><a
										href="${pageContext.request.contextPath }/goods/goodsAction_categroyPage.action?categroy=${Categroy}&page=1&rows=36&sort=collectionCount&order=asc">从低到高<span
											class="glyphicon glyphicon-arrow-up"></span></a></li>
									<li><a
										href="${pageContext.request.contextPath }/goods/goodsAction_categroyPage.action?categroy=${Categroy}&page=1&rows=36&sort=collectionCount&order=desc">从高到低<span
											class="glyphicon glyphicon-arrow-down"></span></a></li>
								</ul></li>
							<li class="dropdown"><a href="javascript:void(0);"
								class="dropdown-toggle" data-toggle="dropdown">发布日期&nbsp;<span
									class="caret"></span>
							</a>
								<ul class="dropdown-menu">
									<li><a
										href="${pageContext.request.contextPath }/goods/goodsAction_categroyPage.action?categroy=${Categroy}&page=1&rows=36&sort=addTime&order=asc">从低到高<span
											class="glyphicon glyphicon-arrow-up"></span></a></li>
									<li><a
										href="${pageContext.request.contextPath }/goods/goodsAction_categroyPage.action?categroy=${Categroy}&page=1&rows=36&sort=addTime&order=desc">从高到低<span
											class="glyphicon glyphicon-arrow-down"></span></a></li>
								</ul></li>
						</ul>
					</div>
				</nav>
			</div>
		</div>
	</div>

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
								${viewCount} 人浏览过
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
							href="${pageContext.request.contextPath }/goods/goodsAction_categroyPage.action?categroy=${Categroy}&page=${CurrentPage-1}&rows=36">
							<i class="fa fa-angle-right glyphicon glyphicon-chevron-left"></i>
						</a> <span class="page-number">第 ${CurrentPage} 页 &frasl; 共
							${PageCount} 页</span> <a class="btn btn-danger older-posts" id="next"
							href="${pageContext.request.contextPath }/goods/goodsAction_categroyPage.action?categroy=${Categroy}&page=${CurrentPage+1}&rows=36">
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
		$(function() {
			var categroy = "${Categroy}";
			if (categroy != null) {
				$("[categroy=" + categroy + "]").attr("class", "nav-current");
			}
		})

		$(window).load(
				function() {
					var CurrentPage = '${CurrentPage}';
					var PageCount = '${PageCount}';
					if (CurrentPage >= PageCount) {
						$("#next").attr("class",
								"btn btn-danger older-posts disabled");
					}
					if (CurrentPage <= 1) {
						$("#prev").attr("class",
								"btn btn-danger older-posts disabled");
					}
				})

		window.onbeforeunload = function() {
			var scrollPos;
			if (typeof window.pageYOffset != 'undefined') {
				scrollPos = window.pageYOffset;
			} else if (typeof document.compatMode != 'undefined'
					&& document.compatMode != 'BackCompat') {
				scrollPos = document.documentElement.scrollTop;
			} else if (typeof document.body != 'undefined') {
				scrollPos = document.body.scrollTop;
			}
			document.cookie = "scrollTop=" + scrollPos; // 存储滚动条位置到cookies中
		}
		window.onload = function() {
			if (document.cookie.match(/scrollTop=([^;]+)(;|$)/) != null) {
				var arr = document.cookie.match(/scrollTop=([^;]+)(;|$)/); // cookies中不为空，则读取滚动条位置
				document.documentElement.scrollTop = parseInt(arr[1]);
				document.body.scrollTop = parseInt(arr[1]);
			}
		}
	</script>
</body>
</html>