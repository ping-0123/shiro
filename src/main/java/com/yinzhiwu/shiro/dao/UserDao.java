package com.yinzhiwu.shiro.dao;

import com.yinzhiwu.shiro.entity.User;

public interface UserDao extends BaseDao<User, Long> {

	User findByUserName(String loginName);

}
