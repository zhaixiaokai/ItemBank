<%@ page contentType="text/html;charset=GB2312" %>
<%@ page import="java.sql.*" %>
<%
int dipage=1;//��ǰҳ����Ĭ��Ϊ1
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
<title>�û�����</title>
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
      out.print("���Ҳ�����");
    }
    try 
    {  
         con=DriverManager.getConnection("jdbc:microsoft:sqlserver://localhost:1433;DatabaseName=testDatabse","sa","");
         sql=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
         rs=sql.executeQuery("SELECT * FROM userTable");
         int countRecord=0;//��¼����
         int countPageRecord=0;//ÿҳ��¼����
         int countPage=0;//��ҳ��
         countPageRecord=5;//ÿҳ5����¼��Ҫ����ÿҳ��¼�����͸������������ֵ
         //�õ���¼������
         rs.last();
         countRecord=rs.getRow();
         //�õ���ҳ��
         if(countRecord/countPageRecord==0)
            countPage=countRecord/countPageRecord;
         else
            countPage=countRecord/countPageRecord+1;
         //�Ѽ�¼ָ��������ǰҳ��һ����¼֮ǰ
         if((dipage-1)*countPageRecord==0)
            rs.beforeFirst();
         else
            rs.absolute((dipage-1)*countPageRecord);
         out.print("<Table Border style='font-size: 10pt'>");
         out.print("<TR><td colspan=8 align=center>�û�����</td></tr>");   
         out.print("<TR>");
            out.print("<Td width=60 >"+"�û�ID��");
            out.print("<Td width=50 >"+"�û���");
            out.print("<Td width=100>"+"�û���ʵ����");
            out.print("<Td width=40>"+"����");
            out.print("<Td width=40>"+"�Ա�");
            out.print("<Td width=100>"+"��ϵ��ַ");
            out.print("<Td width=100>"+"��ϵ�绰");
            out.print("<Td width=100>"+"���ʱ��");
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
        if(i>=countPageRecord) break; //��ǰҳ��ʾ�꣬���˳�ѭ��
        }
        out.print("<TR><td colspan=8 align=center>");
           out.print("��"+countRecord+"����¼,��"+countPage+"ҳ����ǰ��"+dipage+"ҳ��ÿҳ"+countPageRecord+"����¼��");
           if(dipage==1)//��ǰ����ҳ
              ;
           else//��ǰ������ҳ
           {
              out.print("<a href=userPage1.jsp?dipage=1>��ҳ</a>��");
              out.print("<a href=userPage1.jsp?dipage="+(dipage-1)+">��һҳ</a>,");
           }
           if(dipage==countPage)//��ǰ��ĩҳ
              ;
           else//��ǰ����ĩҳ
           {
              out.print("<a href=userPage1.jsp?dipage="+(dipage+1)+">��һҳ</a>,");
              out.print("<a href=userPage1.jsp?dipage="+countPage+">ĩҳ</a>");
           }          
           
           out.print("</td></tr>"); 
        out.print("</Table>");
        con.close();
     }
   catch(SQLException e1) 
   {
      out.print("SQL�쳣��");
   }
 %>
</BODY>
</HTML>