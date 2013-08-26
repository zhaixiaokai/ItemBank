<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
<title>Nested Headers</title>

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
<script type="text/javascript" src="../js/jquery-1.5.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.rotate.js"></script>
<script type="text/javascript" src="../js/jquery.form.js"></script>

<link rel="stylesheet" type="text/css" href="../js/yui/build/fonts/fonts-min.css" />
<link rel="stylesheet" type="text/css" href="../js/yui/build/datatable/assets/skins/sam/datatable.css" />
<script type="text/javascript" src="../js/yui/build/yahoo-dom-event/yahoo-dom-event.js"></script>
<script type="text/javascript" src="../js/yui/build/dragdrop/dragdrop-min.js"></script>
<script type="text/javascript" src="../js/yui/build/element/element-beta-min.js"></script>
<script type="text/javascript" src="../js/yui/build/datasource/datasource-beta-min.js"></script>
<script type="text/javascript" src="../js/yui/build/datatable/datatable-beta-min.js"></script>

<!--there is no custom header content for this example-->
<script type="text/javascript">
var	datasource=null;
function GetData(){
	$.ajax( {
		type : "post",
		dataType : "json",
		async : false,
		url : "FunctionGetTreeDataAction",
		success : function(result) {
			datasource=result;
			alert(result);
		 },
		error:function(){
		}
	});
}
</script>
</head>

<body class=" yui-skin-sam">

<h1>Nested Headers</h1>

<div class="exampleIntro">
				
</div>

<!--BEGIN SOURCE CODE FOR EXAMPLE =============================== -->

<div id="nested"></div>
<script type="text/javascript">
GetData();
</script>
<script type="text/javascript">
YAHOO.util.Event.addListener(window, "load", function() {
    YAHOO.example.NestedHeaders = new function() {
        var myColumnDefs =datasource;

        this.myDataSource = new YAHOO.util.DataSource("DataDicTableDataGetAction?"); 
        this.myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
        this.myDataSource.responseSchema = {
            fields: ["page","visitsmonth","visitsytd","viewsmonth","viewsytd"]
        };

        this.myDataTable = new YAHOO.widget.DataTable("nested", myColumnDefs, this.myDataSource);
    };
});
</script>

<!--END SOURCE CODE FOR EXAMPLE =============================== -->

</body>
</html>