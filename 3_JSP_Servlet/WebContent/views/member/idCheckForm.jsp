<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function idValue(){
		//회원가입페이지에 있는 JoinuserId value를 팝업창에 inputId value 공간에 집어넣음
		//체크 아이디가 존재할때는 다르게 반영해줘야함...
		if('<%= request.getAttribute("checkedId")%>' == 'null'){ 
			document.getElementById('inputId').value = opener.document.joinForm.joinUserId.value; /*여기먼저   */
		} else{
			document.getElementById('inputId').value = '<%= request.getAttribute("checkedId")%>';
		}
	}
	
	function usedId(){ /* 마지막에  */
		//inputId에 있는걸 부모창의 value로 넘기기
		opener.document.joinForm.joinUserId.value =
			document.getElementById('inputId').value;
		self.close();
			
	}


</script>
</head>
<body onload="idValue();">
<!--팝업으로 아이디 체크하기  -->
	<b>아이디 중복 체크</b>
	<br>
	<form action="<%= request.getContextPath() %>/idCheck.me" id="idCheckForm">
	<!--여기서 idCheck.me 로 서블릿으로 이동!!  -->
		<input type="text" id="inputId" name="inputId">
		<input type="submit" value="중복확인">
	</form>
	
	<br>
	
	<%
		if(request.getAttribute("result")!=null){ //널포인트 에러때문에 이프들어가야함
			int result =(int)request.getAttribute("result");
		
			if(result >0){
	%>		
			이미 사용 중인 아이디입니다.	
	<%		}else{
	
	%>	
	  사용가능한 아이디 입니다.
	<%
			}
		}
	%>
	
	<br>
	<br>
	
	<input type="button" id="cancel" value="취소" onclick="window.close();">
	<input type="button" id="usedId" value="확인" onclick="usedId();">

</body>
</html>