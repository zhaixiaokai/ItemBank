/*
 * 本功能实现首页图片
 * 【原理简述】 
 * 1，将除了第一张以外的图片全部隐藏，
 * 2，获取第一张图片的alt信息显示在信息栏，并添加点击事件 
 * 3，为4个按钮添加点击侦听，点击相应的按钮，用fadeOut，fadeIn方法显示图片
 * 4，设置setInterval，定时执行切换函数 
 * 【代码说明】
 * filter(":visible") :获取所有可见的元素 
 * unbind()：从匹配的元素中删除绑定的事件 
 * siblings：取得一个包含匹配的元素集合中每一个元素的所有唯一同辈元素的元素集合 
 * 例：找到每个div的所有同辈元素中带有类名为selected的元素。 
 *
*/var t = n = 0, count; 
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