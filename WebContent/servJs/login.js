	function	login(){
		var	username=document.loginAction.username.value;
		var	password=document.loginAction.password.value;
		var	check	=document.loginAction.check.value;
		
		if(check==""){
			alert("请输入验证码");
			return;
		}
		if(username==""){
			alert("请输入用户名");
		}
		else if(password==""){
			alert("请输入密码");
		}
		else{
			var password = MD5(password); // 对输入的密码进行md5加密后验证
			document.loginAction.password.value=password;
			//loginAction.submit();
			$(function() {
				var options = {
					type : "post",
					dataType : "json",
					url: "loginAction",
					success : function(result){
						if(result.result!="loginSuccess"){
							alert(result.text);
							document.loginAction.password.value="";
							document.getElementById("checkPic").src="../../servJsp/CreateCheckCode.jsp";
						}
						else{
							document.location.href="../../login/HomePage.jsp";
						}
					}
				};
				$('#loginAction').ajaxSubmit(options);
			});
		}
	}
	function resetCheckIMG(){
		//alert("reset");
		document.getElementById("checkPic").src="../../servJsp/CreateCheckCode.jsp";
	}
	
	function logout(){
		if(confirm("确定要退出？")){
			$.ajax({
				type : "post",
				dataType : "json",
				async : false,
				url : "LogoutAction",
				success : function() {
					document.location.href="../../login/HomePage.jsp";
				}
			});
		}
	}