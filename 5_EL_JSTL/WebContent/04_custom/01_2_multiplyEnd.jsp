<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>jstl core라이브러리</h1>
	<p>
		사용할 태그 라이브러리 선업하기 : taglib 지시자 사용<br>
		prefix: 앞 첨자, 다른 태그와 구별할 수 있는 name space 제공<br>
		uri : 실제 웹 상 주소가 아니라 태그 라이브러리를 나타내는 식별자로
			    이 지시자를 통해 작성한 태그 이름과 매칭되는 자바 코드를 찾음 
	</p>
	
	<h2>c:set 태그</h2><!-- 변수 선언, scope는 기본적으로 page -->
	<!-- 1. 변수의 선언 -->
	<c:set var="no1" value="${ param.num1 }" />
	<c:set var="no2" value="${ param.num2 }" />
	
	<!-- 2. 변수의 활용 -->
<%-- 	<c:set var="result" value="${param.num1 * param.num2 }" /> --%>
<%-- 	<p>${ param.num1 }곱하기 ${ param.num2 }의 값은? ${ result }</p> --%>
	<c:set var="result" value="${no1 * no2 }" />
	<p>${ no1 }곱하기 ${ no2 }의 값은? ${ result }</p>
	
	<h2>c:remove 태그</h2>
	<p> 지정한 변수를 모든 scope에서 검색해 삭제하거나 지정한 scope에서만 삭제</p>
	<!-- request스코프에 9999값을 가진 result 변수 생성 -->
	
	<c:set var="result" value="9999" scope="request" />
	삭제 전\${ result } : ${ result } <br>
	삭제 전\${ requestScope.result } : ${ requestScope.result }<br>
	
	<br>
	
	<!-- 1. 모든 scope 삭제 -->
<%-- 	<c:remove var="result" /> --%>
	
	<!-- 2. 특정 scope 삭제 -->
<%--  	<c:remove var="result" scope="request" /> --%>
 	<c:remove var="result" scope="page" />
 	
 	삭제 후\${ result } : ${ result } <br> <!-- 페이지 영역을 지웠으니 리퀘스트의 값을 가져와서 9999 바뀜 -->
	삭제 후\${ requestScope.result } : ${ requestScope.result }<br>
	
</body>
</html>