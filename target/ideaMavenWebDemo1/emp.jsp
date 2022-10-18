<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
 <link rel="stylesheet" href="easyui/themes/default/easyui.css" type="text/css"></link>
 <link rel="stylesheet" href="easyui/themes/icon.css" type="text/css"></link>
 <script type="text/javascript" src="easyui/jquery-1.9.1.js"></script>
 <script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
 <script type="text/javascript" src="easyui/locale/easyui-lang-zh_CN.js"></script>
 <script type="text/javascript">
  /*********准备添加页面相关数据begin************/
  $(function(){
	  $('#winempdetail').window('close');  // close a window
	  $("#btedit").hide();
  $.getJSON('doinit_Emp.do',function(map){
	  var lswf=map.lswf;
	  var lsdep=map.lsdep;
	//生成福利复选框值
	  for(var i=0;i<lswf.length;i++){
		  var wf=lswf[i];
		  $("#wf").append("<input type='checkbox' name='wids' value='"+wf.wid+"' />"+wf.wname);
	  }
	 //生成部门下拉列表
	  $('#depid').combobox({    
		    data:lsdep,    
		    valueField:'depid',    
		    textField:'depname',
		    value:1,
		    panelHeight:90
		});  
  });
  });
  /*********准备添加页面相关数据end************/
  /***********员工列表bengin*************/
  $(function(){
	  $('#dg').datagrid({    
		    url:'findPageAll_Emp.do', 
		    pagination:true,
		    striped:true,
		    singleSelect:true,
		    pageNumber:1,
		    pageSize:5,
		    pageList:[1,2,3,4,5],
		    columns:[[    
		        {field:'eid',title:'编号',width:100,align:'center'},
		        {field:'ename',title:'姓名',width:100,align:'center'},
		        {field:'sex',title:'性别',width:100,align:'center'},
		        {field:'address',title:'地址',width:100,align:'center'},
		        {field:'sdate',title:'生日',width:100,align:'center'},
		        {field:'fname',title:'照片',width:100,align:'center',
		        	formatter: function(value,row,index){
						return '<img src=uppic/'+row.fname+' width=40 height=50 />'
					}

		        },		        
		        {field:'depname',title:'部门',width:100,align:'center'},
		        {field:'opt',title:'操作',width:180,align:'center',
		        	formatter: function(value,row,index){
		        		var bt1='<input type=button id=btdel name=btdel value=删除 onclick=delById('+row.eid+') />';
		        		var bt2='<input type=button id=btdel name=btdel value=编辑 onclick=findById('+row.eid+') />';
		        		var bt3='<input type=button id=btdel name=btdel value=详细查询 onclick=findDistById('+row.eid+') />'
						return bt1+bt2+bt3;
					}	  
		        }
		    ]]    
		});  

  });
  /***********员工列表end*************/
  /***********保存与修改bengin*************/
  $(function(){
	  //添加
	 $("#btsave").click(function(){
		 $.messager.progress();	// 显示进度条
		 $('#ffemp').form('submit', {
		 	url: 'save_Emp.do',
		 	onSubmit: function(){
		 		var isValid = $(this).form('validate');
		 		if (!isValid){
		 			$.messager.progress('close');	// 如果表单是无效的则隐藏进度条
		 		}
		 		return isValid;	// 返回false终止表单提交
		 	},
		 	success: function(code){
		 		if(code=='1'){
		 			$.messager.alert("提示","保存成功");
		 			$('#dg').datagrid('reload');    // 重新载入当前页面数据  
		 		}else{
		 			$.messager.alert("提示","保存失败");
		 		}
		 		$.messager.progress('close');	// 如果提交成功则隐藏进度条
		 	}
		 });
	 });
	//修改
	 $("#btedit").click(function(){
		 $.messager.progress();	// 显示进度条
		 $('#ffemp').form('submit', {
		 	url: 'update_Emp.do',
		 	onSubmit: function(){
		 		var isValid = $(this).form('validate');
		 		if (!isValid){
		 			$.messager.progress('close');	// 如果表单是无效的则隐藏进度条
		 		}
		 		return isValid;	// 返回false终止表单提交
		 	},
		 	success: function(code){
		 		if(code=='1'){
		 			$.messager.alert("提示","修改成功");
		 			$('#dg').datagrid('reload');    // 重新载入当前页面数据  
		 			$("#btsave").show();
		 			$("#btedit").hide();
		 			$("#ffemp").form("reset");
		 		}else{
		 			$.messager.alert("提示","修改失败");
		 		}
		 		$.messager.progress('close');	// 如果提交成功则隐藏进度条
		 	}
		 });
	 });
  });
  
  
  /***********保存与修改end*************/
  
  /***********删除与查找begin*************/
  function delById(eid){    
	  $.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
	      if (r){    
	           $.get('delById_Emp.do?eid='+eid,function(code){
	        	   if(code=='1'){
			 			$.messager.alert("提示","删除成功");
			 			$('#dg').datagrid('reload');    // 重新载入当前页面数据  
			 		}else{
			 			$.messager.alert("提示","删除失败");
			 		} 
	           });    
	      }    
	  });
  }
  function findById(eid){
	  $.getJSON("findById_Emp.do?eid="+eid,function(emp){
		  //删除原有选中的福利
		  $(":checkbox[name='wids']").each(function(){
			  $(this).prop("checked",false);
		  });
		  //处理管理表单数据（类似以前修改页面数据）
		  $('#ffemp').form('load',{
				'eid':emp.eid,
				'ename':emp.ename,
				'sex':emp.sex,
				'address':emp.address,
				'sdate':emp.sdate,
				'depid':emp.depid,
				'emoney':emp.emoney
			});
         //处理照片
         $("#mtphoto").attr('src','uppic/'+emp.fname);
         //处理福利复选框
         var wids=emp.wids;
         $(":checkbox[name='wids']").each(function(){
        	 for(var i=0;i<wids.length;i++){
        		 if($(this).val()==wids[i]){
            		 $(this).prop("checked",true);
            	 }   
        	 }
        	 
		  });
	  });
	  $("#btedit").show();
	  $("#btsave").hide();
  }
  function findDistById(eid){
	  $.getJSON("findById_Emp.do?eid="+eid,function(emp){
		  //给详情窗口写入值
		  $("#eidtxt").html(emp.eid);
		  $("#enametxt").html(emp.ename);
		  $("#sextxt").html(emp.sex);
		  $("#addresstxt").html(emp.address);
		  $("#sdatetxt").html(emp.sdate);
		  $("#fnametxt").html(emp.fname);
		  $("#depnametxt").html(emp.depname);
		  $("#emoneytxt").html(emp.emoney);
		  //获取福利编号
		  var lswf=emp.lswf;
		  var wnames=[];//福利名称数组
		  for(var i=0;i<lswf.length;i++){
			  var wf=lswf[i];
			  wnames.push(wf.wname);
		  }
		  var strwname=wnames.join(',');
		  $("#wftxt").html(strwname);
		//处理照片
	      $("#dtphoto").attr('src','uppic/'+emp.fname);
		  $('#winempdetail').window('open');  // open a window 
	  });
  }
  /***********删除与查找end*************/
  
 </script>
