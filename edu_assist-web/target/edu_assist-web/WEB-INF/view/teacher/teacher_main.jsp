<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<html>
<head>

<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css">
<script src="${pageContext.request.contextPath}/static/jquery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/static/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/teacher/session/semester_main/semester_main.js"></script>
<script src="${pageContext.request.contextPath}/js/teacher/teacher_page.js"></script>
<script src="${pageContext.request.contextPath}/js/public/captcha.js"></script>
<script src="${pageContext.request.contextPath}/static/jquery-validation/jquery.validate.min.js"></script>
<script src="${pageContext.request.contextPath}/static/jquery-validation/messages_zh.min.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/validate.css">

</head>
<input id="PageContext" type="hidden"
		value="${pageContext.request.contextPath}" />
		
<style type="text/css">
hr{
	color="red";
}
</style>
<body>
	<div class="col-md-12 column">
		<nav class="navbar navbar-default" role="navigation">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target="#example-navbar-collapse">
						<span class="sr-only">切换导航</span> <span class="icon-bar"></span> <span
							class="icon-bar"></span> <span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="#">教师端</a>
				</div>
				<div class="collapse navbar-collapse" id="example-navbar-collapse">
					<ul id="nav" class="nav navbar-nav">
						<li id="session_manage" class="active" ><a href="#">课期管理</a></li>
					</ul>
				</div>
			</div>
		</nav>
	</div>
	<div id="teacher_main_down" class="col-md-12 column">
		
	</div>
</body>
</html>