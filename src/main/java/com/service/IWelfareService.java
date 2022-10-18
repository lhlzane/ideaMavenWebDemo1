package com.service;

import java.util.*;

import org.springframework.stereotype.Service;

import com.po.*;

public interface IWelfareService {
	/**查询福利所有*/
	 public List<Welfare> findAll();
}
