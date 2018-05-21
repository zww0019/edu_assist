<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
	<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<script src="${pageContext.request.contextPath}/js/teacher/session/semester_main/semester_main.js?111"></script>
<input id="PageContext" type="hidden"
		value="${pageContext.request.contextPath}" />
<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">${academic_year.start_year}-${academic_year.end_year}学年${academic_year.semesters[0].semester}</h3>
	</div>
	<div class="panel-body">
		<div class="col-md-12 column">
			<div class="col-md-12 column">
			<div class="panel panel-info">
				<div class="table-responsive">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>课程</th>
								<th>完结状态</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="session" items="${academic_year.semesters[0].sessions}">
								<tr>
									<td>${session.course.course_name}</td>
									<c:if test = "${session.completion_status == '0' }">
									<td><span class="label label-success">未完结</span></td>
									</c:if>
									<c:if test = "${session.completion_status == '1' }">
									<td>
									<span class="label label-default">已完结</span>
									<td>
									</c:if>
									<td>
										<button type="button" onclick="getSessionDetilInfo('${pageContext.request.contextPath}/teacher/classespage','${academic_year.start_year}-${academic_year.end_year}','${academic_year.semesters[0].semester}','${session.course.course_name}')" class="btn btn-primary btn-sm">课期详情</button>
										<button type="button" onclick="complete_session('${pageContext.request.contextPath}/resources/session','${academic_year.start_year}-${academic_year.end_year}','${academic_year.semesters[0].semester}','${session.course.course_name}')" class="btn btn-warning btn-sm" >课期完结</button>
										<button type="button" onclick="deleteSession('${pageContext.request.contextPath}/resources/session','${academic_year.start_year}-${academic_year.end_year}','${academic_year.semesters[0].semester}','${session.course.course_name}')" class="btn btn-danger btn-sm">删除课期</button>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

			</div>
		</div>
		</div>
	</div>
	
</div>
