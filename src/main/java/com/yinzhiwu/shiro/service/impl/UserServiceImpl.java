package com.yinzhiwu.shiro.service.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.web.util.SavedRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.shiro.dao.UserDao;
import com.yinzhiwu.shiro.entity.Role;
import com.yinzhiwu.shiro.entity.User;
import com.yinzhiwu.shiro.service.UserService;
import com.yinzhiwu.shiro.util.PasswordHelper;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, Long> implements UserService{

	@Autowired public void setDao(UserDao userDao){ super.setBaseDao(userDao);}
//	@Autowired private RoleService roleService;
	@Autowired private PasswordHelper passwordHelper;

	@Override
	public Long save(User user){
		 passwordHelper.encryptPassword(user);
		 return super.save(user);
	}
	
	@Override
	public Set<String> findRoles(String username) {
		return findByUsername(username)!=null?findByUsername(username).getRolesName():Collections.emptySet();
	}

	@Override
	public Set<String> findPermissions(String username) {
		User user = findByUsername(username);
		if(user==null) return Collections.emptySet();
		Set<String> permissionNames = new HashSet<>();
		List<Role> roles = user.getRoles();
		for (Role role : roles) {
			permissionNames.addAll(role.getPermissionsName());
		}
		return permissionNames;
	}

	@Override
	public User findByUsername(String username) {
		return super.findByProperty("username", username).get(0);
	}

	@Override
	public void changePassword(Long id, String newPassword) {
	      User user =get(id);
	      user.setPassword(newPassword);
	      passwordHelper.encryptPassword(user);
	      update(user);
		
	}
}
