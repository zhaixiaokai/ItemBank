/********
 * checkbox全选函数
 * @param obj		全选框对象
 * @param cName 	被重置的复选框的name
 */
function check_all(obj,cName)
{
	var checkboxs = document.getElementsByName(cName);
	for(var i=0;i<checkboxs.length;i++){checkboxs[i].checked = obj.checked;}
}