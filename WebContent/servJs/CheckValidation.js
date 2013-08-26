/* 
 * 用途：检查输入字符串是否只由字母、数字、下划线组成 
 * 输入： value：字符串 
 * 返回： 如果通过验证返回true,否则返回false 
 */
function CheckIfIsLetter_Number(s) {
	var regu = "^[0-9a-zA-Z\_]+$";
	var re = new RegExp(regu);
	return re.test(s);
}
/*
 * 用途：检查输入字符串是否只由汉字、字母、数字组成 
 * 输入： value：字符串 
 * 返回： 如果通过验证返回true,否则返回false
 */

function CheckIfChinaNumbLetter(s) {
	var regu = "^[0-9a-zA-Z\u4e00-\u9fa5\_]+$";
	var re = new RegExp(regu);
	return re.test(s);
}
/*
 * 用途：检查输入字符串是否只由字母、数字组成 
 * 输入： value：字符串
 *  返回： 如果通过验证返回true,否则返回false
 */
function CheckIfIsLetterNumber(s) {
	var regu = "^[0-9a-zA-Z]+$";
	var re = new RegExp(regu);
	return re.test(s);
}
/*
 * 用途：检查输入字符串是否只由字母组成 
 * 输入： value：字符串
 *  返回： 如果通过验证返回true,否则返回false
 */
function CheckIfIsLetter(s) {
	var regu = "^[a-zA-Z]+$";
	var re = new RegExp(regu);
	return re.test(s);
}
/* 
用途：检查输入字符串是否只由汉字组成 
输入： 
s：字符串 
返回： 
如果通过验证返回true,否则返回false 
*/ 
function isZh(str){ 
 var reg = /^[\u4e00-\u9fa5]+$/;
 return reg.test(str);
}
/*
 * 用途：检查输入对象的值是否符合整数格式
 *  输入：str 输入的字符串 
 *  返回：如果通过验证返回true,否则返回false
 */
function CheckIfIsInteger(str) {
	var regu = /^[-]{0,1}[0-9]{1,}$/;
	return regu.test(str);
}
/*
 * 用途：检查输入字符串是否符合正整数格式 
 * 输入： s：字符串
 * 返回： 如果通过验证返回true,否则返回false
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
用途：检查输入对象的值是否符合Email格式 
输入：str 输入的字符串 
返回：如果通过验证返回true,否则返回false 
*/ 
function isEmail(str) { 
var myReg = /^[-_A-Za-z0-9]+@([_A-Za-z0-9]+\.)+[A-Za-z0-9]{2,3}$/; 
if (myReg.test(str)) return true; 
return false; 
} 
/* 
用途：检查输入字符串是否符合国内固话或者传真格式 
输入： 
s：字符串  格式例如：020-87110252
返回： 
如果通过验证返回true,否则返回false 
*/ 
function isTel(s){
  var reg=/^((0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/; 
 return reg.test(s);
}
/* 
用途：检查输入手机号码是否正确 
输入： 
s：字符串 
返回： 
如果通过验证返回true,否则返回false 
*/ 
function checkMobile(s) { 
var regu = /^[1][0-9][0-9]{9}$/; 
var re = new RegExp(regu); 
return re.test(s);
}

/* 
用途：检查输入字符串是否符合13位ISBN国际标准书号格式 
输入： 
s：字符串  格式例如：978-7-302-17884-2
返回： 
如果通过验证返回true,否则返回false 
*/ 
function isISBN(s){
  var reg=/^\d{3}-\d-\d{3}-\d{5}-\d$/; 
 return reg.test(s);
}

/*
 * 判断是否为数字
 */
function isFigure(s){
	var reg=/\d/; 
	return reg.test(s);
}
/* 
用途：检查输入字符串是否符合身份证格式 
输入： 
s：字符串 
返回： 
如果通过验证返回true,否则返回false 
*/
function isIDno(strIDno){

/*var aCity = { 11: "北京", 12: "天津", 13: "河北", 14: "山西", 
		15: "内蒙古", 21: "辽宁", 22: "吉林", 23: "黑龙江", 31: "上海", 
		32: "江苏", 33: "浙江", 34: "安徽", 35: "福建", 36: "江西", 
		37: "山东", 41: "河南", 42: "湖北", 43: "湖南", 44: "广东", 
		45: "广西", 46: "海南", 50: "重庆", 51: "四川", 52: "贵州", 
		53: "云南", 54: "西藏", 61: "陕西", 62: "甘肃", 63: "青海", 
		64: "宁夏", 65: "新疆", 71: "台湾", 81: "香港", 82: "澳门", 91: "国外" };
var iSum = 0;
var info = "";
var strIDno = value;
var idCardLength = strIDno.length;*/


var myRegExp=/^\d{17}[\d|x]$|^\d{15}$/i;
return myRegExp.test(strIDno);

}
//正则表达式判断是否为小数
function isDecimal(s) {
 var regu = "^(([0-9]+[\.]?[0-9]+)|[1-9])$"; // 小数测试
 var re = new RegExp(regu);
 if (s.search(re) != -1)
  return true;
 else
  return false;
}
