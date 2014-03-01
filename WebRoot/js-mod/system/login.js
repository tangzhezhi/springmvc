$(function() {
	var options = {
		beforeSubmit : showRequest, //提交前处理 
		success : showResponse, //处理完成 
		resetForm : true,
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
	$("#msg").html('提交成功');
}