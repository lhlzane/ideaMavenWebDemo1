package com.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.po.Dep;
import com.util.DaoServiceUtil;
@Service("DepServiceImpl")
@Transactional
public class DepServiceImpl implements IDepService {
	@Resource(name="DaoService")
    private DaoServiceUtil daoService;
    
	public DaoServiceUtil getDaoService() {
		return daoService;
	}

	public void setDaoService(DaoServiceUtil daoService) {
		this.daoService = daoService;
	}

	@Override
	public List<Dep> findAll() {
		// TODO Auto-generated method stub
		return daoService.getDepMapper().findAll();
	}

}
