var pageContext = $('#PageContext').val();
function complete_session(url,academicyear,semester,coursename){
	$.ajax({
		url:url,
		type:"POST",
		data:{"_method":'PUT',"start_year":academicyear.split('-')[0],"end_year":academicyear.split('-')[1],"semester":semester,"course":coursename},
		success:function(data){
			if(data=="success"){
				alert("设置完成");
				window.location.href =pageContext+"/teacher/mainpage";
			}else{
				alert("删除失败");
			}
		}
	})
}
function getSessionDetilInfo(url,academicyear,semester,coursename){
	var args={"start_year":academicyear.split('-')[0],"end_year":academicyear.split('-')[1],"semester":semester,"course":coursename}
	$.get(url,args,function(data){
		$('#session_right').html(data);
		$('#nav li').removeClass("active");
	})
}
function deleteSession(url,academicyear,semester,coursename){
	$.ajax({
		url:url,
		data:{"_method":'DELETE',"start_year":academicyear.split('-')[0],"end_year":academicyear.split('-')[1],"semester":semester,"course":coursename},
		type:"POST",
		success:function(data){
			if(data=="success"){
				alert("删除成功");
				window.location.href =pageContext+"/teacher/mainpage";
			}else{
				alert("删除失败");
			}
		}
	})
}
function splitYear(academicyear){
	return academicyear.split('-');
}