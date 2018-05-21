<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<html>
<head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/validate.css">
<script
	src="${pageContext.request.contextPath}/static/jquery/jquery.min.js"></script>
	<script
	src="${pageContext.request.contextPath}/static/jquery-validation/jquery.validate.min.js"></script>
	<script
	src="${pageContext.request.contextPath}/static/jquery-validation/messages_zh.min.js"></script>
<script
	src="${pageContext.request.contextPath}/static/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/login/login.js"></script>
<script src="${pageContext.request.contextPath}/js/public/captcha.js"></script>
</head>
<body>
	<input id="PageContext" type="hidden"
		value="${pageContext.request.contextPath}" />
	<div id="model">
		<div class='modal fade' id='login_Model' tabindex='-1' role='dialog'
			data-backdrop='static' data-keyboard='false' aria-hidden='true'>
			<div class='modal-dialog'>
			<form id="login_form" role='form'  action="${pageContext.request.contextPath}/main/dologin.do" method="post">
				<div class='modal-content'>
					<div class='modal-content'>
						<div class='modal-header'>
							<button type='button' class='close' data-dismiss='modal'
								aria-hidden='true'>&times;</button>
							<h4 class='modal-title' id='myModalLabel'>作业上传系统</h4>
						</div>
						<div class='modal-body'>
								<div class='form-group'>
									<label>身份</label> <select name="iden" class='form-control'>
										<option>教师</option>
										<option>学生</option>
									</select>
								</div>
								<div class='form-group'>
									<label>用户名</label> <input name="username" class='form-control' id='username' />
								</div>
								<div class='form-group'>
									<label>密码</label> <input name="password" class='form-control' id='password'
										type='password' />
								</div>
								<div class='form-group'>
									<label>验证码</label><img
										src="${pageContext.request.contextPath}/public/captcha-image.do"
										id="kaptchaImage" /> <input name="captcha" class='form-control' id='captcha'
										type='text' />
								</div>
								<div class="from-group">
									<label class="invalid" id="login_message">${login_message}</label>
								</div>
						</div>
						<div class='modal-footer'>
							<button type='submit' class='btn btn-primary'>登录</button>
						</div>
					</div>
					<!-- /.modal-content -->
					
				</div>
				</form>
				<!-- /.modal -->
			</div>
		</div>
	</div>
</body>
</html>