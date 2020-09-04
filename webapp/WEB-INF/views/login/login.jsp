<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Insert title here</title>
    <link href="/app/resources/css/login.css" rel="stylesheet" />
    <script src="/app/resources/jquery-3.2.1.min.js"></script>
    <script>
      var k = "${msg}";
      $("document").ready(() => {
        if (k == "fail") {
          $("#loginstatus").html("로그인에 실패했습니다.");
        }
        
        $("#homeBtn").on("click", () => {
	        location.href = "/app/home";
	      });
      });
    </script>
    
  </head>
  <body>
  <div class="login_bar">
      <button id="homeBtn" class="logBtn">홈</button>      
	</div>

    <div class="forms-container">
      <div class="form-group">
        <h1 class="title">LOG IN</h1>
        

        <form action="login" method="POST" id="login">
          <input
            type="text"
            name="id"
            id="id"
            class="form-control"
            placeholder="아이디를 입력하세요"
          />
          <input
            type="password"
            name="pw"
            id="pw"
            class="form-control"
            placeholder="비밀번호를 입력하세요"
          />
          <br />
          <input
            id="autologin"
            type="checkbox"
            name="autologin"
            value="자동로그인"
          />
          자동 로그인
          <br />
			<div id="loginstatus">
			
			</div>
          <button type="submit" class="btn btn_primary" value="로그인">
            로그인 하기
          </button>
        </form>
      </div>
      <div class="forget-group">
        <a id="id_forget" href="/app/login/findid">아이디를 잊어버리셨나요?</a>
        <a id="pw_forget" href="/app/login/findpw">
          비밀번호를 잊어버리셨나요?
        </a>
        <a id="signup" href="/app/signup">회원가입</a>
      </div>
    </div>
  </body>
</html>
