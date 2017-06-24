package com.yinzhiwu.shiro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.shiro.dao.RoleDao;
import com.yinzhiwu.shiro.entity.Role;
import com.yinzhiwu.shiro.service.RoleService;

@Service
public class RoleServiceImpl extends BaseServiceImpl<Role, Integer> implements RoleService{

	@Autowired public void setDao(RoleDao dao){ super.setBaseDao(dao);}
}
