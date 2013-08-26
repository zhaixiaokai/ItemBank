<!-- select * from (select rownum rn,cu.* from curse cu where special_field_id='11' order by rownum)
where rn<4 and rn>1 -->

<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="net.ib.util.dao.*,java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>表格输出和翻页显示</title>
<%

	DaoImpl di	=	new	DaoImpl();
	String	getSql	=	request.getParameter("setSql");
	System.err.println(getSql);
	if(getSql!=null){
		di.execute(getSql);
	}

%>
<%-- 下面的代码用来获取数据数目，计算出页面数，通过页面数获取当前页面应该显示的数据内容 --%>
<%
	int	currentRow=0;
	int dipage=1;//当前页码数默认为1
	int	rowPerPage=10;//每一页显示16条数据
	List<Map> list	=	di.executeQuery("select count(*) from sys_curse cu where special_field_id='11'");// 获取数据项数目 
	String	DataCountStr	=	list.get(0).get("count(*)").toString();
	int	DataCount	=	Integer.valueOf(DataCountStr);
	System.out.println("DataCount:	"+DataCount);
	int	pageNum=0;//分页总数
	if(DataCount%rowPerPage==0){
		pageNum=DataCount/rowPerPage;
		if(DataCount==0){
			pageNum=1;
		}
	}
	else{
		pageNum=DataCount/rowPerPage+1;
	}
	System.out.println(pageNum);
	String pages=request.getParameter("dipage");
	if(pages==null){
	  pages="1";
	}
	try{
	  dipage=Integer.parseInt(pages);
	}
	catch(Exception e){
	  dipage=1; 
	}
	//获取数据的sql语句
	String	sql="select * from (select rownum rn,cu.* from sys_curse cu where special_field_id='11' order by rownum) where rn<="+rowPerPage*dipage+" and rn>"+(dipage-1)*rowPerPage;
	List<Map> PageList	=	di.executeQuery(sql);
	int	row	=	PageList.size();
