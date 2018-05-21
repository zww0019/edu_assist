<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<script
	src="${pageContext.request.contextPath}/static/bootstrap-fileinput/js/fileinput.min.js"></script>
<script
	src="${pageContext.request.contextPath}/static/bootstrap-fileinput/js/locales/zh.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/bootstrap-fileinput/css/fileinput.min.css" />
<script
	src="${pageContext.request.contextPath}/static/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script
	src="${pageContext.request.contextPath}/static/bootstrap-datetimepicker/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="${pageContext.request.contextPath}/js/public/captcha.js"></script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script
	src="${pageContext.request.contextPath}/js/teacher/session/semester_main/classess/classes_main.js?t=11111111"></script>
<script type="text/javascript">
	$(function() {
		var pagecontext = $('#PageContext').val();
		//初始化datetimepicker插件
		$('.date').each(function() {
			var time_id = $(this).attr("id");
			$("#" + time_id + "").datetimepicker({
				minView : "month",
				format : "yyyy-mm-dd",
				autoclose : true,
				todayBtn : true,
				language : 'zh-CN',
				pickerPosition : "bottom-left"
			});
		});
		//0.初始化fileinput插件
		//初始化上传excel表格
		$("input[name='excel_file']").each(
				function() {
					var classes_id = $(this).attr("id").substring(4,
							$(this).attr("id").length);
					var oFileInput = new FileInput();
					oFileInput.Init($(this).attr("id"), pagecontext
							+ "/teacher/students", {
						"classes_id" : classes_id
					});
				});

	});
	//初始化fileinput
	var FileInput = function() {
		var oFile = new Object();

		//初始化fileinput控件（第一次初始化）
		oFile.Init = function(ctrlName, uploadUrl, parameter) {
			var control = $('#' + ctrlName);
			//初始化上传控件的样式
			control.fileinput({
				language : 'zh', //设置语言
				uploadUrl : uploadUrl, //上传的地址
				allowedFileExtensions : [ 'xls', 'xlsx' ],//接收的文件后缀
				showUpload : true, //是否显示上传按钮
				browseClass : "btn btn-primary", //按钮样式     
				maxFileCount : 1, //表示允许同时上传的最大文件个数
				enctype : 'multipart/form-data',
				validateInitialCount : true,
				showPreview : false,
				previewFileIcon : "<i class='glyphicon glyphicon-king'></i>",
				previewFileIconSettings : {
					'docx' : '<i class="glyphicon glyphicon-file"></i>',
					'xlsx' : '<i class="glyphicon glyphicon-file"></i>',
					'pptx' : '<i class="glyphicon glyphicon-file"></i>',
					'jpg' : '<i class="glyphicon glyphicon-picture"></i>',
					'pdf' : '<i class="glyphicon glyphicon-file"></i>',
					'zip' : '<i class="glyphicon glyphicon-file"></i>',
				},
				uploadExtraData : function(previewId, index) {

					return parameter;
				},
				msgFilesTooMany : "选择上传的文件数量({n}) 超过允许的最大数值{m}！"
			})
		};
		return oFile;
	};
	//页面加载时请求作业概览显示
	// 	$(function(){
	// 		var url = pagecontext+"/teacher/tasks";
	// 		var 
	// 		$.get()
	// 	})
