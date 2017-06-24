package com.yinzhiwu.shiro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.shiro.dao.PermissionDao;
import com.yinzhiwu.shiro.entity.Permission;
import com.yinzhiwu.shiro.service.PermissionService;

@Service
public class PermissionServiceImpl extends BaseServiceImpl<Permission, Integer> implements PermissionService{

	@Autowired public void setDao(PermissionDao dao){ super.setBaseDao(dao);}
}
