// 回车键按下时
$(window).keydown(function(event) {
	if (event.keyCode == 13) {
		loginSure();
	}
});
// 登录按钮点击
function loginSure() {
	var admin_name = $("#name").val();
	var admin_password = $("#password").val();
	// 判断是否为选择状态
	var admin_rember = $(".form-checkbox").is(':checked');
	// 账号和密码都不为空时
	if (admin_name != "" && admin_password != "") {
		var form_button = document.getElementById('form-button');
		// 获取#form-button的data-login状态值
		var data_login = form_button.dataset.login;
		// 改变状态值
		loginButtonChange("no");
		// 登录
		if (data_login == "yes")
			loginSave(admin_name, admin_password, admin_rember);
	} else {
		if (admin_name == "") {
			addAlert("提醒！", "账号为空，请重新输入", "warning");
		}
		if (admin_password == "") {
			addAlert("提醒！", "密码为空，请重新输入", "warning");
		}
	}
}
//登录函数
function loginSave(admin_name, admin_password, admin_rember) {
	$.ajax({
		type : "POST",
		url : "function/loginDeal",
		data : {
			"username" : admin_name,
			"password" : admin_password,
			"remberPassword" : admin_rember
		},
		dataType : "json",
		success : function(msg) {
			console.log(msg);
			if (msg.result == "success") {
				addAlert("恭喜！", "登录成功", "success");
				// 跳转到首页
				top.location.href="index"; 
			} else if (msg.result == "fail") {
				addAlert("提醒！", "登录失败，请重新登录", "warning");
				// 改变状态值
				loginButtonChange("yes");
			} else if (msg.result == "not") {
				addAlert("提醒！", "账号或密码错误，请重新输入", "warning");
				// 改变状态值
				loginButtonChange("yes");
			}
		},
		error : function(msg) {
			addAlert("error！", "登录失败", "warning");
			// 改变状态值
			loginButtonChange("yes");
			console.log(msg);
		}
	});
}
// 改变登录按钮的状态
function loginButtonChange(type) {

	switch (type) {
	case 'no':
		var form_button = document.getElementById('form-button');
		// 将状态变为no
		form_button.dataset.login = 'no';
		// 将标签内容清空
		$("#form-button").text("");
		$("#form-button").append('<i class="fa fa-spinner fa-pulse"></i>');
		// 改背景
		$("#form-button").css({
			"background" : "#aaa"
		});
		break;
	case 'yes':
		var form_button = document.getElementById('form-button');
		// 将状态变为no
		form_button.dataset.login = 'yes';
		// 将标签内容清空
		$("#form-button").text("");
		$("#form-button").append('登录');
		// 改背景
		$("#form-button").css({
			"background" : "#68C6DE"
		});
		break;
	}

}
