<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>jstl fmt</h2>
	<p>날짜, 시간, 숫자 데이터의 출력 형식을 지정할 때 사용</p>
	
	<h3>숫자 데이터 포맷 지정 : formatNumber태그</h3>
	천단위 구분 기호 적용 : <fmt:formatNumber value="123456789" /><br> <!-- groupingUsed="true"가 기본값이다. -->
	<fmt:formatNumber value="123456789" groupingUsed="true"/><br>
	<fmt:formatNumber value="123456789" groupingUsed="false"/><br> <!-- 천단위 구분기호 없이 표시됨  -->
	
	<br>
	
	<b>실수값 소수점 아래 자리수 지정 : pattern속성 사용</b><br>
	#을 사용한 경우 : <fmt:formatNumber value="1.239567" pattern="#.##"/><br> <!-- 소수점 반올림 -->
	#을 사용한 경우 : <fmt:formatNumber value="1.2" pattern="#.##"/><br> <!-- 소수점 빈자리 비워둠 -->
	0을 사용한 경우 : <fmt:formatNumber value="1.2" pattern="#.00"/><br>	<!-- 소수점 빈자리 0으로 채움 -->

	<h3>type 속성으로 백분율, 통화기호 처리</h3>
	<fmt:formatNumber value="0.12" type="percent"/><br> <!-- 퍼센트  -->
	<fmt:formatNumber value="123456789" type="currency"/><br> <!-- 원화 붙이기 -->
	<fmt:formatNumber value="123456789" type="currency" currencySymbol="$" /> <!-- 다른 통화로 표시하기 -->
	
	
</body>
</html>