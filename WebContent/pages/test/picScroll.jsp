
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml"> 
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" /> 
<title>JQuery实现图片轮播效果 </title> 
<script src="../js/jquery.min.js" type="text/javascript"></script> 
<style type="text/css"><!-- 
#banner {position:relative; width:478px; height:286px; border:1px solid #666; overflow:hidden; font-size:16px} 
#banner_list img {border:0px;} 
#banner_bg {position:absolute; bottom:0;background-color:#000;height:30px;filter: Alpha(Opacity=30);opacity:0.3;z-index:1000;cursor:pointer; width:478px; } 
#banner_info{position:absolute; bottom:4px; left:5px;height:22px;color:#fff;z-index:1001;cursor:pointer} 
#banner_text {position:absolute;width:120px;z-index:1002; right:3px; bottom:3px;} 
#banner ul {position:absolute;list-style-type:none;filter: Alpha(Opacity=80);opacity:0.8; z-index:1002; 
margin:0; padding:0; bottom:3px; right:5px; height:20px} 
#banner ul li { padding:0 8px; line-height:18px;float:left;display:block;color:#FFF;border:#e5eaff 1px solid;background-color:#6f4f67;cursor:pointer; margin:0; font-size:16px;} 
#banner_list a{position:absolute;} <! 让四张图片都可以重叠在一起 
--></style> 
</head> 
<body> 
<p>【实例演示】</p> 
<div id="banner"> 
<div id="banner_bg"></div> 
<!--标题背景--> 
<div id="banner_info"></div> 
<!--标题--> 
<ul> 
<li>1</li> 
<li>2</li> 
<li>3</li> 
<li>4</li> 
</ul> 
<div id="banner_list"> 
<a href="#" target="_blank"><img src="http://www.poluoluo.com/jzxy/UploadFiles_333/201110/2011101614491219.jpg" title="橡树小屋的blog" alt="helloworldblog" /></a> 
<a href="#" target="_blank"><img src="http://www.poluoluo.com/jzxy/UploadFiles_333/201110/2011101614491286.jpg" title="橡树小屋的blog" alt="橡树小屋的blog" /></a> 
<a href="#" target="_blank"><img src="http://www.poluoluo.com/jzxy/UploadFiles_333/201110/2011101614491283.jpg" title="橡树小屋的blog" alt="橡树小屋的blog" /></a> 
<a href="#" target="_blank"><img src="http://www.poluoluo.com/jzxy/UploadFiles_333/201110/2011101614491389.jpg" title="橡树小屋的blog" alt="橡树小屋的blog" /></a> 
</div> 
</div> 
<script type="text/javascript">// <![CDATA[ 
var t = n = 0, count; 
$(document).ready(function(){ 
count=$("#banner_list a").length; 
$("#banner_list a:not(:first-child)").hide(); 
$("#banner_info").html($("#banner_list a:first-child").find("img").attr('alt')); 
$("#banner_info").click(function(){window.open($("#banner_list a:first-child").attr('href'), "_blank")}); 
$("#banner li").click(function() { 
var i = $(this).text() - 1;//获取Li元素内的值，即1，2，3，4 
n = i; 
if (i >= count) return; 
$("#banner_info").html($("#banner_list a").eq(i).find("img").attr('alt')); 
$("#banner_info").unbind().click(function(){window.open($("#banner_list a").eq(i).attr('href'), "_blank")}) 
$("#banner_list a").filter(":visible").fadeOut(500).parent().children().eq(i).fadeIn(1000); 
$(this).css({"background":"#be2424",'color':'#000'}).siblings().css({"background":"#6f4f67",'color':'#fff'}); 
}); 
t = setInterval("showAuto()", 4000); 
$("#banner").hover(function(){clearInterval(t)}, function(){t = setInterval("showAuto()", 4000);}); 
}) 
function showAuto() 
{ 
n = n >=(count - 1) ? 0 : ++n; 
$("#banner li").eq(n).trigger('click'); 
} 
// ]]></script> 