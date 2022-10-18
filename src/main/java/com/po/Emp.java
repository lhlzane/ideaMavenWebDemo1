package com.po;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Emp  implements Serializable{
   private Integer eid;
   private String ename;
   private String sex;
   private String address;
   private Date birthday;
   private String fname="default.jpg";
   private Integer depid;
   //与前台页面相关的临时属性
    //是日期与字符串转化
   private String sdate;
    //展示部门名称
   private String depname;
    //是日期与字符串转化
   private Float emoney;
    //添加或修改时福利Id数组
   private String[] wids;
   //查询福利时
   private List<Welfare> lswf;
   //文件上传
   private MultipartFile pic;
public Emp() {
	super();
	// TODO Auto-generated constructor stub
}

public Emp(Integer eid, String ename, String sex, String address, Date birthday, String fname, Integer depid,
		String sadte, String depname, Float emoney, String[] wids, List<Welfare> lswf, MultipartFile pic) {
	super();
	this.eid = eid;
	this.ename = ename;
	this.sex = sex;
	this.address = address;
	this.birthday = birthday;
	this.fname = fname;
	this.depid = depid;
	this.sdate = sadte;
	this.depname = depname;
	this.emoney = emoney;
	this.wids = wids;
	this.lswf = lswf;
	this.pic = pic;
	System.out.println("构造器正在动作.......");
}

public Integer getEid() {
	return eid;
}
public void setEid(Integer eid) {
	this.eid = eid;
}
public String getEname() {
	return ename;
	
}
public void setEname(String ename) {
	this.ename = ename;
	System.out.println("set方法正在运行.....");
}
public String getSex() {
	return sex;
}
public void setSex(String sex) {
	this.sex = sex;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public Date getBirthday() {
	return birthday;
}
public void setBirthday(Date birthday) {
	this.birthday = birthday;
}
public String getFname() {
	return fname;
}
public void setFname(String fname) {
	this.fname = fname;
}
public Integer getDepid() {
	return depid;
}
public void setDepid(Integer depid) {
	this.depid = depid;
}
public String getSdate() {
	sdate=new SimpleDateFormat("yyyy-MM-dd").format(birthday);
	return sdate;
}
public void setSdate(String sdate) {
	try {
		birthday=new SimpleDateFormat("yyyy-MM-dd").parse(sdate);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	this.sdate = sdate;
}
public String getDepname() {
	return depname;
}
public void setDepname(String depname) {
	this.depname = depname;
}
public Float getEmoney() {
	return emoney;
}
public void setEmoney(Float emoney) {
	this.emoney = emoney;
}
public String[] getWids() {
	return wids;
}
public void setWids(String[] wids) {
	this.wids = wids;
}
public List<Welfare> getLswf() {
	return lswf;
}
public void setLswf(List<Welfare> lswf) {
	this.lswf = lswf;
}

public MultipartFile getPic() {
	return pic;
}

public void setPic(MultipartFile pic) {
	this.pic = pic;
}

@Override
public String toString() {
	return "Emp [eid=" + eid + ", ename=" + ename + ", sex=" + sex + ", address=" + address + ", birthday=" + birthday
			+ ", fname=" + fname + ", depid=" + depid + ", sdate=" + sdate + ", depname=" + depname + ", emoney="
			+ emoney + ", wids=" + Arrays.toString(wids) + ", lswf=" + lswf + ", pic=" + pic + "]";
}


   
   
   
}
