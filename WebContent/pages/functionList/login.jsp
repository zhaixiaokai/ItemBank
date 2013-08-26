<%@page import="net.ib.config.SessionData"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<table style="margin: 5px">
  <tr>
    <td>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="left_body" style="color: #687f96;">
  <%if(session.getAttribute(SessionData.SESSION_USERNAME)==null) {%>
  <tr >
    <td class="left_title">用户登录</td>
  </tr>
  <tr>
    <td>
    <form name="loginAction" id="loginAction">
       <table width="192px" border="0" cellspacing="0" cellpadding="0" style=" font-size:12px;margin-left: 20px">
         <tr>
           <td width="25%" height="37" valign="middle">用户名：</td>
           <td width="75%" valign="middle">
             <input type="text" name="username" id="username" class="input_1"/>
           </td>
         </tr>
         <tr>
           <td height="37" valign="middle">密　码：</td>
           <td valign="middle">
             <input type="password" name="password" id="password" class="input_2"/>
           </td>
         </tr>
         <tr>
           <td height="37" valign="middle">验证码：</td>
           <td valign="middle">
             <input type="text" name="check" id="check" class="input_3" style="float:left"/>
             <span style="float:left; margin-left:6px;">
             <img id="checkPic" src="../../servJsp/CreateCheckCode.jsp" onclick="resetCheckIMG()"/></span></td>
         </tr>
         <tr>
         <td height="37" colspan="2">用户类型：
             <select name="usertype">
    		<option value="0">请选择</option>
    		<option value="teacher">教师</option>
    		<option value="student">学生</option>
    	</select>
         </td>
         </tr>
         <tr>
           <td colspan="2" align="center">
             <input name="input" type="button" class="button" value="登录" onclick="login()" />
             <input name="input" type="button" class="button" value="忘记密码" />
           </td>
         </tr>
       </table>
       </form>
	</td>
  </tr>
  <%}else{ %>
  <tr >
    <td class="left_title">用户信息</td>
  </tr>
  <tr>
  	<td height="100px;"><div style="margin: 25px;">欢迎使用！<br>用户：<%=session.getAttribute(SessionData.SESSION_USERNAME) %><br><br>
  	<a href="updatePsw.jsp" target="_blank">修改密码</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="#" onclick="logout()">退出登录</a>
  	</div></td>
  	
  </tr>
  <%} %>
  <tr>
    <td class="left_title">本站公告</td>
  </tr>
  <tr>
    <td class="menu">
      <ul>
        <li><a target="mainFrame" href="../frame/NewsFile/Notice.jsp">关于期末考试的通知</a></li>
        <li><a target="mainFrame" href="../frame/NewsFile/Notice.jsp">信息与通信工程学院《数字电路》课程试题更新</a></li>
        <li><a target="mainFrame" href="../frame/NewsFile/Notice.jsp">最新考试信息</a></li>
      </ul>    
    </td>
  </tr>
  <tr>
    <td class="left_title">资料下载</td>
  </tr>
  <tr>
    <td class="menu">
      <ul>
        <li><a target="mainFrame" href="../frame/NewsFile/Download.jsp">最新添加试题</a></li>
        <li><a target="mainFrame" href="../frame/NewsFile/Download.jsp">模拟试卷</a></li>
      </ul>
	</td>
  </tr>
  <tr>
  	<td class="left_title">产品信息</td>
  </tr>
  <tr>
  	<td class="menu">
      <ul>
        <li><a target="mainFrame" href="../frame/NewsFile/infoRelease.jsp">web信息发布</a></li>
        <li><a target="mainFrame" href="../frame/NewsFile/infoView.jsp">web信息查看</a></li>
        <li><a href="#">试卷发布</a></li>
      </ul>  	
  	</td>
  </tr>
</table>
	</td>
  </tr>
</table>