%>
<script src="../js/TableDisp.js" type="text/javascript" charset="gb2312"></script>
<script src="../js/checkAll.js" type="text/javascript" charset="gb2312"></script>
<script type="text/javascript">
function funcDelet(e){
	pKey	=	e.id;
	var sql	=	"delete from curse where curse_id="+"'"+pKey+"'";
	var url="../document/ExcuteSQL.jsp?sql="+sql;
	document.getElementById(pKey).href="turnTable.jsp?sql="+sql;

}
function funcModify(){
	
}
function bulkDelete(nameGroup){
	var checkboxs = document.getElementsByName(nameGroup);
	alert("helloworld");
	var sql	=	"delete from curse where";
	var	flag=false;
	if(checkboxs.length>0){
		alert(checkboxs.length);
		for(var i=0;i<checkboxs.length;i++){
			if(checkboxs[i].checked){
				if(flag==false){
					sql+=" curse_id='"+checkboxs[i].id.substring(5)+"'";//去掉Id中的check
					alert(sql);
					flag=true;
				}
				else{
					sql+=" or"+" curse_id='"+checkboxs[i].id.substring(5)+"'";
				}
			}
		}
	}
	if(flag==false){
		sql="";
	}
	alert(sql);
	document.getElementById("bulkDel").href="turnTable.jsp?setSql="+sql;
	alert("helloworld");
}
</script>
</head>
<body>
	<%-- 下面的table是生成数据表格的方法demo --%>
	<a id="bulkDel" href="javascript:void(0);" onclick="bulkDelete('check');">批量删除</a>
	<table	width="100%" id="dataTable">
		<tr>
			<td><input type="checkbox" name="checkAll" id="checkAll" onclick="check_all(this,'check');">序号</td>
			<td>curse_id</td>
			<td>curse_name</td>
			<td>special_field_id</td>
			<td>操作</td>
		</tr>
	<%
			for(currentRow=0;currentRow<row;currentRow++)
			{
	%>
		<tr id="tr<%=currentRow%>">
			<td><input type="checkbox" name="check" id="check<%=PageList.get(currentRow).get("curse_id")%>"/><%=PageList.get(currentRow).get("rn") %></td>
			<td><%=PageList.get(currentRow).get("curse_id")%></td>
			<td><%=PageList.get(currentRow).get("curse_name")%></td>
			<td><%=PageList.get(currentRow).get("special_field_id")%></td>
			<td><a id="<%=PageList.get(currentRow).get("curse_id")%>" href="javascript:void(0)" onclick="funcDelet(this)">删除</a></td>
		</tr>
	<%
			}
	%>
	</table>
	<%-- 上面的table是生成数据表格的方法demo --%>
	
	<%-- 下面的table是表格footer的生成方法 --%>
	<table>
	<tr>
	<td>
	<%
	    if(dipage==1)//当前是首页
	       ;
	    else//当前不是首页
    {
    %>
    
       <a href=turnTable.jsp?dipage=1>首页</a>&nbsp;
       <a href=turnTable.jsp?dipage=<%=(dipage-1) %>>&lt;上一页</a>&nbsp;
    <%
    }
	if(pageNum>5){
		if(dipage<=3){    	
    %>
			<a id="a_1" href=turnTable.jsp?dipage=1>1</a>&nbsp;
			<a id="a_2" href=turnTable.jsp?dipage=2>2</a>&nbsp;
			<a id="a_3" href=turnTable.jsp?dipage=3>3</a>&nbsp;
			<a id="a_4" href=turnTable.jsp?dipage=4>4</a>&nbsp;
			<a id="a_5" href=turnTable.jsp?dipage=5>5</a>&nbsp;
			...   	
    <%
		}
		else if(dipage>=pageNum-2){  	
    %>
			...
			<a id="a_1" href=turnTable.jsp?dipage=<%=(pageNum-4) %>><%=(pageNum-4) %></a>&nbsp;
			<a id="a_2" href=turnTable.jsp?dipage=<%=(pageNum-3) %>><%=(pageNum-3) %></a>&nbsp;
			<a id="a_3" href=turnTable.jsp?dipage=<%=(pageNum-2) %>><%=(pageNum-2) %></a>&nbsp;
			<a id="a_4" href=turnTable.jsp?dipage=<%=(pageNum-1) %>><%=(pageNum-1) %></a>&nbsp;
			<a id="a_5" href=turnTable.jsp?dipage=<%=(pageNum) %>><%=(pageNum) %></a>&nbsp;
	<%
		}
		else{
	%>
			...
			<a href=turnTable.jsp?dipage=<%=(dipage-2) %>><%=(dipage-2) %></a>&nbsp;
			<a href=turnTable.jsp?dipage=<%=(dipage-1) %>><%=(dipage-1) %></a>&nbsp;
			<a style="color: red" href=turnTable.jsp?dipage=<%=(dipage) %>><%=(dipage) %></a>&nbsp;
			<a href=turnTable.jsp?dipage=<%=(dipage+1) %>><%=(dipage+1) %></a>&nbsp;
			<a href=turnTable.jsp?dipage=<%=(dipage+2) %>><%=(dipage+2) %></a>&nbsp;
			...
	<%
		}		
	}
	else{
		for(int	i=1;i<pageNum+1;i++){
	%>
			<a id="a_<%=i %>" href=turnTable.jsp?dipage=<%=i %>><%=i %></a>&nbsp;
	<%
		}
	}
    if(dipage==pageNum)//当前是末页
        ;
     else//当前不是末页
     {
	%>
       <a href=turnTable.jsp?dipage=<%=(dipage+1) %>>下一页&gt;</a>&nbsp;
       <a href=turnTable.jsp?dipage=<%=pageNum %>>末页</a>&nbsp;
    <%
    }     
    /* out.print("共"+pageNum+"条记录,共"+pageNum+"页，当前第"+dipage+"页，每页"+rowPerPage+"条记录，到第"); */
	%>
	共<%=pageNum %>页&nbsp;&nbsp;&nbsp;到第<input id="input_refferToNum" type="text" size="3">页&nbsp;&nbsp;&nbsp
	<a id="sure" href="javascript:void(0);" onclick="refferTo(<%=pageNum%>);">确定</a>
	</td>
	</tr>
	</table>
	<%-- 上面的table是表格footer的生成方法 --%>
	<script type="text/javascript">
		setCurrentPageIdRed(<%=dipage%>,<%=pageNum%>);
	</script>
</body>
</html>