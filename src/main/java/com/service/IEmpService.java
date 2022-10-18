package com.service;

import java.util.*;

import org.springframework.stereotype.Service;

import com.po.*;

public interface IEmpService {
 /**添加员工*/
 public boolean save(Emp emp);
 /**修改员工*/
 public boolean update(Emp emp);
 /**删除员工*/
 public boolean  delByEid(Integer eid);
 /**查询单个员工*/
 public Emp findByEid(Integer eid);
 /**分页查询所有员工*/
 public List<Emp> findPageAll(PageBean pb);
 /**查询总记录数*/
 public int  findMaxRows();
}
