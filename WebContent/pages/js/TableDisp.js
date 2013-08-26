/*
 * author:	xiaokai
 * date:	20121108
 * function:设置当前显示页面链接颜色为红色：  <上一页  ... 12  13  14  15  16  共16页 
*/
function setCurrentPageIdRed(currentDispage,pageNum){
	
	if(pageNum<=5){
		var elementId="a_"+currentDispage;
		document.getElementById(elementId).style.color="red";
	}
	else{
		if(currentDispage<=3){
			var elementId="a_"+currentDispage;
			document.getElementById(elementId).style.color="red";
		}
		else if(currentDispage>=pageNum-2){
			var	elementId="a_"+(5+currentDispage-pageNum);
			document.getElementById(elementId).style.color="red";
		}
		else{}		
	}

}
/*
 * author：	xiaokai
 * date：	20121108
 * Function：判断NUM是否为数字
*/
function fucCheckNUM(NUM){
	var i,j,strTemp;
	strTemp="0123456789";
	if ( NUM.length== 0)
	return 0
	for (i=0;i<NUM.length;i++)
	{
		j=strTemp.indexOf(NUM.charAt(i)); 
		if (j==-1)
		{
			//说明有字符不是数字
			return 0;
		}
	}
	//说明是数字
	return 1;
}
/*
 * author:	xiaokai
 * date:	20121108
 * function:获取ID为input_refferToNum中的内容，并且验证输入框内容合法性，如果合法跳转页面
 * 
*/
function	refferTo(pageNum){
	
	var	refferToNum	=	document.getElementById("input_refferToNum").value;
	if(refferToNum==""){
		alert("请输入跳转页数");
		document.getElementById("input_refferToNum").focus();
	}
	else if(fucCheckNUM(refferToNum)==0){
		alert("页数必须是数字")
		document.getElementById("input_refferToNum").focus();
	}
	else if(refferToNum>pageNum|refferToNum<=0){
		alert("数值范围应在1-"+pageNum+"之间");
		document.getElementById("input_refferToNum").focus();
	}
	else{
		document.getElementById("sure").href="turnTable.jsp?dipage="+refferToNum;
	}
}