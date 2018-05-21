<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script>
	$(function () { $("[data-toggle='tooltip']").tooltip(); });
</script>
<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">${session.start_year}-${session.end_year}学年${session.semester}-${session.course }---${classesname}</h3>
	</div>
	<div class="panel-body">
		<ul id="myTab" class="nav nav-tabs">
			<li class="active"><a href="#home" data-toggle="tab"> 作业 </a></li>

		</ul>
		<div id="myTabContent" class="tab-content">
			<div class="tab-pane fade in active" id="home">
				<br>
				<div class="row clearfix">
					<div class="col-md-12 column">
						<div class="row clearfix">
							<div class="col-md-4 column">

								<div class="panel panel-primary">
									<div class="panel-heading">
										<h3 class="panel-title">作业列表</h3>
									</div>
									<div class="panel-body">

										<nav id="myScrollspy">
											<div class="container-fluid">
												<div class="container-fluid">
													<ul class="nav nav-pills nav-stacked">
														<c:forEach var="task" items="${tasks }">


															<li><a href="#${task.task_id }_scrollpy">${task.task_name }
															</a></li>


														</c:forEach>
													</ul>
												</div>
											</div>
										</nav>

									</div>

								</div>
							</div>
							<div class="col-md-8 column">
								<div class="panel panel-primary">
									<div class="panel-heading">
										<h3 class="panel-title">作业完成情况</h3>
									</div>
									<div class="panel-body">
										<div class=" row pre-scrollable">
											<c:forEach var="task" items="${tasks}">
												<div id="${task.task_id }_scrollpy">
													<h1>作业名称：<a href="#" data-toggle="tooltip" data-placement="bottom" title="${task.task_content }">${task.task_name }</a></h1>
													<c:forEach var="naturalclass"
														items="${task_list[task.task_id] }">

														<h4>行政班级：${naturalclass.name }&nbsp;(${naturalclass.task_statusDTOs[0].complete_count+naturalclass.task_statusDTOs[0].uncomplete_count })</h4>
														<h4>
															上交/未交：<label><a href="#" data-toggle="modal"
																data-target="#${fn:replace(fn:replace(naturalclass.name, '(', '' ) , ')', '' ) }_${task.task_id }_task_complete_by_naturalclass">${naturalclass.task_statusDTOs[0].complete_count }</a>/<a
																href="#" data-toggle="modal"
																data-target="#${fn:replace(fn:replace(naturalclass.name, '(', '' ) , ')', '' ) }_${task.task_id }_task_uncomplete_by_naturalclass">${naturalclass.task_statusDTOs[0].uncomplete_count }</a></label>
														</h4>
														<div class="progress">
															<div class="progress-bar progress-bar-success"
																role="progressbar" aria-valuenow="60" aria-valuemin="0"
																aria-valuemax="100"
																style="width: ${naturalclass.task_statusDTOs[0].complete_count/(naturalclass.task_statusDTOs[0].complete_count+naturalclass.task_statusDTOs[0].uncomplete_count)*100}%;">
																<span class="sr-only">
																	${naturalclass.task_statusDTOs[0].complete_count/(naturalclass.task_statusDTOs[0].complete_count+naturalclass.task_statusDTOs[0].uncomplete_count)*100}%
																	完成（信息）</span>
															</div>
														</div>
													</c:forEach>
												</div>
												<hr>
											</c:forEach>
										</div>
									</div>

								</div>
							</div>

						</div>
					</div>
				</div>
			</div>
			<div class="col-md-12 column">
				<div class="row clearfix">
					<span class="pull-right"><button class="btn btn-danger"
							onclick="getSessionDetilInfo('${pageContext.request.contextPath}/teacher/classespage','${session.start_year}-${session.end_year}','${session.semester}','${session.course }')">退出教学班</button></span>
				</div>
			</div>
		</div>
	</div>
</div>
<c:forEach var="task" items="${tasks}">
	<c:forEach var="naturalclass" items="${task_list[task.task_id] }">
		<!-- 上交学生名单模态框（Modal） -->
		<div class="modal fade"
			id="${fn:replace(fn:replace(naturalclass.name, '(', '' ) , ')', '' ) }_${task.task_id }_task_complete_by_naturalclass"
			tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
			aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">已上交学生名单</h4>
					</div>
					<div class="modal-body">
						<div class=" row pre-scrollable">
							<table class="table table-striped">
								<thead>
									<tr>
										<th>学号</th>
										<th>姓名</th>
									</tr>
								</thead>
								<tbody>

									<c:forEach var="complete_namelist" items="${naturalclass.task_statusDTOs[0].complete_namelist }">
									<tr>
										<td>${complete_namelist.studentid }</td>
										<td>${complete_namelist.studentname }</td>
									</tr>
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
		<!-- 未交学生名单模态框（Modal） -->
		<div class="modal fade"
			id="${fn:replace(fn:replace(naturalclass.name, '(', '' ) , ')', '' ) }_${task.task_id }_task_uncomplete_by_naturalclass"
			tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
			aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">未交学生名单</h4>
					</div>
					<div class="modal-body">
						<div class=" row pre-scrollable">
							<table class="table table-striped">
								<thead>
									<tr>
										<th>学号</th>
										<th>姓名</th>
									</tr>
								</thead>
								<tbody>

									<c:forEach var="uncomplete_namelist" items="${naturalclass.task_statusDTOs[0].uncomplete_namelist }">
									<tr>
										<td>${uncomplete_namelist.studentid }</td>
										<td>${uncomplete_namelist.studentname }</td>
									</tr>
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
	</c:forEach>
</c:forEach>
