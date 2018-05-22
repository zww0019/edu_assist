package com.warzone.edu_assist.controller.login;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.warzone.edu_assist_domain_user.User;
import com.warzone.edu_assist_role.Identity;
import com.warzone.edu_assist_service_user.service.UserService;


@Controller
@RequestMapping("main")
public class LoginController {

	@Autowired
	private UserService userService;
	@RequestMapping("login")
	public String loginpage(){
		return "login/index";
	}
	@RequestMapping(value = "dologin",method=RequestMethod.POST,produces= {"application/json;charset=UTF-8"})
	public ModelAndView login(String username,String password,String iden,String captcha,HttpServletRequest request) throws Exception{
		String sion_captcha = (String) request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		ModelMap model = new ModelMap();
		String view ="login/index";
		System.out.println("AAAAAAAAAAAAAAAAAAAA这时一个测试语句");
		if(sion_captcha.equals(captcha)){
			System.out.println(iden);
			System.out.println(Identity.学生.toString());
			User user = userService.getUserByUsernameAndAuthority(username,iden.equals(Identity.学生.toString()) ? Identity.学生.getIdentity() : Identity.教师.getIdentity());
			
			if(user==null){
				model.addAttribute("login_message", "用户名错误");
			}else if(user.getPassword().equals(password))
				{
					request.getSession().setAttribute("user", user);
					System.out.println(user);
					if(user.getAuthority().equals(Identity.学生.getIdentity())){
						view="redirect:/student/mainpage";
					}else{
						view="redirect:/teacher/mainpage";
					}
					
				}else{
					model.addAttribute("login_message", "密码错误");
					}
		}else{
			model.addAttribute("login_message", "验证码错误");
		}
		return new ModelAndView(view, model);
	}
}
