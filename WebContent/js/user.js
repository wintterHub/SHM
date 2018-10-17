$(function() {
	$("#form1").bootstrapValidator(
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
										message : '用户名长度必须在2到18位之间'
									},
									threshold : 2, // 有2字符以上才发送ajax请求
									remote : {
										url : '${pageContext.request.contextPath }/user/userAction_nickNameIsExist.action',// 验证地址
										message : '昵称已存在',// 提示消息
										delay : 500,// 设置0.5秒发送一次ajax
										type : 'POST',// 请求方式
										data : function(validator) {
											return {
												nickName : $('input[name="nickName"]').val(),
												userName : $('input[name="userName"]').val()
											};
										}
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
							age : {
								verbose : false,
								validators : {
//									notEmpty : {
//										message : '年龄不能为空'
//									},
									regexp : {
										regexp : /^[0-9]+$/,
										message : '年龄只能是数字'
									}
								}
							},
							gender : {
								verbose : false,
								validators : {
//									notEmpty : {
//										message : '性别不能为空'
//									},
									regexp : {
										regexp : /^[\u7537\u5973]+$/,
										message : '性别只能是男或女'
									}
								}
							},
							address : {
								verbose : false,
								validators : {
//									notEmpty : {
//										message : '学校不能为空'
//									},
									regexp : {
										regexp : /^[\w\u2E80-\u9FFF]+$/,
										message : '学校不能有特殊字符'
									},
								}
							},
							department : {
								verbose : false,
								validators : {
//									notEmpty : {
//										message : '专业不能为空'
//									},
									regexp : {
										regexp : /^[\w\u2E80-\u9FFF]+$/,
										message : '专业不能有特殊字符'
									},
								}
							},
							studentID : {
								verbose : false,
								validators : {
//									notEmpty : {
//										message : '学号不能为空'
//									},
									regexp : {
										regexp : /^[0-9]+$/,
										message : '学号只能是数字'
									}
								}
							},
							mobile : {
								verbose : false,
								validators : {
//									notEmpty : {
//										message : '手机号不能为空'
//									},
									regexp : {
										regexp : /^1[345678]\d{9}$/,
										message : '手机号不存在'
									}
								}
							},
							qqNumber : {
								verbose : false,
								validators : {
//									notEmpty : {
//										message : 'QQ号不能为空'
//									},
									regexp : {
										regexp : /^[1-9]\d{4,}$/,
										message : 'QQ号不存在'
									}
								}
							}
						}
					});
});