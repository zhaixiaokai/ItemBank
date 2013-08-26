<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<style type="text/css">
.楷体 {
	font-family: 楷体;
}

.zitiLittle {
	font-size: 9px;
}

.zitiMiddle {
	font-size: 10px;
}

a {
	text-decoration: none;
	color: #51bfe0
}

A:hover {
	font-size: 23;
	COLOR: #C03;
	TEXT-DECORATION: underline;
}

a:link {
	color: #000
}

a:visited {
	color: #000
}
</style>
<script type="text/javascript">
	var	bgcolor=null;
  function color_move_on(obj, c)
  {
    obj.style.color = c;
  }

  function color_move_out(obj, c)
  {
    obj.style.color = c;
  }
  function onClickHomePage(){
  	self.parent.frames["mainFrame"].location="../functionList/HomePage.html";
	self.parent.frames["leftFrame"].location="../functionList/login.html";
  }
</script>
</head>

<body background="../source/header_little.jpg" leftmargin="0"
	rightmargin="0" bottommargin="0">

	<div>
		<table width="1024px" border="0" cellspacing="0" cellpadding="0">
			<tr height="117px">
				<td>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="450px"></td>
							<td width="280px">&nbsp;</td>
							<td width="38%">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td align="center" width="223px">欢迎使用：xiaokai</td>
										<td align="center" class="楷体"><span class="zitiLittle"><a
												target="leftFrame" href="../functionList/login.jsp">退出登录</a></span></td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td align="center" class="楷体"><span class="zitiLittle"><a
												href="#"
												onclick="this.style.behavior='url(#default#homepage)';this.setHomePage(window.top.document.URL);">设为主页</a></span></td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td align="center" class="楷体"><span class="zitiLittle"><a
												target="_top"
												href="javascript:window.external.addFavorite(window.top.document.URL,'è¯é¢åºç³»ç»')">收藏本站</a></span></td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td align="center" class="楷体"><span class="zitiLittle"
											id="localtime"> <script type="text/javascript">
								function showLocale(objD)
								{
									var str,colorhead,colorfoot;
									var yy = objD.getYear();
									if(yy<1900) yy = yy+1900;
									var MM = objD.getMonth()+1;
									if(MM<10) MM = '0' + MM;
									var dd = objD.getDate();
									if(dd<10) dd = '0' + dd;
									var hh = objD.getHours();
									if(hh<10) hh = '0' + hh;
									var mm = objD.getMinutes();
									if(mm<10) mm = '0' + mm;
									var ss = objD.getSeconds();
									if(ss<10) ss = '0' + ss;
									var ww = objD.getDay();
									colorhead="<font color=\"#373737\">";
									if  (ww==0)  ww="星期日";
									if  (ww==1)  ww="星期一";
									if  (ww==2)  ww="星期二";
									if  (ww==3)  ww="星期三";
									if  (ww==4)  ww="星期四";
									if  (ww==5)  ww="星期五";
									if  (ww==6)  ww="星期六";
									colorfoot="</font>"
									str = colorhead + yy + "-" + MM + "-" + dd + " " + hh + ":" + mm + ":" + ss + "  " + ww + colorfoot;
									return(str);
								}
								function tick()
								{
									var today;
									today = new Date();
									document.getElementById("localtime").innerHTML = showLocale(today);
									window.setTimeout("tick()", 1000);
								}
								tick();
							</script>

										</span></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr height="20px">
				<td>

					<table background="../source/bg_2.gif" width="100%" border="0"
						cellspacing="0" cellpadding="0">
						<tr>
							<td id="Menu_0" width="140" align="right"
								onmouseover="onMouseOver();"><a href="#"
								style="text-decoration: none; font-size: 14px"><p
										onclick="onClickHomePage();" style="color: #fff">系统首页</p></a></td>
							<td id="Menu_1" width="140" align="right"><a
								href="../functionList/appManage.html" target="leftFrame"
								style="text-decoration: none; font-size: 14px"><p
										style="color: #fff">应用管理</p></a></td>
							<td id="Menu_2" width="140" align="right"><a
								href="../functionList/paperManage.html" target="leftFrame"
								style="text-decoration: none; font-size: 14px"><p
										style="color: #fff">教学管理</p></a></td>
							<td id="Menu_2" width="140" align="right"><a
								href="../functionList/paperManage.html" target="leftFrame"
								style="text-decoration: none; font-size: 14px"><p
										style="color: #fff">试题管理</p></a></td>
							<td id="Menu_3" width="140" align="right"><a
								href="../functionList/examDbManage.html" target="leftFrame"
								style="text-decoration: none; font-size: 14px"><p
										style="color: #fff">试题库管理</p></a></td>
							<td id="Menu_4" width="140" align="right"><a
								href="../functionList/sysManage.html" target="leftFrame"
								style="text-decoration: none; font-size: 14px"><p
										style="color: #fff">系统管理</p></a></td>
							<td width="100">&nbsp;</td>
							<td width="136">&nbsp;</td>
							<td width="148">&nbsp;</td>
							<td width="148">&nbsp;</td>
							<td width="150">&nbsp;</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>
