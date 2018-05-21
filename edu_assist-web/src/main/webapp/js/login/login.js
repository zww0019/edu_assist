$(function() {
	var pageContext = $('#PageContext').val();
	//表单验证部分
	  $("#login_form").validate({
		    rules: {
		      username: {
		        required: true,
		        minlength:12
		      },
		      password: {
		        required: true,
		        minlength: 1
		      },
		      captcha:{
		    	  required: true,
		    	  minlength:4,
		    	  maxlength:4
		      }
		    },
		    messages: {
		      username: {
		        required: "请输入用户名",
		        minlength: "用户名必须超过12个字符"
		      },
		      password: {
		        required: "请输入密码",
		        minlength: "密码长度不能小于 1 个字母"
		      },
		      captcha:{
		    	required: "请输入验证",
		    	minlength: "验证码长度不能低于4位"
		      }
		    },
		    errorClass: "invalid",
		    submitHandler:function(form){
		    	var username = $('#username').val();
		    	var password = $('#password').val();
		    	var iden = $("select option:selected").text();
		    	var url = pageContext+"/main/dologin.do";
		    	var args = {"username":username,"password":password,"iden":iden};
	            form.submit();
		  	   } , 
		});
	$('#login_message').fadeToggle(5000);
	$('#login_Model').modal('show');
	// 登录按钮点击时，显示上传页面
//	$('#submit').on("click", function() {
//		var iden = $("select option:selected").text().trim();
//		if (iden == "学生") {
//			$('#model').load;
//			$('#upload_Model').modal('show');
//		} else {
//			window.location.href = "teacher_main.jsp";
//		}
//
//	})
})