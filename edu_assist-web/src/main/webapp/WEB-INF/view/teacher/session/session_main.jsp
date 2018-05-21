<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<html>
<head>
<script src="${pageContext.request.contextPath}/static/bootstrap-treeview/bootstrap-treeview.min.js"></script>
<script type="text/javascript">
function existCourse(coursename,list){
	var flag = false;
	for(var c=0;c<list.length;c++){
		if(list[c]==coursename){
			flag = true;
		}
	}
	return flag;
}
$(function(){
	
	var pageContext = $('#PageContext').val();
	var def_year=false;
	var def_semester=false;
	var def_course=false;
	$.ajax({
			url:pageContext+"/resources/acadeYearTree",
			success:function(tree){
				$('#tree').treeview({
					data : tree,
					levels : 2,
					showBorder:false
				});
				var year="";
				var semester="";
				var course=""
				var courselist = new Array();
				var courseNum=0;
				for(var i = 0;i<tree.length;i++){
					year+="<option>"+tree[i].text+"</option>";
					for(var j=0;j<tree[i].nodes.length;j++){
						for(var k=0;k<tree[i].nodes[j].nodes.length;k++){
							var temp = tree[i].nodes[j].nodes[k].text;
							if(!existCourse(temp,courselist)){
								course+="<option>"+tree[i].nodes[j].nodes[k].text+"</option>";
								courselist[courseNum]=temp;
								courseNum++;
							}
						}
					}
				}
				$('#academic_year').append(year+"<option id='def_year'>添加</option>");
				$('#course').append(course+"<option id='def_course'>添加</option>");
			}
	});
	$('#teacher_main_down').on("click","#def_year",function(){
		def_year=true;
		$('#year_div').html("<input class='form-control' id='start_year' placeholder='起始年份'></input>-<input class='form-control' id='end_year'placeholder='结束年份'></input>");
	})
	$('#teacher_main_down').on("click","#def_semester",function(){
		def_semester=true;
		$('#semester_div').html("<input class='form-control' id='semester' placeholder='学期'></input>");
	})
	$('#teacher_main_down').on("click","#def_course",function(){
		def_course=true;
		$('#course_div').html("<input class='form-control' id='course' placeholder='课程'></input>");
	})
	var arr = 'sss@vvv'.split('@');
	$('#teacher_main_down').on("click","#submit",function(){
		var start_year;
		var end_year;
		var semester;
		var course;
		if(def_year){
			start_year=$('#start_year').val().trim();
			end_year=$('#end_year').val().trim();
		}else{
			var year = $('#academic_year option:selected').text();
			start_year=year.split('-')[0];
			end_year=year.split('-')[1];
		}
		if(def_semester){
			semester=$('#semester').val().trim();
		}else{
			semester = $('#semester option:selected').text();
		}
		if(def_course){
			course=$('#course').val().trim();
		}else{
			course = $('#course option:selected').text();
		}
		var addSessionUrl=pageContext+"/resources/session";
		var args={"start_year":start_year,"end_year":end_year,"semester":semester,"course":course};
		$.ajax({
			type:"POST",
			url:addSessionUrl,
			data:args,
			statusCode:{500:function(){alert("添加失败");},
						200:function(data){
							if(data=="fail"){
								alert("课期重复！！！");
							}else{
								window.location.href =pageContext+"/teacher/mainpage";
							}
						}
						}
			})
		});
	})
</script>
</head>
<body>
			<div class="row clearfix">
				<div class="col-md-3 column">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">课期列表</h3>
						</div>
						
						<div class="panel-body">
						<p>
							<button id="add_session" type="button" class="btn btn-primary btn-lg btn-block">添加课期</button>
						</p>
						<div class=" row pre-scrollable">
							<div id="tree"></div>
							</div>
						</div>
					</div>
				</div>
				
				<div id="session_right" class="col-md-9 column">
					
				</div>
		</div>
		<div class='modal fade' id='add_session_Model' tabindex='-1' role='dialog' data-keyboard='false' aria-hidden='true'> 
	<div class='modal-dialog'> 
	<div class='modal-content'> 
	<div class='modal-header'> 
	<button type='button' class='close' data-dismiss='modal' aria-hidden='true'> 
	&times; 
	</button> 
	<h4 class='modal-title' id='myModalLabel'> 
	添加一个课期 
	</h4> 
	</div> 
	<div class='modal-body'> 
	<form role='form'> 
	<div class='form-group'>
	<label>学年</label> 
	<div id="year_div">
	<select id='academic_year' class='form-control'> 
	<option >请选择</option>
	</select> 
	</div>
	</div>
	<div class='form-group'>
	<label>学期</label> 
	<div id="semester_div">
	<select id='semester' class='form-control'> 
	<option>第一学期</option>
	<option>第二学期</option>
	</select> 
	</div>
	</div>
	<div class='form-group'>
	<label>课程</label> 
	<div id="course_div">
	<select id='course' class='form-control'>
	<option >请选择</option> 
	</select> 
	</div>
	</div>
	</form> 
	</div> 
	<div class='modal-footer'> 
	<button type='button' class='btn btn-danger' data-dismiss='modal'>取消</button>
	<button id='submit' type='button' class='btn btn-primary'>确定</button> 
	</div> 
	</div><!-- /.modal-content --> 
	</div><!-- /.modal --> 
	</div>
</body>

</html>