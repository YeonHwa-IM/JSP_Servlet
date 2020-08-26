<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>주문 내역</h2>
	<!-- 뷰에서 뷰로 넘어간 파라미터 값을 가져올 때 scope영역에서 가져오지 않도록 param.을 붙여주기
	배열이면 Values.에 [방번호]를 붙여줘야함 -->
	상품 명 : ${ param.pname }<br>
	수량 : ${ param.pcount }<br>
	옵션1 : ${ paramValues.option[0] }<br>
	옵션2 : ${ paramValues.option[1] }<br>
</body>
</html>