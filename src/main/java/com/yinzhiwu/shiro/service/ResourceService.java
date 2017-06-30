package com.yinzhiwu.shiro.service;

import java.util.List;
import java.util.Set;

import com.yinzhiwu.shiro.entity.Resource;

public interface ResourceService extends BaseService<Resource,Long> {

	List<Resource> findMenus(Set<String> permissions);

	Set<String> findPermissions(Set<Long> resourceIds);

}
