$(function() {
	
	$('#loginForm').bootstrapValidator({
        message: 'This value is not valid',
        fields: {       
            userName: {
		  		message: 'The username is not valid',
                validators: {
                    notEmpty: {
                        message: '用户名不能为空'
                    },
                    emailAddress: {
                        message: '请输入一个有效的邮箱地址'
                    }
                }
            }
    	}
	  });
	
	
	var options = {
		beforeSubmit : showRequest, //提交前处理 
		success : showResponse, //处理完成 
		clearForm: true,
		resetForm : true,
		timeout:   6000,
		dataType : 'json'
	};
	
	
	$('#loginForm').submit(function() {
		$(this).ajaxSubmit(options);
	});
});

function showRequest(formData, jqForm, options) {
	var userName = $("#userName").val();
	$("#userPwd").val($.md5($("#userPwd").val()));
	return true;
}

function showResponse(responseText, statusText) {
	 $('#userPwd').val("");
}