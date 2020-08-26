<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="notice.model.vo.Notice, java.util.ArrayList"%>
    
<% ArrayList<Notice> list = (ArrayList)request.getAttribute("list");%>
<!-- 서블릿에서 보낸 "list" 라는 상자안의 값을 list 변수 담고 아래화면에 뿌려주기-->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.outer{
		width: 800px; height: 500px; background: rgba(255, 255, 255, 0.4); border: 5px solid white;
		margin-left: auto; margin-right: auto; margin-top: 50px;
	}
	#listArea{text-align: center;}
	.tableArea{width:650px; height:350px; margin:auto;}
	th{border-bottom: 1px solid grey;}
	#writeNoBtn{background: #B2CCFF; color: white; border-radius: 10px; width: 80px; height: 25px;}
</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>
	
	<div class="outer">
		<br>
		<h2 align="center">공지사항</h2>
		<div class="tableArea">
			<table id="listArea">
				<tr>
					<th>글번호</th>
					<th width="300px">글제목</th>
					<th width="100px">작성자</th>
					<th>조회수</th>
					<th width="100px">작성일</th>
				</tr>
				<% if(list.isEmpty()){ %>
				<tr>
					<td colspan="5">존재하는 공지사항이 없습니다.</td>
				</tr>
				<% } else {
					for(Notice n : list){
				%>
				<tr>
					<td><%= n.getnNO() %></td>
					<td><%= n.getnTitle() %></td>
					<td><%= n.getnWriter()%></td>
					<td><%= n.getnCount() %></td>
					<td><%= n.getnDate() %></td>
				</tr>
				<%  }
					
				   }
				%>	
			</table>
		</div>
		
		<div class="searchArea" align="center"> 
		<!--관리자만 로그인 시키기 아이디와 비밀번호를 보고 -->
			<% if(loginUser != null && loginUser.getUserId().equals("admin")) {%>
			<button onclick="location.href='views/notice/noticeInsertForm.jsp'" id="writeNoBtn">글쓰기</button>
			<% } %>
		
		</div>
		<script>
		$(function(){
			$('#listArea td').mouseenter(function(){
				$(this).parent().css({'background':'darkgray','cursor':'pointer'})
			}).mouseout(function(){
				$(this).parent().css('background','none');
			}).click(function(){
				var num =$(this).parent().children().eq(0).text();
				location.href="<%= request.getContextPath() %>/detail.no?no="+num;
			});//겟 방식으로 넘겼을때 나오는것datail.no?no= 는 억지로 쿼리스트링을 만들어준거임..
		});
		
		</script>
		
	</div>
</body>
</html>