</script>
<c:forEach var="classes" items="${classes}">
	<div class="panel panel-info">
		<div class="panel-heading">
			<h2 class="panel-title">${session.start_year }-${session.end_year }学年${session.semester }${session.course }---${classes.classes_name }</h2>
		</div>
		<div class="panel-body">
			<div class="row clearfix">
				<div class="col-md-3 column">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">功能列表</h3>
						</div>
						<div class="panel-body">
						<c:if test="${session.status=='1' }">
							<button type="button" class=" btn  btn-block btn-warning disabled"
								data-toggle="modal" data-target="#${classes.classes_id }_import">导入学生</button>
							<button type="button" class="btn  btn-block btn-success disabled"
								data-toggle="modal"
								data-target="#${classes.classes_id }_publishTask">发布作业</button>
								</c:if>
								<c:if test="${session.status=='0' }">
								<button type="button" class=" btn  btn-block btn-warning "
								data-toggle="modal" data-target="#${classes.classes_id }_import">导入学生</button>
							<button type="button" class="btn  btn-block btn-success"
								data-toggle="modal"
								data-target="#${classes.classes_id }_publishTask">发布作业</button>
								</c:if>
							<button type="button" class="btn  btn-block btn-info" onclick="entire_classes('${pageContext.request.contextPath}/teacher/classes','${session.start_year }','${session.end_year }','${session.semester }','${session.course }','${classes.classes_id }','${classes.classes_name }')">进入教学班</button>
						
						</div>
					</div>
				</div>
				<div class="col-md-9 column">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">教学班概览</h3>
						</div>
						<div class="panel-body">
							<label>课程名称：${session.course }</label> <br> <label>主讲人：
								${session.teachername }</label><br> <label>学生人数：<a href="#"
								data-toggle="modal" data-target="#${classes.classes_id }">${classes.student_sum}</a>人
							</label><br>

							<div id="module_taskSimply" class="well well-sm">
								<c:forEach var="task" items="${classes.task_statuses }">
									<label>作业名称：</label>${task.task_name }<br>
									<label>人数：</label>
									<label><a href="#" data-toggle="modal"
										data-target="#${task.task_id }_task_complete">${task.complete_count }</a>/<a
										href="#" data-toggle="modal"
										data-target="#${classes.classes_id }">${classes.student_sum }</a></label>
									<div class="progress">
										<div class="progress-bar progress-bar-success"
											role="progressbar" aria-valuenow="60" aria-valuemin="0"
											aria-valuemax="100"
											style="width: ${task.complete_count/classes.student_sum*100}%;">
											<span class="sr-only">
												${task.complete_count/classes.student_sum*100}% 完成（信息）</span>
										</div>
									</div>
								</c:forEach>
							</div>
						</div>
					</div>
				</div>

			</div>
			<hr>
		</div>
	</div>
	<div class="modal fade" id="${classes.classes_id }" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">教学班名单</h4>
				</div>
				<div class="modal-body">

					<div class="checkbox">
						<c:forEach var="naturalclass" items="${classes.naturalClasses }">
							<label id="checkbox"> <input type="checkbox"
								value="${naturalclass.name }" checked>${naturalclass.name }(<strong><mark>${fn:length(naturalclass.students) }</mark></strong>)
							</label>
						</c:forEach>
					</div>
					<hr>
					<div class=" row pre-scrollable">
						<table class="table table-hover">
							<thead>
								<tr>
									<th>姓名</th>
									<th>学号</th>
									<th>性别</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="naturalClasses"
									items="${classes.naturalClasses }">
									<c:forEach var="student" items="${naturalClasses.students }">
										<tr style="display: visible;"
											id="${fn:replace(fn:replace(naturalClasses.name, '(', '' ) , ')', '' ) }">
											<td>${student.name }</td>
											<td>${student.studentid }</td>
											<td>${student.sex }</td>
										</tr>
									</c:forEach>
								</c:forEach>
							</tbody>

						</table>

					</div>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
	<!-- 导入学生模态框（Modal） -->
	<div class="modal fade" id="${classes.classes_id }_import"
		tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">导入学生</h4>
				</div>
				<div class="modal-body">
					<fieldset disabled>
						<div class='form-group'>
							<label>教学班名称：</label> <input class='form-control'
								name="classes_name" id='classes_name'
								value="${classes.classes_name }" />
						</div>
					</fieldset>
					<div class='form-group'>
						<label>导入学生名单：</label> <input type="file" name="excel_file"
							id="tag_${classes.classes_id }" class="file-loading" />
						<p class="help-block">支持xls和xlsx格式的Excel表格。被导入到系统的学生都可以从学生端登录到作业提交系统，用户名密码为学号</p>
					</div>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
	<!-- 发布作业模态框（Modal） -->
	<div class="modal fade" id="${classes.classes_id }_publishTask"
		tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">发布作业</h4>
				</div>
				<div class="modal-body">
					<fieldset disabled>
						<div class='form-group'>
							<label>教学班名称：</label> <input class='form-control'
								name="classes_name" id='classes_name'
								value="${classes.classes_name }" />
						</div>
					</fieldset>
					<input class='form-control' name="classes_id"
						value="${classes.classes_id }" type="hidden" />
					<div class="form-group">
						<label for="task_name">作业名称：</label> <input class="form-control"
							name="task_name" type="text"
							id="${classes.classes_id }_task_name" placeholder="请输入作业名称">
					</div>
					<div class="form-group">
						<label for="task_content">作业内容：</label>
						<textarea name="task_content"
							id="${classes.classes_id }_task_content" class="form-control"
							rows="3"></textarea>
					</div>
					<div class="form-group">
						<label for="task_finished">截止日期：</label>
						<div class="input-group date" id="${classes.classes_id }_time">
							<input id="${classes.classes_id }_end_date" type='text'
								name="end_date" class="form-control" /> <span
								class="input-group-addon"> <span
								class="glyphicon glyphicon-calendar"></span>
							</span>
						</div>
					</div>
					<div class='form-group'>
						<label for="captcha">验证码：</label> <img
							src="${pageContext.request.contextPath}/public/captcha-image.do"
							id="kaptchaImage" /> <input class="form-control" name="captcha"
							type="text" id="${classes.classes_id }_captcha"
							placeholder="请输入验证码">
						<p class="help-block">如有作业模板，请进入教学班后添加</p>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" id="publish_task" class="btn btn-primary"
						onclick="publish_task('${pageContext.request.contextPath}/teacher/task','${classes.classes_id }','${session.start_year }-${session.end_year }','${session.semester }','${session.course }','${pageContext.request.contextPath}/teacher/classespage')">发布</button>
				</div>
			</div>
			<!-- /.modal-content -->

		</div>
		<!-- /.modal -->
	</div>
	<!-- 已上交/未上交学生名单模态框（Modal） -->
	<c:forEach var="task" items="${classes.task_statuses }">
		<div class="modal fade" id="${task.task_id }_task_complete"
			tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
			aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">上交/未上交名单</h4>
					</div>
					<div class="modal-body">
						<div class="col-md-6 column">
							<div class="panel panel-success">
								<div class="panel-heading">
									<h3 class="panel-title">上交学生名单</h3>
								</div>
								<div class="panel-body">
									<div class=" row pre-scrollable">
										<table class="table table-striped">
											<thead>
												<tr>
													<th>学号</th>
													<th>姓名</th>
												</tr>
											</thead>
											<tbody>

												<c:forEach var="student" items="${task.complete_namelist }">
													<tr>
														<td>${student.studentid }</td>
														<td>${student.studentname }</td>
													</tr>
												</c:forEach>

											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-6 column">
							<div class="panel panel-danger">
								<div class="panel-heading">
									<h3 class="panel-title">未上交学生名单</h3>
								</div>
								<div class="panel-body">
									<div class=" row pre-scrollable">
										<table class="table table-striped">
											<thead>
												<tr>
													<th>学号</th>
													<th>姓名</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="student"
													items="${task.uncomplete_namelist }">
													<tr>
														<td>${student.studentid }</td>
														<td>${student.studentname }</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭
						</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>
	</c:forEach>
</c:forEach>
