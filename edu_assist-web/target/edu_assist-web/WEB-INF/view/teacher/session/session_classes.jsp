<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<style type="text/css">
table {
	height: 10px; //
	高度要固定 overflow: scroll;
	//
	超过部分使用滚动条
}
</style>
<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">教学班-数据库原理</h3>
	</div>

	<div class="panel-body">
		<div class="col-md-3 column">
			<div class="panel panel-info">
				<div class="panel-heading text-center">
					<h4 class="panel-title">点名册</h4>
				</div>
				<div class="panel-body ">
					<form class="form-horizontal" role="form">
						<div class="form-group form-group-sm">
							<div class="col-sm-8">
								<input class="form-control" type="text" id="student_name"
									placeholder="输入学生姓名">

							</div>
							<div class="col-sm-4">
								<button type="submit" class="btn btn-sm btn-warning">查找</button>
							</div>
						</div>
					</form>
					<label>总人数：100</label>
					<div class=" row pre-scrollable">
						<table class="table table-hover">
							<thead>
								<tr>
									<th>姓名</th>
									<th>学号</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>张文武</td>
									<td>141404060328</td>
								</tr>
								<tr>
									<td>张文武</td>
									<td>141404060328</td>
								</tr>
								<tr>
									<td>张文武</td>
									<td>141404060328</td>
								</tr>
								<tr>
									<td>张文武是的</td>
									<td>141404060328</td>
								</tr>
								<tr>
									<td>张文武</td>
									<td>141404060328</td>
								</tr>
								<tr>
									<td>张文武</td>
									<td>141404060328</td>
								</tr>
								<tr>
									<td>张文武</td>
									<td>141404060328</td>
								</tr>
								<tr>
									<td>张文武</td>
									<td>141404060328</td>
								</tr>
								<tr>
									<td>张文武</td>
									<td>141404060328</td>
								</tr>
								<tr>
									<td>张文武</td>
									<td>141404060328</td>
								</tr>
								<tr>
									<td>张文武</td>
									<td>141404060328</td>
								</tr>
								<tr>
									<td>张文武</td>
									<td>141404060328</td>
								</tr>
								<tr>
									<td>张文武</td>
									<td>141404060328</td>
								</tr>
								<tr>
									<td>张文武</td>
									<td>141404060328</td>
								</tr>
								<tr>
									<td>张文武</td>
									<td>141404060328</td>
								</tr>
								<tr>
									<td>张文武</td>
									<td>141404060328</td>
								</tr>
								<tr>
									<td>张文武</td>
									<td>141404060328</td>
								</tr>
								<tr>
									<td>张文武</td>
									<td>141404060328</td>
								</tr>
								<tr>
									<td>张文武</td>
									<td>141404060328</td>
								</tr>
								<tr>
									<td>张文武</td>
									<td>141404060328</td>
								</tr>
								<tr>
									<td>张文武</td>
									<td>141404060328</td>
								</tr>
							</tbody>

						</table>

					</div>
				</div>
			</div>
		</div>
		<div class="col-md-9 column">
			<div class="col-md-3 column">
				<div class="panel panel-info">
					<div class="panel-heading text-center">
						<h4 class="panel-title">教学班管理</h4>
					</div>
					<div class="panel-body ">
						<button id="import_namelist" type="button" class="btn btn-primary btn-lg btn-block">导入点名册</button>
						<button id="task_progress" type="button" class="btn btn-primary btn-lg btn-block">作业上交情况</button>
						<button id="add_task" type="button" class="btn btn-primary btn-lg btn-block">添加作业信息</button>
					</div>
				</div>

			</div>
			<div id="session_struct_right_right" class="col-md-9 column">
			
		</div>
		</div>
	</div>
</div>
