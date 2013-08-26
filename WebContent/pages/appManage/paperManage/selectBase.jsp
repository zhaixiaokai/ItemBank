<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../css/multimenu.css" rel="stylesheet" type="text/css" />
<link href="../../css/MainFrame.css" rel="stylesheet" type="text/css" />
<link href="../../css/MainFrame.css" rel="stylesheet" type="text/css" />
<link href="../../css/infoedit.css" rel="stylesheet" type="text/css" />
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<style type="text/css">
</style>
<script type="text/javascript">
function orelat(x){
   var o=document.getElementById(x);
   var li=o.getElementsByTagName('li');
   for(var i=0;i<li.length;i++){
         li[i].onmouseover=new Function("orelatf1(this)");
        li[i].onmouseout=new Function("orelatf2(this)");
   }
}
function orelatf1(o){
      var ul=o.getElementsByTagName('ul'); 
    if(o.className.indexOf('relat_li')>=0)o.style.backgroundColor='#84C1FF';//class值，主菜单划过背景色
    if(ul[0]){
    var class1=o.className;
    o.className=class1+' hovechild';
    ul[0].style.display='block';
    }
    
}
function orelatf2(o){
   var class2=o.className;
   if(o.className.indexOf('relat_li')>=0){o.style.backgroundColor="#84C1FF";}//class值，主菜单划过背景色
   if(class2.indexOf('relat_li')<0){//class值
      o.className='';
   }
   var ul=o.getElementsByTagName('ul'); if(ul[0]){ul[0].style.display='none';}
} 
</script>
</head>
<body >
	<div id="act_top"><a href="#">组卷管理</a>&nbsp;&gt;&gt;&nbsp;<a href="#">手动组卷</a>&nbsp;&gt;&gt;&nbsp;<a href="#">选择题库</a></div>
		<table id="divLine" height="4px" width="100%" border="0" cellspacing="0" cellpadding="0">
     		 <tr>
       		 	<td style="border:0"></td>
     		 </tr>
    	</table>
    <div class="div_style1">
		<table width="100%" class="CContent">
			<tr>
				 <td class="CPanel">
					<table border="0" cellpadding="0" cellspacing="0" style="width:100%">
						<tr><td align="left">
							<br>
							>>请选择目标试题库：
						</td></tr>
						<tr><td width="100%">
							<ul id="howl" class="relat">
   							<li class="relat_li">选择课程
   								<ul class="relat_ul" style="display:none;">
    								<li ><a href="#">信息与通信工程学院</a>
    									<ul style="display:none;">
        									<li><a href="#">030102 大学英语</a></li>
        									<li><a href="handing.html">030103 数据库应用技术</a></li>
        									<li><a href="#">030104 通信原理</a></li>
        									<li><a href="#">030105 通信电子电路</a></li>
        									<li><a href="#">030106 电子电路基础</a></li>
       										<li><a href="#">030107 数字电路与逻辑设计</a></li>
       			 						</ul>
    								<li><a href="#">人文学院</a>
    									<ul style="display:none;">
        									<li><a href="#">010101 马克思主义哲学</a></li>
        									<li><a href="#">010102 中国哲学</a></li>
        									<li><a href="#">010103 外国哲学</a></li>
        									<li><a href="#">010104 逻辑学</a></li>
        									<li><a href="#">010105 伦理学</a></li>
       										<li><a href="#">010106 美学</a></li>
       			 						</ul>
    								<li><a href="#">经济管理学院</a>
    									<ul style="display:none;">
        									<li><a href="#">0201 理论经济学</a></li>
        									<li><a href="#">020101 政治经济学</a></li>
        									<li><a href="#">020103 经济史</a></li>
        									<li><a href="#">020104 西方经济学</a></li>
        									<li><a href="#">020105 世界经济</a></li>
       										<li><a href="#">020102 经济思想史</a></li>
       			 						</ul>
    								<li><a href="#">教育学</a>
 										<ul style="display:none;">
     										<li><a href="#">菜单三</a></li>
     										<li><a href="#">菜单三</a></li>
     										<li><a href="#">菜单三</a></li>
     										<li><a href="#">菜单三</a></li>
     										<li><a href="#">菜单三</a></li>
    										<li><a href="#">菜单三</a></li>
    			 						</ul>
    								<li><a href="#">文学</a>
    									<ul style="display:none;">
        									<li><a href="#">菜单三</a></li>
        									<li><a href="#">菜单三</a></li>
        									<li><a href="#">菜单三</a></li>
        									<li><a href="#">菜单三</a></li>
        									<li><a href="#">菜单三</a></li>
       										<li><a href="#">菜单三</a></li>
       			 						</ul>
    								<li><a href="#">历史学</a>
    									<ul style="display:none;">
        									<li><a href="#">菜单三</a></li>
        									<li><a href="#">菜单三</a></li>
        									<li><a href="#">菜单三</a></li>
        									<li><a href="#">菜单三</a></li>
        									<li><a href="#">菜单三</a></li>
       										<li><a href="#">菜单三</a></li>
       			 						</ul>
    								<li><a href="#">理学</a>
         								<ul style="display:none;">
        									<li><a href="#">菜单三</a></li>
        									<li><a href="#">菜单三</a></li>
        									<li><a href="#">菜单三</a></li>
        									<li><a href="#">菜单三</a></li>
        									<li><a href="#">菜单三</a></li>
       										<li><a href="#">菜单三</a></li>
       			 						</ul>
    								</li>
    							</ul>
   							</li>
						</ul>
				</td></tr>	
			</table>
		
		</td>
	</tr>
</table>
</div>

			

		

<script type="text/javascript">
orelat('howl');
</script>
</body>
</html>