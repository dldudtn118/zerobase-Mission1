<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="wifi.model.vo.LocalHistory, java.util.ArrayList" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>와이파이 정보 구하기</title>
<link href="/resources/css/greenTable.css" rel="stylesheet" type="text/css"> 
</head>
<body>
	<%
  		ArrayList<LocalHistory> alLH = (ArrayList<LocalHistory>)request.getAttribute("alLH");
  		String noSession = (String)request.getAttribute("noSession");
	%>
	<h1>위치 히스토리 목록</h1>
	<a href="/">홈</a>│<a href="#">위치 히스토리 목록</a>│<a href="javascript:wifiINsert();">Open API 와이파이 정보 가져오기</a><br>

	<script>
   		 function wifiINsert(){
    	<%
    		String oldSessionId = (String)session.getAttribute("sessionId");
    		String nowSessionId = session.getId();
    	if((oldSessionId !=null && nowSessionId != null) || oldSessionId==nowSessionId){
    	%>
    		alert("세션 종료후 이용해주세요(새로고침)");
    	<%
    	}else{
    	%>
    		window.location.href = "/insert.wifi";
    	<%
    	}
    	%>
        }   
        
    	function lhDelete(pNum){
    		window.location.href = "/delete.lh?pNum="+pNum;
    	}
        
    </script>
	<table id="customers">
  <tr align ="center">
    <th>ID</th>
    <th>X좌표</th>
    <th>Y좌표</th>
    <th>조회일자</th>
    <th>비고</th>
  </tr>
  	<%	
		if(alLH==null){
	%>
  <tr>
    <td colspan="17" align="center">근처 WIFI 정보를 조회해 주세요.</td>
  </tr>
  	<%}else{
  		for(LocalHistory lh:alLH){
  	%>
  		<tr align ="center">
  			<td><%=lh.getpNum()%></td>
  			<td><%=lh.getLnt()%></td>
  			<td><%=lh.getLat()%></td>
  			<td><%=lh.getCheckTime()%></td>
  			<td><button type="button" onclick="lhDelete(<%=lh.getpNum()%>)">삭제</button></td>
  	<%}}%>
  	
</table>
</body>
</html>