</head>
<body>
<p align="center">员工列表</p>
<hr />
<!--easyui数据表格写入位置  -->
<table id="dg"></table>
<p>
<form action=""  name="ffemp" id="ffemp" method="post" enctype="multipart/form-data">
<table border="1px" width="700px" height="400px" align="center">
 <tr bgcolor="#FFFFCC" align="center">
   <td colspan="3">员工管理</td>
 </tr>
 <tr>
   <td>姓名</td>
   <td>
   <input type="text" id="ename" name="ename" class="easyui-validatebox" data-options="required:true">
   </td>
   <td rowspan="7" width="262px">
     <img id="mtphoto" alt="" src="uppic/default.jpg" width="260px" height="210px">
   </td>
 </tr>
 <tr>
   <td>性别</td>
   <td>
    <input type="radio" id="sex" name="sex" value="男">男
    <input type="radio" id="sex" name="sex" value="女">女
   </td>
 </tr>
 <tr>
   <td>地址</td>
   <td>
   <input type="text" id="address" name="address">
   </td>
 </tr>
 <tr>
   <td>生日</td>
   <td>
   <input type="date" id="sdate" name="sdate" value="1990-01-01">
   </td>
 </tr>
 <tr>
   <td>照片</td>
   <td>
   <input type="file" id="pic" name="pic">
   </td>
 </tr>
 <tr>
   <td>部门</td>
   <td>
    <input type="text" id="depid" name="depid">
   </td>
 </tr>
 <tr>
   <td>薪资</td>
   <td>
    <input type="text" id="emoney" name="emoney" value="5000">
   </td>
 </tr>
 <tr>
   <td>福利</td>
   <td colspan="2">
    <span id="wf"></span>
   </td>
 </tr>
 <tr bgcolor="#FFFFCC" align="center">
   <td colspan="3">
   <input type="hidden" id="eid" name="eid">
   <input type="button" id="btsave" name="btsave" value="添加">
   <input type="button" id="btedit" name="btedit" value="修改">
   <input type="reset">
   </td>
 </tr>
</table>
</form>
</p>
<div id="winempdetail" class="easyui-window" title="员工详细信息" style="width:700px;height:600px"   
        data-options="iconCls:'icon-save',modal:true">   
     <table border="1px" width="700px" height="400px" align="center">
 <tr bgcolor="#FFFFCC" align="center">
   <td colspan="3">员工管理</td>
 </tr>
 <tr>
   <td align="center" width="100px">编号</td>
   <td>
    <span id="eidtxt"></span>
   </td>
   <td rowspan="8" width="262px">
     <img id="dtphoto" alt="" src="uppic/default.jpg" width="260px" height="210px">
   </td>
 </tr>
 <tr>
   <td align="center">姓名</td>
   <td>
   <span id="enametxt"></span>
   </td>
 </tr>
 <tr>
   <td align="center">性别</td>
   <td>
    <span id="sextxt"></span>
   </td>
 </tr>
 <tr>
   <td align="center">地址</td>
   <td>
   <span id="addresstxt"></span>
   </td>
 </tr>
 <tr>
   <td align="center">生日</td>
   <td>
   <span id="sdatetxt"></span>
   </td>
 </tr>
 <tr>
   <td align="center">照片</td>
   <td>
   <span id="fnametxt"></span>
   </td>
 </tr>
 <tr>
   <td align="center">部门</td>
   <td>
    <span id="depnametxt"></span>
   </td>
 </tr>
 <tr>
   <td align="center">薪资</td>
   <td>
    <span id="emoneytxt"></span>
   </td>
 </tr>
 <tr>
   <td align="center">福利</td>
   <td colspan="2">
    <span id="wftxt"></span>
   </td>
 </tr>
</table>  
</div>  

</body>
</html>