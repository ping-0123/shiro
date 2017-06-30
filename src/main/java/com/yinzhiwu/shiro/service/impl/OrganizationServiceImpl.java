package com.yinzhiwu.shiro.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.shiro.dao.OrganizationDao;
import com.yinzhiwu.shiro.entity.Organization;
import com.yinzhiwu.shiro.service.OrganizationService;

@Service
public class OrganizationServiceImpl extends BaseServiceImpl<Organization,Long> implements OrganizationService{
	@Autowired public void setDao(OrganizationDao dao){
		super.setBaseDao(dao);
	}

	@Override
	public List<Organization> findAllWithExclude(Organization source) {
		List<Organization> orgs = findAll();
		orgs.removeAll(source.getAllChilds());
		orgs.remove(source);
		return orgs;
	}

	@Override
	public void move(Organization source, Organization target) {
		source.setParent(target);
		update(source);
	}

}
