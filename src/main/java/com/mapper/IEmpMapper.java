package com.mapper;

import java.util.*;

import org.springframework.stereotype.Service;

import com.po.*;

@Service("EmpDao")
public interface IEmpMapper {
 /**���Ա��*/
 public int save(Emp emp);
 /**�޸�Ա��*/
 public int update(Emp emp);
 /**ɾ��Ա��*/
 public int  delByEid(Integer eid);
 /**��ѯ����Ա��*/
 public Emp findByEid(Integer eid);
 /**��ҳ��ѯ����Ա��*/
 public List<Emp> findPageAll(PageBean pb);
 /**��ѯ�ܼ�¼��*/
 public int  findMaxRows();
 /**��ѯ���ID*/
 public int  findMaxEid();
 
}
