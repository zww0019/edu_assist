<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

	<script
	src="${pageContext.request.contextPath}/js/teacher/session/semester_main/classess/no_classes.js"></script>

<input id="PageContext" type="hidden"
	value="${pageContext.request.contextPath}" />

<div class="panel panel-info">
	<div class="panel-heading">
		<h2 class="panel-title">${session.start_year }-${session.end_year }学年${session.semester }${session.course }</h2>
	</div>
	<div class="panel-body">
		<div class="row clearfix text-center">
			<p>还没有教学班，快去创建吧</p>
			<button id="create_classess" type="button" class="btn btn-primary"
				data-toggle="modal" data-target="#create_classes_modal">创建教学班</button>
		</div>
	</div>
	<div class="modal fade" id="create_classes_modal" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">创建教学班</h4>
				</div>
				<div class="modal-body">
					<form role='form'>
						<fieldset disabled>
							<div class='form-group'>
								<label>学年：</label> <input class='form-control' id='academic_year'
									value="${session.start_year }-${session.end_year }" />
							</div>
							<div class='form-group'>
								<label>学期：</label> <input class='form-control' id='semester'
									value="${session.semester }" />
							</div>
							<div class='form-group'>
								<label>课程：</label> <input class='form-control' id='course'
									value="${session.course }" />
							</div>
						</fieldset>
						<div class='form-group'>
							<label>教学班名称：</label> <input class='form-control'
								id='classes_name' placeholder='请输入教学班名称' />
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button id="create_classes" type="button" class="btn btn-primary">创建</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
</div>