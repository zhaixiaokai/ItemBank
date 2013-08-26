/*
 * author：xiaokai
 * Function：改变节点状态，是否显示子节点，收起或者展开节点功能
 * Usingcondition：节点结构如<a><span><img></span></a>时放可使用
*/
function ChangeStatus(o) {
		//alert(o.firstChild.firstChild.src);
		/*
		 * <a href="#" onclick="javascript:ChangeStatus(this);"><span><img class=s src="../source/ad.png" />信息与通信学院</span></a>
		*/
		if (o.parentNode) {
			if (o.parentNode.className == "Opened") {
				o.parentNode.className = "Closed";
				o.firstChild.firstChild.src= "../source/ar.png";
/*				var source = event.srcElement;
				alert(source.firstChild);*/
				source.src = "../source/ar.png";
			} else if (o.parentNode.className == "Closed"){
				o.parentNode.className = "Opened";
/*				var source = event.srcElement;
				source.src = "../source/ad.png";*/
				o.firstChild.firstChild.src= "../source/ad.png";
			}
			else {
				o.parentNode.className = "Closed";
				o.firstChild.firstChild.src= "../source/ar.png";		
			}
			
		}
	}