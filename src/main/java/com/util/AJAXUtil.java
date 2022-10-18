package com.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.serializer.PropertyFilter;

public class AJAXUtil {
/**服务器返回响应转换json*/
	public static void printString(HttpServletResponse response,String s){
		response.setCharacterEncoding("utf-8");
		PrintWriter out=null;
		try {
			out = response.getWriter();
			System.out.println("返回前端的json字符串:"+s);
			out.print(s);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	};
/**
 * 过滤属性
 * */
public static PropertyFilter filterProperys(final String ... PropNames){
	PropertyFilter propertyFilter=new PropertyFilter() {
		
		@Override
		public boolean apply(Object arg0, String PropName, Object arg2) {
			for(String pname:PropNames){
				if(PropName.equals(pname)){
					return false;//过滤该属性
				}
			}
			return true;//不过滤该属性
		}
	};
	return propertyFilter;
	
}
	
}
