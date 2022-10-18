package com.util;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.service.*;

@Service("BizService")
public class BizServiceUtil {
	  @Resource(name="DepServiceImpl")
	  private IDepService depServiceImpl;
	  @Resource(name="EmpServiceImpl")
	  private IEmpService empServiceImpl;
	  @Resource(name="WelfareServiceImpl")
	  private IWelfareService welfareServiceImpl;
	public IDepService getDepServiceImpl() {
		return depServiceImpl;
	}
	public void setDepServiceImpl(IDepService depServiceImpl) {
		this.depServiceImpl = depServiceImpl;
	}
	public IEmpService getEmpServiceImpl() {
		return empServiceImpl;
	}
	public void setEmpServiceImpl(IEmpService empServiceImpl) {
		this.empServiceImpl = empServiceImpl;
	}
	public IWelfareService getWelfareServiceImpl() {
		return welfareServiceImpl;
	}
	public void setWelfareServiceImpl(IWelfareService welfareServiceImpl) {
		this.welfareServiceImpl = welfareServiceImpl;
	}
	  
}
