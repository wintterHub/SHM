$(function() {
	$("#vcodebtn")
			.click(
					function(e) {
						var email = $("#emailinput").val();
						reg = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
						if (reg.test(email)) {
							$
									.ajax({
										url : "${pageContext.request.contextPath }/user/userAction_sendmail.action",
										type : "POST",
										data : "email="
												+ $("#emailinput").val(),
										success : function(data) {
											if (data == 1) {
												$("#vcodebtn").attr("disabled",
														"disabled");
												Countdown(60, "vcodebtn");// 按钮显示倒计时
											} else {
												alert("发送失败");
											}
										},
										error : function() {
											alert("发送失败");
										}
									})
						}
					});
})

function Countdown(timer, id) {
	if (timer >= 1) {
		$("#" + id + "").text("验证码已发送（" + timer + "）");
		timer -= 1;
		setTimeout(function() {
			Countdown(timer, id);
		}, 1000);
	}
	if (timer == 0) {
		$("#vcodebtn").removeAttr("disabled");
		$("#" + id + "").text("发送邮箱验证码");
	}
}

$(function() {
	$("form")
			.bootstrapValidator(
					{
						message : 'This value is not valid',
						feedbackIcons : {
							valid : 'glyphicon glyphicon-ok',
							invalid : 'glyphicon glyphicon-remove',
							validating : 'glyphicon glyphicon-refresh'
						},
						fields : {
							nickName : {
								verbose : false,
								message : '昵称验证失败',
								validators : {
									notEmpty : {
										message : '昵称不能为空'
									},
									regexp : {
										regexp : /^[\w\u2E80-\u9FFF]+$/,
										message : '不能有特殊字符'
									},
									stringLength : {
										min : 2,
										max : 18,
										message : '长度必须在2到18位之间'
									},
									threshold : 2, // 有2字符以上才发送ajax请求
									remote : {
										url : '${pageContext.request.contextPath }/user/userAction_nickNameIsExist.action',
										message : '用户已存在',// 提示消息
										delay : 500,// 设置1秒发送一次ajax
										type : 'POST'// 请求方式
									}
								}
							},
							email : {
								verbose : false,
								validators : {
									notEmpty : {
										message : '邮箱不能为空'
									},
									emailAddress : {
										message : '邮箱地址格式有误'
									},
									threshold : 5, // 有5字符以上才发送ajax请求
									remote : {
										url : '${pageContext.request.contextPath }/user/userAction_findUserByEmail.action',// 验证地址
										message : '邮箱已存在',// 提示消息
										delay : 500,// 设置1秒发送一次ajax
										type : 'POST'// 请求方式
									}
								}
							},
							password : {
								verbose : false,
								validators : {
									notEmpty : {
										message : '密码不能为空'
									},
									regexp : {
										regexp : /^[\w\u2E80-\u9FFF]+$/,
										message : '不能有特殊字符'
									},
									stringLength : {
										min : 8,
										max : 18,
										message : '长度必须在8到18位之间'
									},
								}
							},
							passwordAgain : {
								verbose : false,
								validators : {
									notEmpty : {
										message : '密码不能为空'
									},
									regexp : {
										regexp : /^[\w\u2E80-\u9FFF]+$/,
										message : '不能有特殊字符'
									},
									stringLength : {
										min : 8,
										max : 18,
										message : '长度必须在8到18位之间'
									},
									identical: {
										field: 'password',
										message: '两次输入密码不一致'
									}
								}
							},
							
							vcode : {
								verbose : false,
								validators : {
									notEmpty : {
										message : '验证码不能为空'
									},
									regexp : {
										regexp : /^[0-9]{4}$/,
										message : '验证码必须为4位数'
									},
									threshold : 4,
									remote : {
										url : '${pageContext.request.contextPath }/user/userAction_checkVcode.action',// 验证地址
										message : '验证码错误',// 提示消息
										delay : 500,// 设置1秒发送一次ajax
										type : 'POST',// 请求方式
										data : function(validator) {
											return {
												email : $('[name="email"]')
														.val(),
												vcode : $('[name="vcode"]')
														.val()
											};
										}
									}
								}
							}
						}
					});
});