<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
    <script type="text/javascript">      
        
        $(function(){         
        $('#kaptchaImage').click(function () {//生成验证码  
             $(this).hide().attr('src', '${pageContext.request.contextPath}/captcha/captcha-image.do?' + Math.floor(Math.random()*100) ).fadeIn();  
             event.cancelBubble=true;  
            });  
        }); 
        
        function changeCode() {  
            $('#kaptchaImage').hide().attr('src', '${pageContext.request.contextPath}/captcha/captcha-image.do?' + Math.floor(Math.random()*100) ).fadeIn();  
            event.cancelBubble=true;  
        } 
   </script> 
   
   <div class="chknumber">  
          <label>验证码:  
              <input name="kaptcha" type="text" id="kaptcha" maxlength="4" class="chknumber_input" />               
          </label>  
          <br />  
          <img src="${pageContext.request.contextPath}/captcha/captcha-image.do" id="kaptchaImage"  style="margin-bottom: -3px"/>  
          <a href="#" onclick="changeCode()">看不清?换一张</a>  
    </div>