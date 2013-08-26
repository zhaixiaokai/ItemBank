
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
<title>Dialog Quickstart Example</title>

<style type="text/css">
/*margin and padding on body element
  can introduce errors in determining
  element position and are not recommended;
  we turn them off as a foundation for YUI
  CSS treatments. */
body {
	margin:0;
	padding:0;
}
</style>

<link rel="stylesheet" type="text/css" href="../js/yui/build/fonts/fonts-min.css" />
<link rel="stylesheet" type="text/css" href="../js/yui/build/button/assets/skins/sam/button.css" />
<link rel="stylesheet" type="text/css" href="../js/yui/build/container/assets/skins/sam/container.css" />
<script type="text/javascript" src="../js/yui/build/utilities/utilities.js"></script>
<script type="text/javascript" src="../js/yui/build/button/button-min.js"></script>
<script type="text/javascript" src="../js/yui/build/container/container-min.js"></script>

<!--there is no custom header content for this example-->

</head>

<body class=" yui-skin-sam">


<div class="exampleIntro">
</div>

<!--BEGIN SOURCE CODE FOR EXAMPLE =============================== -->

<style>
#example {height:30em;}
label { display:block;float:left;width:45%;clear:left; }
.clear { clear:both; }
#resp { margin:10px;padding:5px;border:1px solid #ccc;background:#fff;}
#resp li { font-family:monospace }
</style>

<script>
YAHOO.namespace("example.container");

function init() {
	
	// Define various event handlers for Dialog
	var handleSubmit = function() {
		this.submit();
	};
	var handleCancel = function() {
		this.cancel();
	};
	var handleSuccess = function(o) {
		var response = o.responseText;
		response = response.split("<!")[0];
		document.getElementById("resp").innerHTML = response;
	};
	var handleFailure = function(o) {
		alert("Submission failed: " + o.status);
	};

	// Instantiate the Dialog
	YAHOO.example.container.dialog1 = new YAHOO.widget.Dialog("dialog1", 
							{ width : "30em",
							  fixedcenter : true,
							  visible : false, 
							  constraintoviewport : true,
							  buttons : [ { text:"Submit", handler:handleSubmit, isDefault:true },
								      { text:"Cancel", handler:handleCancel } ]
							});

	// Validate the entries in the form to require that both first and last name are entered
	YAHOO.example.container.dialog1.validate = function() {
		var data = this.getData();
		if (data.firstname == "" || data.lastname == "") {
			alert("Please enter your first and last names.");
			return false;
		} else {
			return true;
		}
	};

	// Wire up the success and failure handlers
	YAHOO.example.container.dialog1.callback = { success: handleSuccess,
						     failure: handleFailure };
	
	// Render the Dialog
	YAHOO.example.container.dialog1.render();

	YAHOO.util.Event.addListener("show", "click", YAHOO.example.container.dialog1.show, YAHOO.example.container.dialog1, true);
	YAHOO.util.Event.addListener("hide", "click", YAHOO.example.container.dialog1.hide, YAHOO.example.container.dialog1, true);
}

YAHOO.util.Event.onDOMReady(init);
</script>

<div>
<button id="show">Show dialog1</button> 
<button id="hide">Hide dialog1</button>
</div>

<div id="dialog1">
<div class="hd">Please enter your information</div>
<div class="bd">
<form method="POST" action="assets/post.php">
	<label for="firstname">First Name:</label><input type="textbox" name="firstname" />
	<label for="lastname">Last Name:</label><input type="textbox" name="lastname" />
	<label for="email">E-mail:</label><input type="textbox" name="email" /> 

	<label for="state[]">State:</label>
	<select multiple name="state[]">
		<option value="California">California</option>
		<option value="New Jersey">New Jersey</option>
		<option value="New York">New York</option>
	</select> 

	<div class="clear"></div>

	<label for="radiobuttons">Radio buttons:</label>
	<input type="radio" name="radiobuttons[]" value="1" checked/> 1
	<input type="radio" name="radiobuttons[]" value="2" /> 2
	
	<div class="clear"></div>

	<label for="check">Single checkbox:</label><input type="checkbox" name="check" value="1" /> 1
	
	<div class="clear"></div>
		
	<label for="textarea">Text area:</label><textarea name="textarea"></textarea>

	<div class="clear"></div>

	<label for="cbarray">Multi checkbox:</label>
	<input type="checkbox" name="cbarray[]" value="1" /> 1
	<input type="checkbox" name="cbarray[]" value="2" /> 2
</form>
</div>
</div>

<div id="resp">Server response will be displayed in this area</div>	

<!--END SOURCE CODE FOR EXAMPLE =============================== -->

</body>
</html>