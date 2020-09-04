<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 작성</title>
<link rel="stylesheet" href="/app/resources/css/writereview.css">
<script src="/app/resources/jquery-3.2.1.min.js"></script>
    <script>
      $("document").ready(() => {
    	  <% session=request.getSession();%>
    	  <% if(session.getAttribute("id")!=null){%>
		    	$('#mypageBtn').removeAttr("hidden");
		    	$('#logoutBtn').removeAttr("hidden"); 
		    	$('#loginBtn').attr("hidden","hidden");
		    	$('#info_writer').innerText = "<%= session.getAttribute("id") %>";
		   <%} else{%>
	    	$('#mypageBtn').attr("hidden","hidden");
		    $('#logoutBtn').attr("hidden","hidden");
	    	$('#loginBtn').removeAttr("hidden");
	       <%}%>
        $("#loginBtn").on("click", () => {
          location.href = "/app/login";
        });
        
        $("#logoutBtn").on("click", () => {
          location.href = "/app/logout";
        });
        
        $("#mypageBtn").on("click", () => {
          location.href = "/app/mypage";
        });
        
        $("#homeBtn").on("click", () => {
        	location.href = "/app";
        });
        
      });
    </script>
</head>
<body>
<div class="login_bar">
      <button id="mypageBtn" class="logBtn" hidden="hidden">마이페이지</button>
      <button id="logoutBtn" class="logBtn" hidden="hidden">로그아웃</button>
      <button id="loginBtn" class="logBtn">로그인</button>
	  <button id="homeBtn" class="logBtn">홈</button>
</div>
<div class="container">
	<h1 id="title">WRITE A REVIEW</h1>
	<div id="body_container">
		<form action="writereview" method="post" id="review_form">
			<textarea name="contents" id="contents" placeholder="리뷰를 작성해주세요."></textarea>
			<input type="hidden" name="uno" id="uno" value='<%= request.getParameter("uno") %>'>
			<input type="submit" id="submit_btn" value="리뷰 작성하기">
		</form>
		<div id="info_container">
			<p id="info_toilet" class="info">화장실 : <%= request.getParameter("tname") %></p>
			<p id="info_writer" class="info">작성자 : <%= session.getAttribute("id") %></p>
			<p id="info_usedate" class="info">사용 일자 : <%= request.getParameter("tdate") %></p>
			<p id="info_date" class="info">작성 일자 : </p>
		</div>
	</div>
</div>
</body>
<script>
	const today = new Date();
	const year = today.getFullYear();
	const month = today.getMonth() + 1 > 10 ? (today.getMonth() + 1).toString() : "0" + (today.getMonth() + 1).toString();
	const day = today.getDate() > 10 ? today.getDate().toString() : "0" + today.getDate().toString();
	
	const dateStr = year + '-' + month + '-' + day;
	document.getElementById("info_date").innerText += " " + dateStr;
</script>
</html>
