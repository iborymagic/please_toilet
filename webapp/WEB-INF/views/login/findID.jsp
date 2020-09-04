<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
<link href="/app/resources/css/find.css" rel="stylesheet"/>
<script src="/app/resources/jquery-3.2.1.min.js"></script>
<script>
	$("document").ready(() => {
	    $("#loginBtn").on("click", () => {
	        location.href = "/app/login";
	      });
	      
	      $("#homeBtn").on("click", () => {
	        location.href = "/app/home";
	      });
	});
</script>
</head>
<body>

<div class="login_bar">
	<button id="homeBtn" class="logBtn">홈</button>
    <button id="loginBtn" class="logBtn">로그인</button> 
</div>

<div class="form">
	<h1 class="title">FIND ID</h1>
	<p class="form_text">가입시 입력했던 이메일을 입력하세요.</p>
	<form action="/app/login/findid" method="post">
	<input type="text" id="email" name="email" placeholder="이메일을 입력해주세요"> <br>
	<input type="submit" id="findBtn" value="아이디 찾기">
	</form>
</div>
</body>
</html>
