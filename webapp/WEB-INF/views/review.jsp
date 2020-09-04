<%@page import="com.pleasetoilet.vo.ReviewVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 보기</title>
<script
      src="https://kit.fontawesome.com/faea847e34.js"
      crossorigin="anonymous"
    ></script>
<script src="/app/resources/jquery-3.2.1.min.js"></script>
<link href="/app/resources/css/review.css" rel="stylesheet" />
<script>
	$(document).ready(() => {
		$("#homeBtn").on("click", () => {
	          location.href = "/app/home";
	    });
		
		$("#mypageBtn").on("click", () => {
	          location.href = "/app/mypage";
	    });
		
		$("#logoutBtn").on("click", () => {
	          location.href = "/app/logout";
	    });
	});
</script>
</head>
<body>
	<div class="login_bar"> 
      <button id="mypageBtn" class="logBtn">마이페이지</button>
      <button id="homeBtn" class="logBtn">홈</button>
      <button id="logoutBtn" class="logBtn">로그아웃</button>
    </div>

	<div class="review_container">
		<% if(request.getAttribute("reviewList") == null){ %>
		등록된 리뷰가 없습니다.
		<%} else { %>
			<h1 id="review_title">REVIEW</h1>
			<h1 class="toilet_name">
				<% ArrayList<ReviewVO> reviewList = (ArrayList<ReviewVO>)request.getAttribute("reviewList"); %>
				<%= reviewList.get(0).getTname() %>
			</h1>
			<c:forEach items="${reviewList}" var="vo">
				<div class="review_item">
					<div class="review_info">
						<p class="writer">${vo.id}<br>
						${vo.usedate}</p>
					</div>
					<div class="review_content">
						<p class="contents">${vo.contents}</p>
					</div>
				</div>
			</c:forEach>
			<%} %>
	</div>
</body>
</html>
