package com.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.po.Emp;
import com.po.EmpWelfare;
import com.po.PageBean;
import com.po.Salary;
import com.po.Welfare;
import com.util.DaoServiceUtil;
@Service("EmpServiceImpl")
@Transactional
public class EmpServiceImpl implements IEmpService {
	@Resource(name="DaoService")
    private DaoServiceUtil daoService;
    
	public DaoServiceUtil getDaoService() {
		return daoService;
	}

	public void setDaoService(DaoServiceUtil daoService) {
		this.daoService = daoService;
	}

	@Override
	public boolean save(Emp emp) {
		//添加员工对象
		int code=daoService.getEmpMapper().save(emp);
		if(code>0){
		//找到刚才添加的员工对象的id（员工表中最大的ID）
		int maxeid=daoService.getEmpMapper().findMaxEid();
		//添加薪资
		Salary sa=new Salary(maxeid,emp.getEmoney());
		daoService.getSalaryMapper().save(sa);
		//添加员工福利
		String[] wids=emp.getWids();
		if(wids!=null&&wids.length>0){
			for(int i=0;i<wids.length;i++){
				EmpWelfare ewf=new EmpWelfare(maxeid,new Integer(wids[i]));
				daoService.getEmpwelfareMapper().save(ewf);
			}
		}
		return true;
		}
		return false;
	}

	@Override
	public boolean update(Emp emp) {
		int code=daoService.getEmpMapper().update(emp);
		if(code>0){
			/**************修改薪资************/
			//获取原有薪资
			Salary oldsa=daoService.getSalaryMapper().findByEid(emp.getEid());
			if(oldsa!=null&&oldsa.getEmoney()!=null){//原来有--修改就是修改
				Salary sa=new Salary(oldsa.getSid(),emp.getEid(),emp.getEmoney());
				daoService.getSalaryMapper().updateByEid(sa);
			}else{//原来没有--修改就是添加
				Salary sa=new Salary(emp.getEid(),emp.getEmoney());
				daoService.getSalaryMapper().save(sa);
			}
			/**************修改员工福利关系表************/
			//获取原有福利
			List<Welfare> lswf=daoService.getEmpwelfareMapper().findByEid(emp.getEid());
			if(lswf!=null&&lswf.size()>0){
				//删除原有福利
				daoService.getEmpwelfareMapper().delByEid(emp.getEid());
			}
			//添加新的福利
			String[] wids=emp.getWids();
			if(wids!=null&&wids.length>0){
				for(int i=0;i<wids.length;i++){
					EmpWelfare ewf=new EmpWelfare(emp.getEid(),new Integer(wids[i]));
					daoService.getEmpwelfareMapper().save(ewf);
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean delByEid(Integer eid) {
		//先删除员工表从表（薪资表，员工福利关系表）
		daoService.getSalaryMapper().delByEid(eid);
		daoService.getEmpwelfareMapper().delByEid(eid);
		//删除员工数据
		int code=daoService.getEmpMapper().delByEid(eid);
		if(code>0){
			return true;
		}
		return false;
	}

	@Override
	public Emp findByEid(Integer eid) {
		//查询员工对象
		Emp oldemp=daoService.getEmpMapper().findByEid(eid);
		//查询该员工薪资
		Salary oldsa=daoService.getSalaryMapper().findByEid(eid);
		if(oldsa!=null&&oldsa.getEmoney()!=null){
			oldemp.setEmoney(oldsa.getEmoney());
		}
		//查询该员工福利数据
		List<Welfare> oldlswf=daoService.getEmpwelfareMapper().findByEid(eid);
		if(oldlswf!=null&&oldlswf.size()>0){
			//福利编号数组
			String[] wids=new String[oldlswf.size()];
			for(int i=0;i<oldlswf.size();i++){
				Welfare wf=oldlswf.get(i);
				wids[i]=wf.getWid()+"";
			}
			oldemp.setWids(wids);
			oldemp.setLswf(oldlswf);
		}
		return oldemp;
	}

	@Override
	public List<Emp> findPageAll(PageBean pb) {
		if(pb==null){
			pb=new PageBean();
		}
		return daoService.getEmpMapper().findPageAll(pb);
	}

	@Override
	public int findMaxRows() {
		// TODO Auto-generated method stub
		return daoService.getEmpMapper().findMaxRows();
	}

}
