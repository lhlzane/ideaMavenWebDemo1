package com.mapper;

import java.util.*;

import org.springframework.stereotype.Service;

import com.po.*;

@Service("SalaryDao")
public interface ISalaryMapper {
	/**添加员工薪资*/
	 public int save(Salary sa);
	 /**根据员工编号删除薪资数据*/
	 public int  delByEid(Integer eid);
	 /**根据员工编号修改薪资数据*/
	 public int  updateByEid(Salary sa);
	 /**根据员工ID查询该员工对应的薪资*/
	 public Salary findByEid(Integer eid);
}
