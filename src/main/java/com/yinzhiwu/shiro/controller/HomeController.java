package com.yinzhiwu.shiro.controller;

import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yinzhiwu.shiro.entity.User;

@Controller
public class HomeController extends BaseController{

	@GetMapping("/login")
	public String loginForm(Model model){
		model.addAttribute("user", new User());
		return "/login";
	}
	@PostMapping("/login")
	public String login(@Valid User user, BindingResult bindingResult, RedirectAttributes redirectAttributes)
	{
		if(bindingResult.hasErrors()) return "/login";
		//使用权限工具进行用户登录， 登录成功后跳转到shiro配置的successUrl中， 与下面的return 没有关系
		try{
			SecurityUtils.getSubject().login(new UsernamePasswordToken(user.getName(), user.getPassword()));
			return "redirect:/user";
		}catch (AuthenticationException e) {
			redirectAttributes.addFlashAttribute("message", "用户名或密码错误");
			return "redirect:/login"; 
		}
	}
	
	@GetMapping("/logout")
	public String logout(RedirectAttributes redirectAttributes){
		SecurityUtils.getSubject().logout();
		redirectAttributes.addFlashAttribute("message", "您已安全退出");
		return "redirect:/login";
	}
	
	@GetMapping("/403")
	public String unauthorizedRole(){
		return "/403";
	}
}
