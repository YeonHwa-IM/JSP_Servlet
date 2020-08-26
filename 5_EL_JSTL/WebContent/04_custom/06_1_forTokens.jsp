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
	<c:set var="family" scope="request">신형만,봉미선,신짱구,신짱아</c:set>
	<c:set var="familes" scope="request">신형만,봉미선.신짱구/신짱아.둘리,또치,도우너</c:set>
	
	<jsp:forward page="06_2_forTokensEnd.jsp" />
</body>
</html>