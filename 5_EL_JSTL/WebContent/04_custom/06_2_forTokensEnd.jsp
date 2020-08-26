<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!--스트링으로 들어오는 값을 ,로 자르기 -->
	<h1>우리가족</h1>
	<ul>
		<c:forTokens items="${ family }" delims="," var="f">
			<li>${ f }</li>
		</c:forTokens>
	</ul>
	
	<hr>
	
	<h1>우리 가족들</h1>
	<ul>
		<c:forTokens items="${ familes }" delims=",/." var="f"> <!-- 토큰이 꼭 하나일 필요는 없다. 여러개 지정해도 된다. -->
			<li>${ f }</li>
		</c:forTokens>
	</ul>
</body>
</html>