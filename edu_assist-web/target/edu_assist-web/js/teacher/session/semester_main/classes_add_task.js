
$(function(){
	//表单验证部分
	  $("#task_form").validate({
		    rules: {
		      task_name: {
		        required: true,
		        minlength:6
		      },
		      task_content: {
		        required: true,
		        minlength: 10
		      },
		      captcha:{
		    	  required: true,
		    	  minlength:4,
		    	  maxlength:4
		      }
		    },
		    messages: {
		    	task_name: {
		        required: "请输入作业名称",
		        minlength: "作业名必须超过3个字"
		      },
		      task_content: {
		        required: "请输入作业内容",
		        minlength: "作业内容不能低于5个字"
		      },
		      captcha:{
		    	required: "请输入验证",
		    	minlength: "验证码长度不能低于4位"
		      }
		    },
		    errorClass: "invalid",
		    submitHandler:function(form){
	            alert("提交事件!");   
	            //form.submit();
		  	   } , 
		});
})
	