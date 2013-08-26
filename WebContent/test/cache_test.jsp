<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="net.sf.ehcache.Cache,net.sf.ehcache.CacheManager,net.sf.ehcache.Element"%>
<%@ page import="net.ib.common.BeanLoader"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
System.out.println("调用缓存框架----------------------------");
CacheManager cacheManager=(CacheManager)BeanLoader.getBean("cacheManager");
if(null!=cacheManager){
	Cache objectCache=cacheManager.getCache("objectCache");
	Element element = objectCache.get("SYSTEM_NAME");
	if(null!=element){
		out.println("SYSTEM_NAME in cache is "+element.getValue());
	}
	Element licStatus = objectCache.get("LICENSE_STATUC");
	if(null!=licStatus){
		out.println("LICENSE_STATUC in cache is "+licStatus.getValue());
	}
}
%>
</body>
</html>