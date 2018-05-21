$(function(){
	var pageContext = $('#PageContext').val();
	$('#create_classes').click(function(){
		var url = pageContext+"/teacher/classes";
		var year= $('#academic_year').val();
		var semester=$('#semester').val();
		var course=$('#course').val();
		var classes_name=$('#classes_name').val();
		var args={"start_year":year.split('-')[0],"end_year":year.split('-')[1],"semester":semester,"course":course,"classes_name":classes_name};
		$.ajax({
			url:url,
			type:"POST",
			data:args,
			success:function(data){
				if(data=="success"){
					$('#create_classes_modal').modal('hide');
					getSessionDetilInfo(pageContext+'/teacher/classespage',year,semester,course);
				}else{
					alert("创建失败");
				}
			}
		})
	})
})