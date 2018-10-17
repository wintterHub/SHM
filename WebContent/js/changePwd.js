$(function() {
	$("form").bootstrapValidator({
		message : 'This value is not valid',
		feedbackIcons : {
			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		fields : {
			password : {
				validators : {
					notEmpty : {
						message : '原始密码不能为空'
					}
				}
			},
			prop1 : {
				verbose : false,
				validators : {
					notEmpty : {
						message : '密码不能为空'
					}
				},
				regexp : {
					regexp : /^[\w\u2E80-\u9FFF]+$/,
					message : '不能有特殊字符'
				},
				stringLength : {
					min : 8,
					max : 18,
					message : '长度必须在8到18位之间'
				}
			},
			passwordAgain : {
				verbose : false,
				validators : {
					notEmpty : {
						message : '密码不能为空'
					},
					identical : {
						field : 'prop1',
						message : '两次输入密码不一致'
					}
				}
			}
		}
	});
});