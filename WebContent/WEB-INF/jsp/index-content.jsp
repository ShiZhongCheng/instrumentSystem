<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
	<div class="right-product my-index right-full">
		<div class="container-fluid">
			<div class="info-center">

				<!---title----->
				<div class="info-title">
					<div class="pull-left">
						<h4>
							<strong>${name}，</strong>
						</h4>
						<p>欢迎登录检修管理系统！</p>
					</div>
					<div class="time-title pull-right">
						<div class="year-month pull-left">
						    <!-- 星期 -->
							<p id="week-days"></p>
							<!-- 日期到（年月日） -->
							<p id="date-ymd">
								<!-- <span>2016</span>年8月23日 -->
							</p>
						</div>
						<div class="hour-minute pull-right">
						    <!-- 日期（时分秒） -->
							<strong id="datw-hms"></strong>
						</div>
					</div>
					<div class="clearfix"></div>
				</div>

			</div>
		</div>
	</div>
</body>
</html>