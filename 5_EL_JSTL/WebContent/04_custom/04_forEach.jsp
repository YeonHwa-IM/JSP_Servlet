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
<%
	for(int i = 1; i <= 6; i++){
%>
	<h<%= i %>>반복문<%= i %></h<%= i %>>
<%
	}
%>
	<hr>
	
	<c:forEach var="i" begin="1" end="6">
		<h${i}>반복문${i}</h${i}>
	</c:forEach>
	
	<hr>
	
	<c:forEach var="i" begin="1" end="6" step="2">
		<h${i}>건너뛰기${i}</h${i}>
	</c:forEach>
	
	<hr>
	
	<c:forEach var="i" begin="1" end="6">
		<h${7-i}>거꾸로 뽑기${7-i}</h${7-i}>
	</c:forEach>
	
</body>
</html>