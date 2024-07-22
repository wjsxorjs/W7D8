<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel = "stylesheet" href = "<c:url value='/resources/css/global.css' />"/>
<link rel = "stylesheet" href = "<c:url value='/resources/css/home.css' />"/>
<link rel = "stylesheet" href = "<c:url value='/resources/css/layout.css' />"/>
<style>
/* Body에 모달 열렸을 때 스크롤 막기 */
body.modal-open {
    overflow: hidden;
}
     
.modal-overlay {
    /* display: none; 처음에는 오버레이를 숨깁니다 */
    position: fixed;
    z-index: 1000;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0,0,0,0.6); /* Black with opacity */
}

/* input 스타일 */
#login_dialog input[type="text"],
#login_dialog input[type="password"] {
    width: calc(100% - 20px);
    padding: 10px;
    margin: 10px 0;
    display: inline-block;
    border: 1px solid #ccc;
    box-sizing: border-box;
    border-radius: 5px; /* 모서리를 둥글게 */
}

#login_dialog .login_btn {
            background-color: #4CAF50;
            color: white;
            padding: 10px 15px;
            margin: 10px 0;
            border: none;
            cursor: pointer;
            width: calc(100% - 20px);
            border-radius: 5px; /* 모서리를 둥글게 */
        }
        
        #login_dialog .login_btn:hover {
            background-color: #45a049;
        }
        
        .options {
            display: flex;
        }

        .options a:hover {
            text-decoration: underline;
        }
</style>
</head>
<body class="modal-open">
<header>
<h1>로그인</h1>
</header>

<c:if test="${sessionScope.mvo eq null}">
<!-- 모달 오버레이 -->
<div class="modal-overlay"></div>
<!-- 로그인 모달 -->
<div style="pointer-events: auto;" id="login_dialog" role="dialog" aria-describedby="radix-:R24pH2:" aria-labelledby="radix-:R24pH1:" data-state="open" class="sboh910 sboh912 sboh915" tabindex="-1">
<div class="sboh91e">
<h2 id="radix-:R24pH1:" class="sboh91f">쌍용마켙</h2>
</div>
<div class="_588sy4i8 _588sy4ew _588sy4ne _588sy4k8 _588sy41z _588sy421">
<div class="_588sy41z _588sy421 _588sy42q _588sy4172 _588sy415q _588sy413q _588sy418e _588sy4gw">
<h2 class="_588sy419q _588sy41y _588sy41ak" level="2">로그인</h2>

<form id="login_form" onsubmit="login(event, this)">
<input type="text" id="id" name="id" placeholder="ID" required>
<input type="password" id="pw" name="pw" placeholder="Password" required>
<button class = "login_btn" type="submit">LOGIN</button>
</form>
<div class="options _588sy41z _588sy421 _588sy42q _588sy412w _588sy4168 _588sy415q">
    <a href="#">아이디 찾기</a>
    <a href="#">비밀번호 찾기</a>
    <a href="#">회원가입</a>
</div>
<a href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=620accc387610feefacbb41cfd1a8d39&redirect_uri=http://localhost:8080/login/kakao">
	<img src="/resources/images/kakao_login.png"/>
</a>
</div>
<button type="button" class="sboh91h"><svg viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg" data-seed-icon="true" data-seed-icon-version="0.4.0-beta.2" width="24" height="24" style="width: 100%; height: 100%;"><g><path fill-rule="evenodd" clip-rule="evenodd" d="M3.72633 3.72633C4.0281 3.42456 4.51736 3.42456 4.81913 3.72633L12 10.9072L19.1809 3.72633C19.4826 3.42456 19.9719 3.42456 20.2737 3.72633C20.5754 4.0281 20.5754 4.51736 20.2737 4.81913L13.0928 12L20.2737 19.1809C20.5754 19.4826 20.5754 19.9719 20.2737 20.2737C19.9719 20.5754 19.4826 20.5754 19.1809 20.2737L12 13.0928L4.81913 20.2737C4.51736 20.5754 4.0281 20.5754 3.72633 20.2737C3.42456 19.9719 3.42456 19.4826 3.72633 19.1809L10.9072 12L3.72633 4.81913C3.42456 4.51736 3.42456 4.0281 3.72633 3.72633Z" fill="currentColor"></path></g></svg><span class="ar0rax0">Close</span>
</button>
</div>
</div>
</c:if>
</body>
</html>