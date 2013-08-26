<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="css/multimenu.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="../css/MainFrame.css">
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
<body>
	<div style="margin-top: 10px">
		<table width="100%" class="table1">
			<tr>
				<th class="pagehead">在线添加试题</th>
			</tr>
			<tr>
				<td>
					<table border="0" cellpadding="0" cellspacing="0"
						style="width: 100%">
						<tr>
							<td align="left" style="font-size: 12px">&nbsp;&nbsp;当前试题库：学科&nbsp;>&nbsp;法学&nbsp;>&nbsp;030102法律史</td>
							<td align="right"><input type="button" name="select"
								value="更改试题库" class="button" onclick="window.history.go(-1);" /></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td width="100%">
					<table width="100%">
						<tr>
							<td>
								<fieldset style="height: 100%;">
									<legend style="font-size: 12px">添加试题</legend>
									<table border="0" cellpadding="2" cellspacing="1"
										style="width: 80%; font-size: 12px">
										<tr>
											<td nowrap align="right" width="100">试题标题:</td>
											<td><input name="text" class="text"
												style="width: 150px" type="text" size="40" /> <span
												class="red"> *</span></td>
										</tr>
										<tr>
											<td nowrap align="right">难度等级:</td>
											<td><select name="select">
													<option selected="selected">--请选择--</option>
													<option>简单</option>
													<option>一般</option>
													<option>中等</option>
													<option>困难</option>
											</select></td>
										</tr>
										<tr>
											<td nowrap align="right">知识点:</td>
											<td><select name="select2">
													<option selected="selected">--请选择--</option>
													<option>知识点a</option>
													<option>知识点b</option>
													<option>知识点c</option>
													<option>知识点d</option>
													<option>知识点f</option>
											</select></td>
										</tr>
										<tr>
											<td align="right" nowrap>题型：</td>
											<td><select name="select2">
													<option selected="selected">--请选择--</option>
													<option onclick="multichoice.jsp">选择题</option>
													<option>填空题</option>
													<option>判断题</option>
													<option>简答题</option>
											</select></td>
										</tr>

										<tr>
											<td nowrap align="right" height="120px">试题内容:</td>
											<td colspan="3"><textarea id="textarea" name="textarea"
													rows="5" cols="70"></textarea></td>
										</tr>
										<tr>
											<td nowrap align="right" height="100px">试题答案:</td>
											<td colspan="3"><textarea id="textarea" name="textarea"
													rows="4" cols="70"></textarea></td>
										</tr>
										<tr>
											<td nowrap align="right" height="100px">备注:</td>
											<td colspan="3"><textarea id="textarea" name="textarea"
													rows="2" cols="70"></textarea></td>
										</tr>
									</table>
								</fieldset>
							</td>
						</tr>
					</table>
				</td>
			</tr>

			<tr>
				<td align="center"><br> <input type="button" name="Submit"
					value="保存" class="button" onclick="save();" /> <input type="button"
					name="Submit2" value="返回" class="button"
					onclick="window.history.go(-1);" /></td>
			</tr>
		</table>
	</div>
	<script type="text/javascript">
orelat('howl');
</script>
</body>
</html>