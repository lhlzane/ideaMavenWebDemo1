package com.util;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mapper.*;

@Service("DaoService")
public class DaoServiceUtil {
	@Resource(name="DepDao")
  private IDepMapper depMapper;
	@Resource(name="EmpDao")
  private IEmpMapper empMapper;
	@Resource(name="EmpWelfareDao")
  private IEmpWelfareMapper empwelfareMapper;
	@Resource(name="SalaryDao")
  private ISalaryMapper salaryMapper;
	@Resource(name="WelfareDao")
	  private IWelfareMapper welfareMapper;
	public IDepMapper getDepMapper() {
		return depMapper;
	}
	public void setDepMapper(IDepMapper depMapper) {
		this.depMapper = depMapper;
	}
	public IEmpMapper getEmpMapper() {
		return empMapper;
	}
	public void setEmpMapper(IEmpMapper empMapper) {
		this.empMapper = empMapper;
	}
	public IEmpWelfareMapper getEmpwelfareMapper() {
		return empwelfareMapper;
	}
	public void setEmpwelfareMapper(IEmpWelfareMapper empwelfareMapper) {
		this.empwelfareMapper = empwelfareMapper;
	}
	public ISalaryMapper getSalaryMapper() {
		return salaryMapper;
	}
	public void setSalaryMapper(ISalaryMapper salaryMapper) {
		this.salaryMapper = salaryMapper;
	}
	public IWelfareMapper getWelfareMapper() {
		return welfareMapper;
	}
	public void setWelfareMapper(IWelfareMapper welfareMapper) {
		this.welfareMapper = welfareMapper;
	}
	
	
}
