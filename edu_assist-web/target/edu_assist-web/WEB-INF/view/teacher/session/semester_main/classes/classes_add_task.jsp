<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<script src="${pageContext.request.contextPath}/static/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script src="${pageContext.request.contextPath}/static/bootstrap-datetimepicker/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript">
	$("#time").datetimepicker({
		minView: "month",
		format : "yyyy-mm-dd",
		autoclose : true,
		todayBtn : true,
		language : 'zh-CN',
		pickerPosition : "bottom-left"
	});
</script>
<script src="${pageContext.request.contextPath}/js/public/captcha.js"></script>
<div class="panel panel-info">
	<div class="panel-body">
		<form id="task_form" class="form-horizontal" role="form">
			<div class="form-group form-group-sm">
				<label class="col-sm-4 control-label" for="task_name">作业名称：</label>
				<div class="col-sm-8">
					<input class="form-control" name="task_name" type="text" id="task_name"
						placeholder="请输入作业名称">
				</div>
			</div>
			<div class="form-group form-group-sm">
				<label class="col-sm-4 control-label" for="task_content">作业内容：</label>
				<div class="col-sm-8">
					<textarea name="task_content" id="task_content" class="form-control" rows="3"></textarea>
				</div>
			</div>
			<div class="form-group form-group-sm">
				<label class="col-sm-4 control-label" for="task_finished">截止日期：</label>
				<div class="col-sm-8">
					<div class="input-group date" id="time">
						<input type='text' class="form-control" /> <span
							class="input-group-addon"> <span
							class="glyphicon glyphicon-calendar"></span>
						</span>
					</div>
				</div>
			</div>
			<div class='form-group form-group-sm'>
				<label class="col-sm-4 control-label" for="captcha">验证码：</label>
				<img
					src="${pageContext.request.contextPath}/public/captcha-image.do"
					id="kaptchaImage" /> <input name="captcha" class='form-control' id='captcha'
					type='text' />
				<div class="col-sm-8">
				<input class="form-control" name="captcha" type="text" id="captcha"
						placeholder="请输入验证码">
				</div>
			</div>
			<br>
					<button class="btn btn-success" type="submit">提交</button>
		</form>
	</div>
</div>