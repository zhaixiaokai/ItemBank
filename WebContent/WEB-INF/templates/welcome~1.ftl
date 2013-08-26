<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>��ӭ!</title>
	</head>
	<body>
		<#-- Greet the user with his/her name -->
		<h1>��ӭ ${user}!</h1>
		<p>We have these animals:
		<ul>
			<#list animals as being>
			<li>${being.name} for ${being.price} Euros!
			</#list>
  		</ul>
	</body>
</html>