<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>
	<div class="right-product right-off">
		<section id="layout" ng-app="doc.ui-layout">
		<div ui-layout class="layout-mock">
			<ui-layout options="{ flow : 'column' }"> <!---leftlist--->
			<sidebar class="east-back left-back">
			<ul class="nav nav-pills nav-stacked">
				<li class="active"><a href="#">安全设置</a></li>
				<li><a href="#">基本资料</a></li>
				<li><a href="#">新闻栏目三</a></li>
			</ul>
			</sidebar> <!---rightcontent---->
			<div class="center-back right-back">
				<div class="container-fluid">
					<div class="manage account-manage info-center">
						<div class="page-header">
							<div class="pull-left">
								<h4>安全设置</h4>
							</div>
						</div>
						<dl class="account-basic clearfix">
							<dt class="pull-left">
								<p class="account-head">
									<img src="img/noavatar_middle.gif">
								</p>
							</dt>
							<dd class="pull-left margin-large-left margin-small-top">
								<p class="text-small">
									<span class="show pull-left base-name">账号昵称</span>: <span
										class="margin-left"> <span> <em
											style="color:${nick_name[1]};margin-right:10px;">${nick_name[0]}</em>
											<a href="#" style="color:${nick_name[3]};margin-left:10px;">${nick_name[2]}</a>
									</span>
									</span>
								</p>
								<p class="text-small">
									<span class="show pull-left base-name">姓名</span>: <span
										class="margin-left">${name}</span> <a
										class="margin-left text-main text-underline" href="#">修改</a>
								</p>
								<p class="text-small">
									<span class="show pull-left base-name">唯一编号</span>: <span
										class="margin-left">${user_id}</span>
									<!-- <a class="margin-left text-main text-underline" href="#">立即认证</a> -->
								</p>
								<p class="text-small">
									<span class="show pull-left base-name">身份</span>:<span
										class="margin-left">${role}</span>
								</p>
							</dd>
						</dl>
						<div class="account-basic clearfix">
							<span class="pull-left show text-small">您当前的账号信息完善程度</span>
							<div
								class="progress-bar pull-left margin-large-left margin-large-35">
								<div
									style="background: ${completion_degree[6]} none repeat scroll 0% 0%; width: ${completion_degree[5]}%;"
									data-width="100"></div>
							</div>
							<span class="pull-left show text-small">安全级别: <span
								style="color: ${completion_degree[6]};" class="leval-safe">${completion_degree[7]}</span></span>
						</div>
						<ul class="accound-bund">
							<li class="clearfix"><span class="bund-class">登录密码</span> <span
								class="w45">安全性高的密码可以使账号更安全，建议您定期更换密码，设置一个包含字母，符号或数字中至少两项且长度超过6位的密码。</span>
								<span class="pull-right margin-large-right"> <em
									class="margin-right text-green-deep">已设置</em> | <a href="#"
									class="button-word1 margin-left btn_ajax_open">修改</a>
							</span></li>

							<li class="clearfix"><span class="bund-class">邮箱绑定</span> <span
								class="w45">绑定邮箱可以用于安全验证、找回密码等重要操作</span> <span
								class="pull-right margin-large-right"> <em
									class="margin-right text-green-deep">已设置</em> | <a href="#"
									class="button-word1 margin-left btn_ajax_open">修改</a>
							</span></li>

							<li class="clearfix border-bottom-none"><span
								class="bund-class">邀请链接</span> <span class="w45" id="fe_text">http://t.cn/Edcx4Wt</span>
								<span class="pull-right margin-large-right"> <a
									class="button-word1 margin-left" id="copy_button"
									data-clipboard-target="fe_text" href="#">复制链接</a>
							</span></li>
						</ul>
					</div>
				</div>
			</div>
			</ui-layout>
		</div>
		</section>
	</div>
</body>
</html>