	function	login(){
		var	username=document.loginAction.username.value;
		var	password=document.loginAction.password.value;
		var	check	=document.loginAction.check.value;
		
		if(check==""){
			alert("��������֤��");
			return;
		}
		if(username==""){
			alert("�������û���");
		}
		else if(password==""){
			alert("����������");
		}
		else{
			var password = MD5(password); // ��������������md5���ܺ���֤
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
		if(confirm("ȷ��Ҫ�˳���")){
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