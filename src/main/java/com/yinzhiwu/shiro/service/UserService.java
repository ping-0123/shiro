package com.yinzhiwu.shiro.service;

import java.util.Set;

import com.yinzhiwu.shiro.entity.User;

public interface UserService extends BaseService<User, Long> {

	Set<String> findRoles(String username);

	Set<String> findPermissions(String username);

	User findByUsername(String username);

	void changePassword(Long id, String newPassword);

}
