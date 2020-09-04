<%@page import="java.util.Iterator"%>
<%@page import="com.pleasetoilet.vo.useToiletVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/app/resources/css/mypage.css" type="text/css">
    <link rel="stylesheet" href="/app/resources/css/mypage-log.css?ver=1" type="text/css">
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700&display=swap" rel="stylesheet">
    <title>급똥</title>
</head>
<body>
	<div id="mypage-logs">
	<% List<useToiletVO> lists = (List<useToiletVO>)request.getAttribute("logList"); 
	   
	   Iterator<useToiletVO> i = lists.iterator();
	   
	   String templateStr = "";
	   if(lists.size() <= 6) {
		   while(i.hasNext()) {
			   useToiletVO vo = i.next();
			   templateStr += "<log-card name='" + vo.getTname() + "' date='" + vo.getUsedate() + "' uno='" + vo.getUno() + "'></log-card>";
		   }
	   } else {
		   int n = 0;
		   while(n < 6) {
			   useToiletVO vo = i.next();
			   templateStr += "<log-card name='" + vo.getTname() + "' date='" + vo.getUsedate() + "' uno='" + vo.getUno() + "'></log-card>";
			   n++;
		   }
	   }
	%>
</div>
</body>
<script>
	templateString = "<%= templateStr %>";
	document.getElementById("mypage-logs").innerHTML = templateString;
</script>
</html>