function entire_classes(url,start_year,end_year,semester,course,classes_id,classes_name){
	var args={"start_year":start_year,"end_year":end_year,"semester":semester,"course":course,"classes_id":classes_id,"classes_name":classes_name};
	$.get(url,args,function(data){
		$("#session_right").html(data);
	})
}
function publish_task(url,classes_id,year,semester,course,refresh_url){
	//控件ID
	var modal_id=classes_id+"_publishTask";
	var taskname_id=classes_id+"_task_name";
	var taskcontent_id=classes_id+"_task_content";
	var captcha_id=classes_id+"_captcha";
	var enddate_id=classes_id+"_end_date";
	//取得控件的值
	var task_name=$("#"+taskname_id+"").val();
	var task_content=$("#"+taskcontent_id+"").val();
	var captcha=$("#"+captcha_id+"").val();
	var enddate=$("#"+enddate_id+"").val();
	var args={"task_name":task_name,"task_content":task_content,"captcha":captcha,"classes_id":classes_id,"end_date":enddate};
	$.post(url,args,function(data){
		
		if(data=="success"){
			$("#"+modal_id+"").modal("hide");
			getSessionDetilInfo(refresh_url,year,semester,course);
		}else{
			alert(data);
		}
	})
}
$(function(){
	$('#checkbox input').click(function(){
		var classa = $(this).val().trim();
		var a = classa.indexOf("(");
		if(a > 0 )
		{
		    var str = classa.split("(");
		    var str2  = str[1].split(")");
		    classa=str[0]+str2[0]+str2[1];
		}
		$('#'+classa+'').toggle();
	})
})