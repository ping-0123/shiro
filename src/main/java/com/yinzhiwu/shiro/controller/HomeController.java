package com.yinzhiwu.shiro.controller;

import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yinzhiwu.shiro.entity.User;

//@Controller
public class HomeController extends BaseController{
	

	@GetMapping("/login")
	public String loginForm(Model model){
		logger.info("start login");
		model.addAttribute("user", new User());
		return "/login";
	}
	@PostMapping("/login")
	public String login(@Valid User user, BindingResult bindingResult, RedirectAttributes redirectAttributes)
	{
		if(bindingResult.hasErrors()) {
			logger.info("has erros");
			return "login";
		}
		//浣跨敤鏉冮檺宸ュ叿杩涜鐢ㄦ埛鐧诲綍锛� 鐧诲綍鎴愬姛鍚庤烦杞埌shiro閰嶇疆鐨剆uccessUrl涓紝 涓庝笅闈㈢殑return 娌℃湁鍏崇郴
		try{
			logger.info("input username and password");
			org.apache.shiro.subject.Subject subject =SecurityUtils.getSubject();
			subject.login(new UsernamePasswordToken(user.getUsername(), user.getPassword()));
			logger.info("login successfully");
			logger.info("Seesion id is: " + subject.getSession(true).getId());
			return "redirect:/user";
		}catch (AuthenticationException e) {
			logger.info("Authenticate failure");
			redirectAttributes.addFlashAttribute("message", "Authenticate failure");
			return "login"; 
		}
	}
	
	
	@GetMapping("/logout")
	public String logout(RedirectAttributes redirectAttributes){
		SecurityUtils.getSubject().logout();
		redirectAttributes.addFlashAttribute("message", "鎮ㄥ凡瀹夊叏閫�鍑�");
		return "redirect:/login";
	}
	
	@GetMapping("/403")
	public String unauthorizedRole(){
		return "/403";
	}
}
