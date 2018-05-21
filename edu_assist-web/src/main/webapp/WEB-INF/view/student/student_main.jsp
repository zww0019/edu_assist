<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<html>
<head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css">
<script
	src="${pageContext.request.contextPath}/static/jquery/jquery.min.js"></script>
<script
	src="${pageContext.request.contextPath}/static/bootstrap/js/bootstrap.min.js"></script>
<script
	src="${pageContext.request.contextPath}/js/teacher/session/semester_main/semester_main.js"></script>
<script
	src="${pageContext.request.contextPath}/js/teacher/teacher_page.js"></script>
<script src="${pageContext.request.contextPath}/js/public/captcha.js"></script>
<script
	src="${pageContext.request.contextPath}/static/jquery-validation/jquery.validate.min.js"></script>
<script
	src="${pageContext.request.contextPath}/static/jquery-validation/messages_zh.min.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/validate.css">
	<script
	src="${pageContext.request.contextPath}/static/bootstrap-fileinput/js/fileinput.min.js"></script>
<script
	src="${pageContext.request.contextPath}/static/bootstrap-fileinput/js/locales/zh.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/bootstrap-fileinput/css/fileinput.min.css" />
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
$(function(){
	var pagecontext = $('#PageContext').val();
	$("input[name='rar_or_zip_file']").each(
			function() {
				var file_name = $(this).attr("title");
				var task_id = $(this).attr("id").substring(4,
						$(this).attr("id").length);
				var oFileInput = new FileInput();
				oFileInput.Init($(this).attr("id"), pagecontext
						+ "/student/upTask",task_id)});

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
			allowedFileExtensions : [ 'rar', 'zip' ],//接收的文件后缀
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
				
				var args={"task_id":parameter}
				return args;
			},
			msgFilesTooMany : "选择上传的文件数量({n}) 超过允许的最大数值{m}！"
		}).on("'fileerror'", function(event, data, previewId, index) {
			alert(data);
		})
	};
	return oFile;
	};

</script>
</head>
<body>
<input id="PageContext" type="hidden"
		value="${pageContext.request.contextPath}" />
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<nav class="navbar navbar-default" role="navigation">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle" data-toggle="collapse"
							data-target="#bs-example-navbar-collapse-1">
							<span class="sr-only">Toggle navigation</span><span
								class="icon-bar"></span><span class="icon-bar"></span><span
								class="icon-bar"></span>
						</button>
						<a class="navbar-brand" href="#">学生端<span
							class="glyphicon glyphicon-th-large"></span></a>
					</div>


					<ul class="nav navbar-nav navbar-right">
						<li><a href="#">退出<span
								class="glyphicon glyphicon-remove"></span></a></li>

					</ul>


				</nav>
			</div>
			<div class="col-md-4 column">


				<div class="panel panel-default">
					<div class="panel-body">
						<label>学号：${student.studentid }</label><br> <label>姓名：${student.name }</label><br>
						<label>班级：${student.classa }</label><br>
					</div>
				</div>

			</div>
			<div class="col-md-8 column">
				<c:forEach var ="academic_year" items="${academic_years}">
				<c:forEach var = "classes" items="${academic_year.semesterDTOs[0].sessionDTOs[0].classesDTOs }">
				<table class="table table-condensed table-bordered table-hover">
				<c:if test="${academic_year.semesterDTOs[0].sessionDTOs[0].status == '0'}">
					<caption>${academic_year.start_year }-${academic_year.end_year }学年${academic_year.semesterDTOs[0].name }---${academic_year.semesterDTOs[0].sessionDTOs[0].coursename }---【<span class="label label-success">未完结</span>】---${classes.classesname }</caption>
					</c:if>
					<c:if test="${academic_year.semesterDTOs[0].sessionDTOs[0].status == '1'}">
					<caption>${academic_year.start_year }-${academic_year.end_year }学年${academic_year.semesterDTOs[0].name }---${academic_year.semesterDTOs[0].sessionDTOs[0].coursename }---【<span class="label label-default">已完结</span>】---${classes.classesname }</caption>
					</c:if>
					<thead>
						<tr>
							<th>作业号</th>
							<th>作业名</th>
							<th>截止日期</th>
							<th>状态</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="task" items="${classes.taskDTOs }">
						<tr class="success">
							<td>${task.task_id }</td>
							<td>${task.task_name }</td>
							<td>${task.end_date }</td>
							<c:if test="${task.status=='0' }">
							<td><span class="label label-danger">未交</span></td>
							</c:if>
							<c:if test="${task.status=='1' }">
							<td><span class="label label-success">已交</span></td>
							</c:if>
							<td>
							<c:if test="${task.status=='0' }">
							<button data-toggle="modal" data-target="#${task.task_id }_show" class="btn btn-success btn-sm">上交</button>
							</c:if>
							<c:if test="${task.status=='1' }">
							<button data-toggle="modal" data-target="#${task.task_id }_show" class="btn btn-success btn-sm disabled">上交</button>
							</c:if>
							</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
				<c:forEach var="task" items="${classes.taskDTOs }">
				<div class="modal fade" id="${task.task_id }_show" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">&times;</button>
								<h4 class="modal-title" id="myModalLabel">${task.task_name }</h4>
							</div>
							<div class="modal-body">
								<label>作业内容：${task.task_content }</label> <br>
								<label>截止日期 ：${task.end_date }</label><br>
								<label>上传附件：</label><br>
								<input type="file" name="rar_or_zip_file"
							id="tag_${task.task_id }" class="file-loading" />
						<p class="help-block">支持rar和zip格式的压缩文件。</p>
								
							</div>
							<div class="modal-footer">

								<button type="button" class="btn btn-primary">提交</button>

							</div>
						</div>
						<!-- /.modal-content -->
					</div>
					<!-- /.modal -->
				</div>
				</c:forEach>
				</c:forEach>
				</c:forEach>
				
			</div>
		</div>
	</div>
</body>

</html>