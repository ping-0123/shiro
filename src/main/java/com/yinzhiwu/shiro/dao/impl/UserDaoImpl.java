package com.yinzhiwu.shiro.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yinzhiwu.shiro.dao.UserDao;
import com.yinzhiwu.shiro.entity.User;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User, Integer> implements UserDao {

	@Override
	public User findByUserName(String loginName) {
		List<User> users = findByProperty("name", loginName);
		if(users != null && users.size()==1)
			return users.get(0);
		return null;
		
	}

}
