package com.mapper;

import java.util.*;

import org.springframework.stereotype.Service;

import com.po.*;

@Service("EmpWelfareDao")
public interface IEmpWelfareMapper {
	/**添加员工福利*/
	 public int save(EmpWelfare ewf);
	 /**根据员工编号删除福利数据*/
	 public int  delByEid(Integer eid);
	 /**根据员工ID查询该员工对应的福利*/
	 public List<Welfare> findByEid(Integer eid);
}
