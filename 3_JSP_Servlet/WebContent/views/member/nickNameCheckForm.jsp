<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>

//NickNameCheckServlet에서 받아온 값을 ...

	function nickNameValue(){ //유효성검사
		//회원가입페이지에 있는 nickName value를 팝업창에 inputNickname value 공간에 집어넣음
		//체크 닉네임이 존재할때는 다르게 반영해줘야함...
		if('<%= request.getAttribute("checkedNick")%>' == 'null'){
		//만약 리퀘스트에서 받아온 어트리뷰트 checkedNick이 널이면 , 없으면 사용 가능하니까
			document.getElementById('inputNick').value
				= opener.document.joinForm.nickName.value
		// 연다, 도큐먼트, 조인폼(가입페이지)에 있는 폼이름, 
		} else{
			document.getElementById('inputNick').value
				= '<%= request.getAttribute("checkedNick")%>';
		}
	}
	
	function usedNick(){
		//inputnick에 있는걸 부모창의 value로 넘기기
		opener.document.joinForm.nickName.value =
			document.getElementById('inputNick').value;
		self.close();
			
	}


</script>
</head>
<body onload="nickNameValue();">
<!--팝업으로 닉네임 체크하기  -->
	<b>닉네임 중복 체크</b>
	<br>
	<form action="<%= request.getContextPath() %>/nickNameCheck.me" id="nickNameCheckForm">
	<!--여기서nickNameCheck.me로 서블릿으로 이동!!  -->
		<input type="text" id="inputNick" name="inputNick">
		<input type="submit" value="중복확인">
	</form>
	
	<br>
	
	<%
		if(request.getAttribute("result")!=null){ //널포인트 에러때문에 이프들어가야함
			int result =(int)request.getAttribute("result");
		
			if(result >0){
	%>		
			이미 사용 중인 닉네임입니다.	
	<%		}else{
	
	%>	
	  사용가능한 닉네임 입니다.
	<%
			}
		}
	%>
	
	<br>
	<br>
	
	<input type="button" id="cancel" value="취소" onclick="window.close();">
	<input type="button" id="usedNick" value="확인" onclick="usedNick()">
					<!--유효성 검사하고 나서 사용하겠다고 확인누르는 키 -> inputId에 있는걸 부모창의 value로 넘기기 -->
</body>
</html>