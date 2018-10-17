<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<style>
#back-to-top {
	position: fixed;
	right: 10px;
	bottom: 10px;
	background: rgba(217, 83, 79, 0.6);
	color: #ffffff;
	text-align: center;
	border-radius: 2px;
	z-index: 1;
}

#back-to-top span:hover {
	background: #D9534F;
	transition: all 0.2s ease-in-out;
	-webkit-transition: all 0.2s ease-in-out;
	border-radius: 2px;
}

#back-to-top span {
	display: block;
	width: 30px;
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
</style>

<a href="#" id="back-to-top"><span
	class="fa fa-angle-up glyphicon glyphicon-chevron-up"></span></a>

<script>
$(document).ready(function() {
	$("#back-to-top").hide();
	$(function() {
		$(window).scroll(function() {
			if ($(window).scrollTop() > 500) {
				$("#back-to-top").fadeIn(500);
			} else {
				$("#back-to-top").fadeOut(500);
			}
		});
		$("#back-to-top").click(function() {
			$('body,html').animate({
				scrollTop : 0
			}, 100);
			return false;
		});
	});
});
</script>