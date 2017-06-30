package com.yinzhiwu.shiro.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//@Controller
@RequestMapping(value="/user")
public class UserController extends BaseController {

	@GetMapping
	public String userForm(){
		return "/user";
	}
}
