package com.mapper;

import java.util.*;

import org.springframework.stereotype.Service;

import com.po.*;

@Service("SalaryDao")
public interface ISalaryMapper {
	/**���Ա��н��*/
	 public int save(Salary sa);
	 /**����Ա�����ɾ��н������*/
	 public int  delByEid(Integer eid);
	 /**����Ա������޸�н������*/
	 public int  updateByEid(Salary sa);
	 /**����Ա��ID��ѯ��Ա����Ӧ��н��*/
	 public Salary findByEid(Integer eid);
}
