<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html lang="zh-CN">
<style>
.copyright {
	position: relative;
	background: #333333;
	font-size: 13px;
	text-align: center;
	color: #A8A8A8;
	padding: 28px 0;
	border-top: 1px solid #303030;
}

.copyright a {
	color: #A8A8A8;
}

.copyright a:hover {
	color: #909090;
}

.copyright span {
	margin: 0 .5em;
}
</style>

<footer class="main-footer">
	<div class="copyright">
		<div class="container">
			<div class="row">
				<div class="col-sm-12">
					<span>校园二手市场</span>&nbsp;|&nbsp; <span>Designed by
						Changjin·Zhao</span>&nbsp;|&nbsp; <span> <a href="/SHM/about.html"
						target="_blank">关于本站</a></span>
					<!--&nbsp;|&nbsp; <span> <a href="${pageContext.request.contextPath}/uiAction_common_problem.action" target="_blank">常见问题</a> -->
					</span>
				</div>
			</div>
		</div>
	</div>
</footer>
<script>
	function getInfo() {
		var clientHeight = document.body.clientHeight;
		var scrollHeight = document.body.scrollHeight;
		if (clientHeight == scrollHeight) {
			$(".main-footer").attr("style",
					"position: fixed; bottom: 0px; width: 100%;");
		}
	}
	getInfo();
</script>
</html>

