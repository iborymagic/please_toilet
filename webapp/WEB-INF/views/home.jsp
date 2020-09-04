<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
  	<meta charset="UTF-8">
    <link
      href="https://fonts.googleapis.com/css2?family=Nanum+Gothic+Coding:wght@700&family=Noto+Sans+KR:wght@400;700&display=swap"
      rel="stylesheet"
    >
    <link rel="stylesheet" type="text/css" href="/app/resources/css/toilet.css">
    <title>급똥</title>
    <script src="/app/resources/jquery-3.2.1.min.js"></script>
    <script>
      $("document").ready(() => {
    	  <% session=request.getSession();%>
    	  <% if(session.getAttribute("id")!=null){%>
		    	$('#mypageBtn').removeAttr("hidden");
		    	$('#logoutBtn').removeAttr("hidden"); 
		    	$('#loginBtn').attr("hidden","hidden");
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
        
      });
    </script>
  </head>
  <body>
    <div class="login_bar">
      <button id="mypageBtn" class="logBtn" hidden="hidden">마이페이지</button>
      <button id="logoutBtn" class="logBtn" hidden="hidden">로그아웃</button>
      <button id="loginBtn" class="logBtn">로그인</button>
    </div>
    <img src="/app/resources/img/paper.PNG" class="paper" align="right">
    <div class="container">
      <h1 class="main_title">PLEASE TOILET</h1>
      <div class="contents">
        PLEASE TOILET 은 주변의 화장실 위치 정보를 제공받을 수 있는 서비스입니다.<br>
        <br>
        우선, 주변 화장실 위치 정보를 제공받기 위해 <br>
        사이트에서 사용자의 위치를 파악할 수 있도록 허용해주세요.<br>
        <br>
        그리고, 화장실의 위치를 표시할 범위를 선택해주세요.<br>
        <form action="/app/map" method="get">
          <div class="container1">
            <input
              oninput="ShowSliderValue(this.value)"
              type="range"
              min="0"
              max="500"
              value="0"
              class="distance"
              name="range"
            />
            <div class="dis">
              <font size="2" id="slider_value_view" class="disfont">0M</font>
            </div>
          </div>
          <input class="find" type="submit" value="주변의 화장실 찾기">
        </form>
        <script language="javascript">
          function ShowSliderValue(sVal) {
            var obValueView = document.getElementById("slider_value_view");
            obValueView.innerHTML = sVal + "M";
          }
        </script>
      </div>
    </div>
  </body>
</html>
