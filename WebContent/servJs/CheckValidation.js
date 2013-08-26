/* 
 * ��;����������ַ����Ƿ�ֻ����ĸ�����֡��»������ 
 * ���룺 value���ַ��� 
 * ���أ� ���ͨ����֤����true,���򷵻�false 
 */
function CheckIfIsLetter_Number(s) {
	var regu = "^[0-9a-zA-Z\_]+$";
	var re = new RegExp(regu);
	return re.test(s);
}
/*
 * ��;����������ַ����Ƿ�ֻ�ɺ��֡���ĸ��������� 
 * ���룺 value���ַ��� 
 * ���أ� ���ͨ����֤����true,���򷵻�false
 */

function CheckIfChinaNumbLetter(s) {
	var regu = "^[0-9a-zA-Z\u4e00-\u9fa5\_]+$";
	var re = new RegExp(regu);
	return re.test(s);
}
/*
 * ��;����������ַ����Ƿ�ֻ����ĸ��������� 
 * ���룺 value���ַ���
 *  ���أ� ���ͨ����֤����true,���򷵻�false
 */
function CheckIfIsLetterNumber(s) {
	var regu = "^[0-9a-zA-Z]+$";
	var re = new RegExp(regu);
	return re.test(s);
}
/*
 * ��;����������ַ����Ƿ�ֻ����ĸ��� 
 * ���룺 value���ַ���
 *  ���أ� ���ͨ����֤����true,���򷵻�false
 */
function CheckIfIsLetter(s) {
	var regu = "^[a-zA-Z]+$";
	var re = new RegExp(regu);
	return re.test(s);
}
/* 
��;����������ַ����Ƿ�ֻ�ɺ������ 
���룺 
s���ַ��� 
���أ� 
���ͨ����֤����true,���򷵻�false 
*/ 
function isZh(str){ 
 var reg = /^[\u4e00-\u9fa5]+$/;
 return reg.test(str);
}
/*
 * ��;�������������ֵ�Ƿ����������ʽ
 *  ���룺str ������ַ��� 
 *  ���أ����ͨ����֤����true,���򷵻�false
 */
function CheckIfIsInteger(str) {
	var regu = /^[-]{0,1}[0-9]{1,}$/;
	return regu.test(str);
}
/*
 * ��;����������ַ����Ƿ������������ʽ 
 * ���룺 s���ַ���
 * ���أ� ���ͨ����֤����true,���򷵻�false
 */
function CheckIfIsNumber(s) {
	var regu = "^[0-9]+$";
	var re = new RegExp(regu);
	/*if (s.search(re) != -1) {
		return true;
	}
	else 
		return false;*/
	return re.test(s);
}
/* 
��;�������������ֵ�Ƿ����Email��ʽ 
���룺str ������ַ��� 
���أ����ͨ����֤����true,���򷵻�false 
*/ 
function isEmail(str) { 
var myReg = /^[-_A-Za-z0-9]+@([_A-Za-z0-9]+\.)+[A-Za-z0-9]{2,3}$/; 
if (myReg.test(str)) return true; 
return false; 
} 
/* 
��;����������ַ����Ƿ���Ϲ��ڹ̻����ߴ����ʽ 
���룺 
s���ַ���  ��ʽ���磺020-87110252
���أ� 
���ͨ����֤����true,���򷵻�false 
*/ 
function isTel(s){
  var reg=/^((0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/; 
 return reg.test(s);
}
/* 
��;����������ֻ������Ƿ���ȷ 
���룺 
s���ַ��� 
���أ� 
���ͨ����֤����true,���򷵻�false 
*/ 
function checkMobile(s) { 
var regu = /^[1][0-9][0-9]{9}$/; 
var re = new RegExp(regu); 
return re.test(s);
}

/* 
��;����������ַ����Ƿ����13λISBN���ʱ�׼��Ÿ�ʽ 
���룺 
s���ַ���  ��ʽ���磺978-7-302-17884-2
���أ� 
���ͨ����֤����true,���򷵻�false 
*/ 
function isISBN(s){
  var reg=/^\d{3}-\d-\d{3}-\d{5}-\d$/; 
 return reg.test(s);
}

/*
 * �ж��Ƿ�Ϊ����
 */
function isFigure(s){
	var reg=/\d/; 
	return reg.test(s);
}
/* 
��;����������ַ����Ƿ�������֤��ʽ 
���룺 
s���ַ��� 
���أ� 
���ͨ����֤����true,���򷵻�false 
*/
function isIDno(strIDno){

/*var aCity = { 11: "����", 12: "���", 13: "�ӱ�", 14: "ɽ��", 
		15: "���ɹ�", 21: "����", 22: "����", 23: "������", 31: "�Ϻ�", 
		32: "����", 33: "�㽭", 34: "����", 35: "����", 36: "����", 
		37: "ɽ��", 41: "����", 42: "����", 43: "����", 44: "�㶫", 
		45: "����", 46: "����", 50: "����", 51: "�Ĵ�", 52: "����", 
		53: "����", 54: "����", 61: "����", 62: "����", 63: "�ຣ", 
		64: "����", 65: "�½�", 71: "̨��", 81: "���", 82: "����", 91: "����" };
var iSum = 0;
var info = "";
var strIDno = value;
var idCardLength = strIDno.length;*/


var myRegExp=/^\d{17}[\d|x]$|^\d{15}$/i;
return myRegExp.test(strIDno);

}
//������ʽ�ж��Ƿ�ΪС��
function isDecimal(s) {
 var regu = "^(([0-9]+[\.]?[0-9]+)|[1-9])$"; // С������
 var re = new RegExp(regu);
 if (s.search(re) != -1)
  return true;
 else
  return false;
}
