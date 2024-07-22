<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reg2</title>
</head>
<body>
	<header>
		<h1>추가정보입력</h1>
	</header>
	<form action="/registry" method="post">
		<img src="${mvo.p_img }" width="100" /><br/>
		이름: <input type="text" name="m_name" /><br/>
		별칭 <input type="text" name="nickname" value="${mvo.nickname}" readonly/><br/>
		이메일: <input type="text" name="email" /><br/>
		연락처: <input type="text" name="phone" /><br/>
		<input type="button" value="저장" />
	</form>
</body>
</html>