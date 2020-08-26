<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	copyright 이 페이지의 저작권은 박신우에게 있습니다.<br>
	<%
		java.util.Date now = new java.util.Date();
		String today = String.format("%ty년 %tm월 %td일 %tA", now, now, now, now);
	%>
	<span style="color:yellowgreen; font-weight:bold;"><%= today %></span>
	

</body>
</html>