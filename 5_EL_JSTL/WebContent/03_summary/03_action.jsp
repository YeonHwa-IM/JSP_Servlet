<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <h2>개인정보 입력 결과(action)</h2>
   <jsp:useBean id="person" class="action.model.vo.Person" scope="request"></jsp:useBean><!-- 빈등록=객체생성  -->
   <table>
      <tr>
         <td>성명</td>
         <td><jsp:getProperty property="name" name="person"/>
         </td>
      </tr>
      <tr>
         <td>성별</td>
         <td><jsp:getProperty property="gender" name="person"/></td>
      </tr>
      <tr>
         <td>나이</td>
         <td><jsp:getProperty property="nai" name="person"/></td>
      </tr>
   </table>
</body>
</html>