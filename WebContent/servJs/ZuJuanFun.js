//����arr�еĸ���
function pushData(arr,par){
	return arr.push(par);
}
//��������
function saveConfig(type){
	var	inputs	=	$('.count');
	//�ж��Ƿ�Ϸ� 
	for(var	i=0;i<inputs.length;i++){
		if(!CheckIfIsInteger(inputs[i].value)){
			alert("�������ֻ��Ϊ���֣�");
			return;
		}
	}
	if(type=="xuanze"){
		for(var i=0;i<inputs.length;i++){
			if(XZIDarr.length==0){

				pushData(XZIDarr,inputs[i].id);
				pushData(XZCount,inputs[i].value);
			}
			else{
				XZIDarr.splice(i,1,inputs[i].id);
				XZCount.splice(i,1,inputs[i].value);
			}
		}
	}else if(type=="panduan"){
		for(var i=0;i<inputs.length;i++){
			if(PDIDarr.length==0){
				pushData(PDIDarr,inputs[i].id);
				pushData(PDCount,inputs[i].value);
			}
			else{
				PDIDarr.splice(i,1,inputs[i].id);
				PDCount.splice(i,1,inputs[i].value);	
			}
			
		}
	}else if(type=="jianda"){
		for(var i=0;i<inputs.length;i++){
			if(JDIDarr.length==0){
				pushData(JDIDarr,inputs[i].id);
				pushData(JDCount,inputs[i].value);
			}
			else{
				JDIDarr.splice(i,1,inputs[i].id);
				JDCount.splice(i,1,inputs[i].value);
			}
		}
	}else if(type=="tiankong"){
		for(var i=0;i<inputs.length;i++){
			if(TKIDarr.length==0){

				pushData(TKIDarr,inputs[i].id);
				pushData(TKCount,inputs[i].value);
			}
			else{
				TKIDarr.splice(i,1,inputs[i].id);
				TKCount.splice(i,1,inputs[i].value);
			}
		}
	}else{
		alert("��֧�ֵ�����");
	}
	ClearFade();
}