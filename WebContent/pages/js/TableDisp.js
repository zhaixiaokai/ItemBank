/*
 * author:	xiaokai
 * date:	20121108
 * function:���õ�ǰ��ʾҳ��������ɫΪ��ɫ��  <��һҳ  ... 12  13  14  15  16  ��16ҳ 
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
 * author��	xiaokai
 * date��	20121108
 * Function���ж�NUM�Ƿ�Ϊ����
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
			//˵�����ַ���������
			return 0;
		}
	}
	//˵��������
	return 1;
}
/*
 * author:	xiaokai
 * date:	20121108
 * function:��ȡIDΪinput_refferToNum�е����ݣ�������֤��������ݺϷ��ԣ�����Ϸ���תҳ��
 * 
*/
function	refferTo(pageNum){
	
	var	refferToNum	=	document.getElementById("input_refferToNum").value;
	if(refferToNum==""){
		alert("��������תҳ��");
		document.getElementById("input_refferToNum").focus();
	}
	else if(fucCheckNUM(refferToNum)==0){
		alert("ҳ������������")
		document.getElementById("input_refferToNum").focus();
	}
	else if(refferToNum>pageNum|refferToNum<=0){
		alert("��ֵ��ΧӦ��1-"+pageNum+"֮��");
		document.getElementById("input_refferToNum").focus();
	}
	else{
		document.getElementById("sure").href="turnTable.jsp?dipage="+refferToNum;
	}
}