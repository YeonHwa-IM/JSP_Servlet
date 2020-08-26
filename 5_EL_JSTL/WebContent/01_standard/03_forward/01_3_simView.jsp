<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String firstNum = request.getParameter("firstNum");
		String secondNum = request.getParameter("secondNum");
		
		int result = (int)request.getAttribute("result");
	%>
	리퀘스트 영역은 한번의 요청만 가능한데, 뷰까지 오는 동안 2번 받아올 수 있었다. 
	어떻게 가능한가?
	jsp:forward =>url을 변경시키지않고 화면만을 바꾼다.
	때문에 url에 있는 쿼리 스트링 때문에 값을 불러오는게 가능하게 한다.
	
	<%= firstNum %>부터<%= secondNum %>까지의 합은?<br>
	결과 값 : <span style="color:pink;"><%= result %></span>

</body>
</html>