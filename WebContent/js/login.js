$(function() {
	$("form").bootstrapValidator({
		message : 'This value is not valid',
		feedbackIcons : {
			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		fields : {
			email : {
				validators : {
					notEmpty : {
						message : '邮箱不能为空'
					},
					emailAddress : {
						message : '邮箱地址格式有误'
					}
				}
			},
			password : {
				validators : {
					notEmpty : {
						message : '密码不能为空'
					}
				}
			}
		}
	});
});