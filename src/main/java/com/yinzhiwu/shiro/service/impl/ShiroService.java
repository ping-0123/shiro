package com.yinzhiwu.shiro.service.impl;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yinzhiwu.shiro.dao.UserDao;
import com.yinzhiwu.shiro.entity.Role;
import com.yinzhiwu.shiro.entity.User;
import com.yinzhiwu.shiro.service.UserService;

@Service
@Transactional
public class ShiroService extends AuthorizingRealm{
	
	@Autowired UserService userService;
	@Autowired UserDao userDao;
	
	/**
	 * 权限验证
	 * @param arg0
	 * @return 
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		//获取登录时输入的用户名
		String loginName = (String) arg0.fromRealm(getName()).iterator().next();
		User user = userDao.findByUserName(loginName);
		if(user ==null) return null;
		//权限信息对象， 用来存放查出的用户的所有角色(role)及权限(permission)
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		//用户角色集合
		info.setRoles(user.getRolesName());
		//用户角色对应的所有权限， 如果只使用角色定义访问权限， 下面四行可以不要
		List<Role> roles = user.getRoles();
		for (Role role : roles) {
			info.addStringPermissions(role.getPermissionsName());
		}
		return info;
		
	}

	/**
	 * 登录认证
	 * @param arg0
	 * @return
	 * @throws AuthenticationException
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
		//UsernamePasswordToken 对象用来存放提交的登录信息
		UsernamePasswordToken token = (UsernamePasswordToken) arg0;
		//查出是否有此用户
		User user = userDao.findByUserName(token.getUsername());
		if(user !=null)
			//如果存在，将此用户存放到登录认证info中
			return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());
		return null;
	}
}
