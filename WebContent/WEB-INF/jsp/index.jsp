<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>首页</title>
<!-- 加载资源 -->
<link href="bootstrap-3.3.5-dist/css/bootstrap.min.css" title=""
	rel="stylesheet" />
<link title="" href="css/style.css" rel="stylesheet" type="text/css" />
<link title="blue" href="css/dermadefault.css" rel="stylesheet"
	type="text/css" />
<link title="green" href="css/dermagreen.css" rel="stylesheet"
	type="text/css" disabled="disabled" />
<link title="orange" href="css/dermaorange.css" rel="stylesheet"
	type="text/css" disabled="disabled" />
<link href="css/templatecss.css" rel="stylesheet" title=""
	type="text/css" />
<script src="script/jquery-1.11.1.min.js" type="text/javascript"></script>
<script src="script/jquery.cookie.js" type="text/javascript"></script>
<script src="bootstrap-3.3.5-dist/js/bootstrap.min.js"
	type="text/javascript"></script>
<!-- 引入basic.js -->
<script src="js/basic.js" type="text/javascript"></script>
<!-- 引入index.js -->
<script src="js/index.js" type="text/javascript"></script>
</head>
<body>
    <!-- 包含头部 -->
    <%@include file="nav-header.jsp" %>
	<div class="down-main">
		<!-- 包含左侧 -->
		<%@include file="nav-left.jsp" %>
		<!-- 包含内容 -->
		<%@include file="index-content.jsp" %>
	</div>
	
</body>
</html>