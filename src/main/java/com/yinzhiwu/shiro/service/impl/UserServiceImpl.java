package com.yinzhiwu.shiro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.shiro.dao.UserDao;
import com.yinzhiwu.shiro.entity.User;
import com.yinzhiwu.shiro.service.UserService;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, Integer> implements UserService{

	@Autowired public void setDao(UserDao userDao){ super.setBaseDao(userDao);}
}
