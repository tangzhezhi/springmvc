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
	if (userName == "") {
		$("#msg").html("姓名不能为空！");
		return false;
	}

	var userPwd = $("#userPwd").val();
	if (userPwd == "") {
		$("#msg").html("年龄不能为空！");
		return false;
	}
	$("#msg").html("正在提交...");

	return true;
}

function showResponse(responseText, statusText) {
	$("#msg").html('提交成功');
}