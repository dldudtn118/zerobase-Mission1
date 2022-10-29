<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>와이파이 정보 구하기</title>
</head>
<body>
	<%
	  int listTotalCount = (Integer)session.getAttribute("listTotalCount");
      if(listTotalCount==-1){%>
		<h1 align="Center">API정보를 가져오는데 실패하였습니다.</h1>
	<%	
	  }else if(listTotalCount==0){%>
		<h1 align="Center">데이터베이스 저장에 실패하였습니다.</h1>
	<%
	  }else{
	%>
	    <h1 align="Center">${sessionScope.listTotalCount} 개의 WIFI 정보를 정상적으로 저장하였습니다.</h1>
	<%
	  }
	%>
	<p align="Center">
	<a href = "/" >홈으로</a>
	</p>
</body>
</html>