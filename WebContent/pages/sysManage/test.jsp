<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../css/tree.css">
<meta name="GENERATOR" content="Microsoft Visual Studio .NET 7.1">
<meta name="ProgId" content="VisualStudio.HTML">
<meta name="Originator" content="Microsoft Visual Studio .NET 7.1">
<link type="text/css" href="css/tree.css" rel="stylesheet">
<script language=jscript>
   function ChangeStatus(o)
   {
    if (o.parentNode)
    {
     if (o.parentNode.className == "Opened")
     {
      o.parentNode.className = "Closed";
     }
     else
     {
      o.parentNode.className = "Opened";
     }
    }
   }
   </script>
</head>
<body>
	<div class="TreeMenu" id="CategoryTree">
		<h4>CSS树形菜单</h4>
		<ul>
			<li class="Opened"><img class=s src="css/s.gif"
				onclick="javascript:ChangeStatus(this);"><a href="#">根节点</a>
				<ul>
					<li class="Opened"><img class=s src="css/s.gif"
						onclick="javascript:ChangeStatus(this);"><a href="#">我的文档</a>
						<ul>
							<li class="Opened"><img class=s src="css/s.gif"
								onclick="javascript:ChangeStatus(this);"><a href="#">JavaScript</a>
								<ul>
									<li class="Child"><img class=s src="css/s.gif">&nbsp;<a
										href="#">常用小技巧</a></li>
									<li class="Child"><img class=s src="css/s.gif">&nbsp;<a
										href="#">围绕鼠标的文字</a></li>
									<li class="Child"><img class=s src="css/s.gif">&nbsp;<a
										href="#">OWC图表</a></li>
									<li class="Child"><img class=s src="css/s.gif">&nbsp;<a
										href="#">锁定表列</a></li>
									<li class="Child"><img class=s src="css/s.gif">&nbsp;<a
										href="#">B/S常用技巧</a></li>
									<li class="Child"><img class=s src="css/s.gif">&nbsp;<a
										href="#">操作xml</a></li>
									<li class="Child"><img class=s src="css/s.gif">&nbsp;<a
										href="#">生成guid</a></li>
								</ul></li>
						</ul></li>
				</ul></li>
		</ul>
	</div>
</body>
</html>