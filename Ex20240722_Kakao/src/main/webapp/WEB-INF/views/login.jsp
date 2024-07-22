<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
	<header>
		<h1>
			Login  
		</h1>
	</header>
	<%-- 세션에 mvo 값이 없을 때 로그인 화면이 나타나야 한다. --%>
	<c:if test="${sessionScope.mvo eq null }">
		<form action="" method="post">
			ID: <input type="text" name="mid" /><br/>
			PW: <input type="password" name="mpw" /><br/>
			<input type="button" value="LOGIN" />
		</form>
		<br/>
		
		<a href="">
			<img src="resources/images/kakao_login.png">
		</a>
		
	</c:if>
</body>
</html>