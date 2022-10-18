package com.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.po.Dep;

public interface IDepService {
	/**查询部门所有*/
 public List<Dep> findAll();

}
