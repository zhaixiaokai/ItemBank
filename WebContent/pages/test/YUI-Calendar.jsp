
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>


    <meta http-equiv="content-type" content="text/html; charset=utf-8">
<title>Quickstart Tutorial</title>

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

<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/2.9.0/build/fonts/fonts-min.css" />
<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/2.9.0/build/calendar/assets/skins/sam/calendar.css" />
<script type="text/javascript" src="http://yui.yahooapis.com/2.9.0/build/yahoo-dom-event/yahoo-dom-event.js"></script>
<script type="text/javascript" src="http://yui.yahooapis.com/2.9.0/build/calendar/calendar-min.js"></script>

<!--there is no custom header content for this example-->

</head>

<body class="yui-skin-sam">


<h1>Quickstart Tutorial</h1>

<div class="exampleIntro">
	<p>This basic example walks you through the steps needed to get a default Calendar up and running. It covers the set of file dependencies which need to be included as well as the basic markup and JS code required to get you started.</p>
			
</div>

<!--BEGIN SOURCE CODE FOR EXAMPLE =============================== -->

<div id="cal1Container"></div>

<script type="text/javascript">
	YAHOO.namespace("example.calendar");

	var navConfig = {
			strings: {
				year:"\u6708",
				month:"选择月",
				submit: "Submit",
				cancel: "Cancel",
				invalidYear: "Please enter a valid year"
				},
				monthFormat: YAHOO.widget.Calendar.TEST,
				initialFocus: "month"
				};
	var navConfig = {navigator :navConfig
	}
	YAHOO.example.calendar.init = function() {
		YAHOO.example.calendar.cal1 = new YAHOO.widget.Calendar("cal1","cal1Container",navConfig);
		var months = ["\u4e00\u6708","\u4e8c\u6708", "\u4e09\u6708", "\u56db\u6708", "\u4e94\u6708", "\u516d\u6708", "\u4e03\u6708", "\u516b\u6708", "\u4e5d\u6708", "\u5341\u6708", "\u5341\u4e00\u6708", "\u5341\u4e8c\u6708"];
	    YAHOO.example.calendar.cal1.cfg.setProperty("MONTHS_SHORT", months);
	    YAHOO.example.calendar.cal1.cfg.setProperty("MONTHS_LONG", months);
	    
	    var weekdays = ["\u65e5", "\u4e00", "\u4e8c", "\u4e09", "\u56db", "\u4e94", "\u516d"];
        YAHOO.example.calendar.cal1.cfg.setProperty("WEEKDAYS_1CHAR", weekdays);
        YAHOO.example.calendar.cal1.cfg.setProperty("WEEKDAYS_SHORT", weekdays);
        YAHOO.example.calendar.cal1.cfg.setProperty("WEEKDAYS_MEDIUM", weekdays);
        YAHOO.example.calendar.cal1.cfg.setProperty("WEEKDAYS_LONG", weekdays);
        
        
		YAHOO.example.calendar.cal1.render();
	}

	YAHOO.util.Event.onDOMReady(YAHOO.example.calendar.init);
</script>

<div style="clear:both" ></div>

<!--END SOURCE CODE FOR EXAMPLE =============================== -->


<!--MyBlogLog instrumentation-->
<script type="text/javascript" src="http://track2.mybloglog.com/js/jsserv.php?mblID=2007020704011645"></script>

</body>
</html>
