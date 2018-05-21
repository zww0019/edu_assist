$(function(){
	
	var pageContext = $('#PageContext').val();
	//课期管理按钮点击事件
	$('#session_manage').on("click",function(){
		$('#teacher_main_down').load(pageContext+"/teacher/sessionpage");
	})
	$('#nav li').click(function(){
		$(this).addClass("active");
	})
	var activeItem = $('.active').text()
	if(activeItem=="课期管理"){
		$('#teacher_main_down').load(pageContext+"/teacher/sessionpage");
	}
	//添加课期按钮点击事件
	$('#teacher_main_down').on("click","#add_session",function(){
		$('#add_session_Model').modal("show");
	})
	//获得被选中的节点
	$('#teacher_main_down').on('nodeSelected' ,"#tree", function(event, data) {
		var parentNode = $('#tree').treeview('getParent', data);
		var rootNode = $('#tree').treeview('getParent', parentNode);
		//三级节点
		if(rootNode.nodeId != undefined){
			var academic_year=rootNode.text;
			var semester = parentNode.text;
			var coursename = data.text;
			getSessionDetilInfo(pageContext+"/teacher/classespage",academic_year,semester,coursename);
		}
		//二级节点
		if(parentNode.nodeId!=undefined && rootNode.nodeId==undefined){
			var academic_year=$('#tree').treeview('getParent', data).text.split('-');
			var semester = data.text;
			var url=pageContext+"/teacher/"+academic_year[0]+"/"+academic_year[1]+"/"+semester+"/sessions";
			var args ={"start_year":academic_year[0],"end_year":academic_year[1],"semester":semester};
			$.get(url,args,function(data){
				$('#session_right').html(data);
			})
			
		}
		//一级节点
		if(parentNode.nodeId==undefined){
			var academic_year =data.text.split('-');
			var url = pageContext+"/teacher/"+academic_year[0]+"/"+academic_year[1]+"/sessions";
			$.get(url,function(data){
				$('#session_right').html(data);
			})
		}
	});
	//点击进入教学班时
	$('#teacher_main_down').on('click',"#login_classes",function(){
		var classes_name = $(this).parent().parent().children().first().text().trim();
		
		$('#session_right').load("session_classes.jsp");
	})
	//点击作业上交情况时
	$('#teacher_main_down').on('click',"#task_progress",function(){
		$('#session_struct_right_right').load("classes_task_progress.jsp");
	})
	//导入点名册时
	$('#teacher_main_down').on('click',"#import_namelist",function(){
		$('#session_struct_right_right').load("classes_importnamelist.jsp");
	})
	//点击添加作业信息时
	$('#teacher_main_down').on('click','#add_task',function(){
		$('#session_struct_right_right').load("classes_add_task.jsp");
	})
})