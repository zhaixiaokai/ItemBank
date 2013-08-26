	function addToFav(){
	        var url = window.top.document.URL;
	        var title = "试题库系统";
	        if (window.sidebar) { // Mozilla Firefox Bookmark
	                window.sidebar.addPanel(title, url,"");
	        } else if( window.external ) { // IE Favorite
	        	try{
	        		window.external.AddFavorite( url, title);
	        	}
	        	catch(e){
	        		
	        		alert('您的浏览器不支持自动自动设置首页, 请使用浏览器菜单手动设置!');
	        	}
	                window.external.AddFavorite( url, title);
	        } else if(window.opera) { // Opera 7+
	                return false; // do nothing
	        } else { 
	                 alert('您的浏览器不支持自动自动设置首页, 请使用浏览器菜单手动设置!');
	        }
	}
	function setHomepage(){
	    if (document.all){
	        document.body.style.behavior='url(#default#homepage)';
	          document.body.setHomePage(window.location.href);
	    }else if (window.sidebar){
	        if(window.netscape){
	            try{
	                netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
	            }catch (e){
	                alert( "该操作被浏览器拒绝，如果想启用该功能，请在地址栏内输入 about:config,然后将项 signed.applets.codebase_principal_support 值该为true" );
	            }
	        }
	        var prefs = Components.classes['@mozilla.org/preferences-service;1'].getService(Components. interfaces.nsIPrefBranch);
	        prefs.setCharPref('browser.startup.homepage',window.location.href);
	    }else{
	        alert('您的浏览器不支持自动自动设置首页, 请使用浏览器菜单手动设置!');
	    }
	}
	
	
	function showLocale(objD)
	{
		var str,colorfoot;
		var yy = objD.getYear();
		if(yy<1900) yy = yy+1900;
		var MM = objD.getMonth()+1;
		if(MM<10) MM = '0' + MM;
		var dd = objD.getDate();
		if(dd<10) dd = '0' + dd;
		var hh = objD.getHours();
		if(hh<10) hh = '0' + hh;
		var mm = objD.getMinutes();
		if(mm<10) mm = '0' + mm;
		var ss = objD.getSeconds();
		if(ss<10) ss = '0' + ss;
		var ww = objD.getDay();
		if  (ww==0)  ww="星期日";
		if  (ww==1)  ww="星期一";
		if  (ww==2)  ww="星期二";
		if  (ww==3)  ww="星期三";
		if  (ww==4)  ww="星期四";
		if  (ww==5)  ww="星期五";
		if  (ww==6)  ww="星期六";
		colorfoot="</font>";
		str =yy + "-" + MM + "-" + dd + " " + hh + ":" + mm + ":" + ss + "  " + ww + colorfoot;
		return(str);
	}
	function tick()
	{
		var today;
		today = new Date();
		document.getElementById("localtime").innerHTML = showLocale(today);
		window.setTimeout("tick()", 1000);
	}