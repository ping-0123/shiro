package com.yinzhiwu.shiro.service;

import java.util.List;

import com.yinzhiwu.shiro.entity.Organization;

public interface OrganizationService extends BaseService<Organization,Long> {

	List<Organization> findAllWithExclude(Organization source);

	void move(Organization source, Organization target);

}
