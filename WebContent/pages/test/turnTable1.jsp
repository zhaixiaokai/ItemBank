<%@ page contentType="text/html;charset=GB2312" %>
<%@ page import="java.sql.*" %>
<%
int dipage=1;//当前页码数默认为1
String pages=request.getParameter("dipage");
if(pages==null)
{
  pages="1";
}
try
{
  dipage=Integer.parseInt(pages);
}
catch(Exception e)
{
  dipage=1; 
}
%>
<HTML>
<title>用户数据</title>
<BODY>
 <% Connection con;
    Statement sql; 
    ResultSet rs;
    try
    {
      Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
    }
    catch(ClassNotFoundException e)
    {
      out.print("类找不到！");
    }
    try 
    {  
         con=DriverManager.getConnection("jdbc:microsoft:sqlserver://localhost:1433;DatabaseName=testDatabse","sa","");
         sql=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
         rs=sql.executeQuery("SELECT * FROM userTable");
         int countRecord=0;//记录条数
         int countPageRecord=0;//每页记录条数
         int countPage=0;//总页数
         countPageRecord=5;//每页5条记录，要设置每页记录条数就更改这个变量的值
         //得到记录的条数
         rs.last();
         countRecord=rs.getRow();
         //得到总页数
         if(countRecord/countPageRecord==0)
            countPage=countRecord/countPageRecord;
         else
            countPage=countRecord/countPageRecord+1;
         //把记录指针移至当前页第一条记录之前
         if((dipage-1)*countPageRecord==0)
            rs.beforeFirst();
         else
            rs.absolute((dipage-1)*countPageRecord);
         out.print("<Table Border style='font-size: 10pt'>");
         out.print("<TR><td colspan=8 align=center>用户数据</td></tr>");   
         out.print("<TR>");
            out.print("<Td width=60 >"+"用户ID号");
            out.print("<Td width=50 >"+"用户名");
            out.print("<Td width=100>"+"用户真实姓名");
            out.print("<Td width=40>"+"年龄");
            out.print("<Td width=40>"+"性别");
            out.print("<Td width=100>"+"联系地址");
            out.print("<Td width=100>"+"联系电话");
            out.print("<Td width=100>"+"添加时间");
         out.print("</TR>");
       int i=0;
       while(rs.next())
       { out.print("<TR>");
             out.print("<TD >"+rs.getLong(1)+"</TD>");
             out.print("<TD >"+rs.getString(2)+"</TD>"); 
             out.print("<TD >"+rs.getString(4)+"</TD>");
             out.print("<TD >"+rs.getInt("user_age")+"</TD>"); 
             out.print("<TD >"+rs.getString("user_sex")+"</TD>");
             out.print("<TD >"+rs.getString("user_address")+"</TD>");
             out.print("<TD >"+rs.getString("user_telephone")+"</TD>");
             out.print("<TD >"+rs.getString("add_time")+"</TD>");
        out.print("</TR>") ;
        i++;
        if(i>=countPageRecord) break; //当前页显示完，则退出循环
        }
        out.print("<TR><td colspan=8 align=center>");
           out.print("共"+countRecord+"条记录,共"+countPage+"页，当前第"+dipage+"页，每页"+countPageRecord+"条记录，");
           if(dipage==1)//当前是首页
              ;
           else//当前不是首页
           {
              out.print("<a href=userPage1.jsp?dipage=1>首页</a>，");
              out.print("<a href=userPage1.jsp?dipage="+(dipage-1)+">上一页</a>,");
           }
           if(dipage==countPage)//当前是末页
              ;
           else//当前不是末页
           {
              out.print("<a href=userPage1.jsp?dipage="+(dipage+1)+">下一页</a>,");
              out.print("<a href=userPage1.jsp?dipage="+countPage+">末页</a>");
           }          
           
           out.print("</td></tr>"); 
        out.print("</Table>");
        con.close();
     }
   catch(SQLException e1) 
   {
      out.print("SQL异常！");
   }
 %>
</BODY>
</HTML>