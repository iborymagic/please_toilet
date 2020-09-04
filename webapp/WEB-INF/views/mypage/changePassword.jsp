<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/app/resources/css/mypage.css" type="text/css" />
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700&display=swap" rel="stylesheet">
    <title>급똥</title>
</head>
<body>
    <form action="/app/mypage/changepassword" method="post">
        <input class="inputs" type="password" name="oldpw" placeholder="현재 비밀번호를 입력하세요.">
        <input class="inputs" type="password" name="newpw" placeholder="변경할 비밀번호를 입력하세요.">
        <input class="inputs" type="password" name="newpwcheck" placeholder="변경할 비밀번호 확인">
        <input type="submit" value="비밀번호 변경하기">
    </form>
</body>
</html>