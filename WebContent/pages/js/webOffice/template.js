/****************************************************
*
* 					��ʼ���ؼ�
*
****************************************************/
// �ر��ĵ�
function unloadFile() {
	document.all.WebOffice1.SetToolBarButton2("Menu Bar",1,4);	// �ָ������εĲ˵����ļ��˵�(word)
	document.all.WebOffice1.SetToolBarButton2("Standard",1,3);	// �ָ������ε��½���
	document.all.WebOffice1.SetToolBarButton2("Standard",2,3);	// �ָ������εĴ���
	document.all.WebOffice1.SetToolBarButton2("Standard",3,3);	// �ָ������εı�����
	document.all.WebOffice1.SetKeyCtrl(595,0,0);				// �ָ������ݼ�(Ctrl+S) 
    document.all.WebOffice1.SetKeyCtrl(592,0,0);				// �ָ���ӡ��ݼ�(Ctrl+P)
	document.all.WebOffice1.Close();
}
/****************************************************
*
* 					��ʼ���ؼ�
*
****************************************************/
// ����װ���ĵ�
function LoadDocument(){
	unloadFile();					// �ر�
	WebOffice1_NotifyCtrlReady();	// �ٴ�
}
/****************************************************
*
* 			����ģ����ǩ��������ģ���ļ�
*
****************************************************/
// 
function SaveDoc(templateId,recordId,fileType) {
	// �˴���� SavaBookMarks()
	if(webform.FileName.value==""){
		alert("ģ�����Ʋ���Ϊ�գ�");
		return false;
	}
	if(webform.Descript.value==""){
		alert("ģ��˵������Ϊ�գ�");
		return false;
	}
	var returnValue;
	document.all.WebOffice1.HttpInit();			//��ʼ��Http����
	// �����Ӧ��PostԪ�� 
	document.all.WebOffice1.HttpAddPostString("TemplateId",templateId);
	document.all.WebOffice1.HttpAddPostString("RecordID", recordId);
	document.all.WebOffice1.HttpAddPostString("FileName",webform.FileName.value);
	document.all.WebOffice1.HttpAddPostString("Descript",webform.Descript.value);	
	document.all.WebOffice1.HttpAddPostString("FileType",fileType);
	document.all.WebOffice1.HttpAddPostCurrFile("FileBody","");		// �ϴ��ļ�

	// �ϴ��ļ����������Ƿ�ɹ�
	returnValue = document.all.WebOffice1.HttpPost("../template/SaveTemplate.jsp");	
	if("succeed" == returnValue){
			alert("�ļ��ϴ�ʧ��");
	} else  {
			alert("�ļ��ϴ��ɹ�");	
	}
}
/****************************************************
*
* 					�����ĵ�������
*
****************************************************/
function SaveToLocal() {
	document.all.WebOffice1.ShowDialog(10000)
}
/****************************************************
*
* 					��ӡ�ĵ�
*
****************************************************/
function PrintDoc() {
	document.all.WebOffice1.ShowDialog(88);	
}
/****************************************************
*
* 					�򿪱����ĵ�
*
****************************************************/
function OpenLocalFile(fileType) {
	document.all.WebOffice1.LoadOriginalFile("open", fileType);	
}
/****************************************************
*
* 					���嵱ǰ�ĵ���ǩ
*
****************************************************/
function DefineMarks() {
	document.all.WebOffice1.BookMarkOpt("/ListBookMarks.jsp",1);
}
/****************************************************
*
* 					���ģ��
*
****************************************************/
function FillBookMarks(){
	document.all.WebOffice1.BookMarkOpt("/FillBookMarks.jsp",2);
}