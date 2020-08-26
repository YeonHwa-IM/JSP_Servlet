<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>우리 가족 - scriptlet</h1>
	<ol>
	<%
		String[] family = (String[])request.getAttribute("family");
		for(String f : family){
	%>
		<li><%= f %></li>
	<%		
		}
	%>
	</ol>
	
	<hr>
	
	<h1>우리 가족 - customActionTag</h1>
	<ol>
		<c:forEach var="f" items="${family}" varStatus="vs">
			vs.cuttrent :  ${ vs.current }<br>
			vs.index :  ${ vs.index }<br>
			vs.count :  ${ vs.count }<br>
			vs.first :  ${ vs.first }<br> <!--이걸 사용할때는  vs.first (첫번째 도는 상태)가 true면~~ 의 식으로 활용-->
			vs.last :  ${ vs.last }
			<li>${ f }</li>
		</c:forEach>
	</ol>
</body>
</html>