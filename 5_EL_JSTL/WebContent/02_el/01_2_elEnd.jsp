<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- <%@ page import ="action.model.vo.Person" %> --%>
<%--   --%>
<!--  	Person person = (Person)request.getAttribute("person"); -->
<!--  	String beverage = (String)request.getAttribute("beverage"); -->
<!--  	int year = (int)request.getAttribute("year"); -->
<!--  	String[] product = (String[])request.getAttribute("product"); -->
<!--  	String book = (String)request.getAttribute("book"); -->
<!-- 	String movies = (String)request.getAttribute("movies"); -->
<%--   --%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>.item{font-weight: bold; color:yellowgreen;}</style>
</head>
<body>
	<h2>scriptlet을 통해 request 객체에 저장된 데이터 출력하기</h2>
<%-- 	<h4>개인정보(<%= year %>)</h4> --%>
<%-- 	이름 : <%= person.getName() %><br> --%>
<%-- 	성별 : <%= person.getGender() %><br> --%>
<%-- 	나이 : <%= person.getNai() %><br> --%>
<!-- 	<h4>취향정보</h4> -->
<%-- 	<%= person.getName() %>님이 가장 좋아하는 음료 : <%= beverage %><br> --%>
<%-- 	<%= person.getName() %>님이 가장 좋아하는 물건 : <%= product[0] %>, <%= product[1] %>,<%= product[2] %><br> --%>
<%-- 	<%= person.getName() %>님이 가장 좋아하는 도서 : <%= book %><br> --%>
<%-- 	<%= person.getName() %>님이 가장 좋아하는 영화 : <%= movies %><br> --%>
	
	<!-- sctiptlet 경우 해당 영역에 값이  없으면 null로 뜸 -->
	
	<hr>
	
	<h2>el의 내장객체 XXXScope를 통해 저장된 데이터 출력하기</h2>
	<h4>개인정보(${ requestScope.year })</h4>
	이름 : ${ requestScope.person.name }<br>
	성별 : ${ requestScope.person.gender }<br>
	나이 : ${ requestScope.person.nai }<br>
	
	<h4>취향정보</h4>
	
	${ requestScope.person.name }님이 가장 좋아하는 음료 : ${ requestScope.beverage }<br>
	${ requestScope.person.name }님이 가장 좋아하는 물건 : ${ requestScope.product[0] }, ${ requestScope.product[1] }, ${ requestScope.product[2] }<br>
	
		<!-- el은 영역이 맞지 않을 때 아무것도 뜨지 않음-->
	${ requestScope.person.name }님이 가장 좋아하는 도서 : ${ requestScope.book }<br>
	${ requestScope.person.name }님이 가장 좋아하는 영화 : ${ requestScope.movie }<br>
	
	<hr>
	
	<h2>Scope를 생갹하여 저장된 데이터 출력하기</h2>
	<p>
		el내장객체 pageScope, requestScope, sessionScope, applicationScope 생략가능<br>
		el은 pageScope -> requestScope -> sessionScope -> applicationScope 순으로 찾음<br>
		여러 scope에 동일한 속성을 기록하며 이름 충돌(naming conflict)이 의심되면 명시적으로 scope를 지정해야 함
	</p>
	<h4>개인정보(${ year })</h4>
	이름 : ${ person.name }<br>
	성별 : ${ person.gender }<br>
	나이 : ${ person.nai }<br>
	
	<h4>취향정보</h4>
	${ person.name }님이 가장 좋아하는 음료 : ${ beverage }<br>
	${ person.name }님이 가장 좋아하는 음료 : ${ sessionScope.beverage }<br>
	${ person.name }님이 가장 좋아하는 음료 : ${ applicationScope.beverage }<br>
	<!--순서대로 영역을 거쳐서 찾아오기 때문에 가져오고자 한느 값이 다르면 세션을 정해주어야 제대로 된 값이 나올 수 있음 -->
	
	${ person.name }님이 가장 좋아하는 물건 : ${ product[0] }, 
									   ${ product[1] }, 
									   ${ product[2] }<br>
	<!-- el은 영역을 지정해주지 않으니까 세션, 어플리케이션에 있는것도 찾아줌 -->
	${ person.name }님이 가장 좋아하는 음료 : ${ book }<br>								   
	${ person.name }님이 가장 좋아하는 영화: ${ movie }<br>								   
	<!-- el은 영역이 맞지 않을 때 아무것도 뜨지 않음-->
	${ person.name }님이 가장 좋아하는 도서 : ${ sessionScope.book }<br>
	${ person.name }님이 가장 좋아하는 영화 : ${ applicationScope.movie }<br>
	
	
</body>
</html>