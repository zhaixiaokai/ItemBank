/*
 * author��xiaokai
 * Function���ı�ڵ�״̬���Ƿ���ʾ�ӽڵ㣬�������չ���ڵ㹦��
 * Usingcondition���ڵ�ṹ��<a><span><img></span></a>ʱ�ſ�ʹ��
*/
function ChangeStatus(o) {
		//alert(o.firstChild.firstChild.src);
		/*
		 * <a href="#" onclick="javascript:ChangeStatus(this);"><span><img class=s src="../source/ad.png" />��Ϣ��ͨ��ѧԺ</span></a>
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