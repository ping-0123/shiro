package com.yinzhiwu.shiro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhiwu.shiro.dao.UrlFilterDao;
import com.yinzhiwu.shiro.entity.UrlFilter;
import com.yinzhiwu.shiro.service.UrlFilterService;

@Service
public class UrlFilterServiceImpl extends BaseServiceImpl<UrlFilter, Long> implements UrlFilterService {
	
	@Autowired public void setDao(UrlFilterDao urlFilterDao){
		super.setBaseDao(urlFilterDao);
	}

}
