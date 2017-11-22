<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%
	//获取请求中的所有cookie，得到cookies数组  
	Cookie[] cookies = request.getCookies();
	String user_id = "";
	String password = "";
	//如果cookies数组不为null，并且它的长度大于0  
	if (cookies != null && cookies.length > 0) {
		//就循环遍历每一条cookie  
		for (Cookie cookie : cookies) {
			if ("user_id".equals(cookie.getName())) {
				user_id = cookie.getValue();
			}
			if ("password".equals(cookie.getName())) {
				password = cookie.getValue();
			}
		}
	}
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>${title}</title>
<!-- css -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/login.css">
<link rel="stylesheet" href="font-awesome/css/font-awesome.min.css">
<!-- js -->
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/alert.js"></script>
</head>
<body>
	<div class="login-content">
		<!-- logo图 -->
		<div class="logo-img">
			<div id="logo">
				<img style="width: 160px;" src="images/logo.png" alt="logo">
			</div>
			<p class="logo-title">检修系统</p>
		</div>
		<!-- 表单 -->
		<div class="login-form-content">
			<form action="#">
				<input id="name" class="form-input" required="true"
					name="admin-name" type="text" placeholder="请输入账号"
					value="<%=user_id%>"></input> <input id="password"
					class="form-input" required="true" name="admin-password"
					type="password" placeholder="请输入密码" value="<%=password%>"></input>
				<div class="form-checkbox-div">
					<input class="form-checkbox" name="admin-rember" type="checkbox"
						checked="checked">记住密码</input>
				</div>
				<div class="form-button-div">
					<button data-login="yes" id="form-button" class="form-button"
						onclick="loginSure()" type="button">登录</button>
				</div>
			</form>
		</div>
		<!-- 日期 -->
		<div class="date-content">
			<div class="date-nowday">${date}</div>
		</div>
	</div>

	<!-- 弹框位置 -->
	<div class="alert-content"></div>
</body>
<script type="text/javascript" src="js/login.js"></script>
</html>