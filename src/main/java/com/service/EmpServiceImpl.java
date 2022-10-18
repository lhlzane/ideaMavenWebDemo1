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
		//���Ա������
		int code=daoService.getEmpMapper().save(emp);
		if(code>0){
		//�ҵ��ղ���ӵ�Ա�������id��Ա����������ID��
		int maxeid=daoService.getEmpMapper().findMaxEid();
		//���н��
		Salary sa=new Salary(maxeid,emp.getEmoney());
		daoService.getSalaryMapper().save(sa);
		//���Ա������
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
			/**************�޸�н��************/
			//��ȡԭ��н��
			Salary oldsa=daoService.getSalaryMapper().findByEid(emp.getEid());
			if(oldsa!=null&&oldsa.getEmoney()!=null){//ԭ����--�޸ľ����޸�
				Salary sa=new Salary(oldsa.getSid(),emp.getEid(),emp.getEmoney());
				daoService.getSalaryMapper().updateByEid(sa);
			}else{//ԭ��û��--�޸ľ������
				Salary sa=new Salary(emp.getEid(),emp.getEmoney());
				daoService.getSalaryMapper().save(sa);
			}
			/**************�޸�Ա��������ϵ��************/
			//��ȡԭ�и���
			List<Welfare> lswf=daoService.getEmpwelfareMapper().findByEid(emp.getEid());
			if(lswf!=null&&lswf.size()>0){
				//ɾ��ԭ�и���
				daoService.getEmpwelfareMapper().delByEid(emp.getEid());
			}
			//����µĸ���
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
		//��ɾ��Ա����ӱ�н�ʱ�Ա��������ϵ��
		daoService.getSalaryMapper().delByEid(eid);
		daoService.getEmpwelfareMapper().delByEid(eid);
		//ɾ��Ա������
		int code=daoService.getEmpMapper().delByEid(eid);
		if(code>0){
			return true;
		}
		return false;
	}

	@Override
	public Emp findByEid(Integer eid) {
		//��ѯԱ������
		Emp oldemp=daoService.getEmpMapper().findByEid(eid);
		//��ѯ��Ա��н��
		Salary oldsa=daoService.getSalaryMapper().findByEid(eid);
		if(oldsa!=null&&oldsa.getEmoney()!=null){
			oldemp.setEmoney(oldsa.getEmoney());
		}
		//��ѯ��Ա����������
		List<Welfare> oldlswf=daoService.getEmpwelfareMapper().findByEid(eid);
		if(oldlswf!=null&&oldlswf.size()>0){
			//�����������
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
