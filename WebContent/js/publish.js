$(function() {
	$("#myForm").bootstrapValidator({
		message : 'This value is not valid',
		feedbackIcons : {
			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},

		fields : {
			title : {
				verbose : false,
				validators : {
					notEmpty : {
						message : '商品名称不能为空'
					},
					stringLength : {
						max : 25,
						message : '商品名称不能超过25个字'
					}
				}
			},
			detailed : {
				validators : {
					notEmpty : {
						message : '商品详情不能为空'
					},
					stringLength : {
						min : 15,
						message : '商品详情至少要15 字'
					}
				}
			},
			loco : {
				validators : {
					notEmpty : {
						message : '交易地点不能为空'
					},
					stringLength : {
						max : 25,
						message : '商品名称不能超过25个字'
					}
				}
			},
			price : {
				validators : {
					notEmpty : {
						message : '价格不能为空'
					},
					regexp : {
						regexp : /^[0-9]+$/,
						message : '价格只能是数字'
					},
					stringLength : {
						max : 9,
						message : '商品名称不能超过9位数'
					}
				}
			},
			mobile : {
				validators : {
					regexp : {
						regexp : /^|[0-9]+$/,
						message : '手机只能是数字'
					}
				}
			},
			qqNumber : {
				validators : {
					regexp : {
						regexp : /^|[0-9]+$/,
						message : 'qq只能是数字'
					}
				}
			}
		}
	});

	// $("#myForm").submit(function(ev) {
	// ev.preventDefault();
	// });
	$("#btn-submit").on("click", function() {

		var hasImage = $(".image1").find("img").length;
		if (hasImage == 0) {
			$('#myModal').modal("show");
		}

		var bootstrapValidator = $("#myForm").data('bootstrapValidator');
		bootstrapValidator.validate();
		if (bootstrapValidator.isValid()) {
			var tel = $("#tel").val();
			var qq = $("#qq").val();
			var checked = $("#form-share").prop("checked");
			if (tel == '' && qq == '') {
				$('[data-toggle="popover"]').popover();
				$('#tel').popover('show');
				$('#qq').popover('show');
			} else if (!checked) {
				$('[data-toggle="popover"]').popover();
				$('#form-share').popover('show');
			} else {
				var x = document.getElementById("myForm")
				x.submit();
			}
		} else {
			return;
		}

	});
});