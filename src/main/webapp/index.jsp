<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="wifi.model.vo.WifiInfo, java.util.ArrayList" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>와이파이 정보 구하기</title>
<link href="/resources/css/greenTable.css" rel="stylesheet" type="text/css"> 
</head>
<body>
	<%
		double lat=0;
		double lnt=0;
		if(request.getParameter("lat")!=null){
		lat = Double.parseDouble(request.getParameter("lat"));
		lnt = Double.parseDouble(request.getParameter("lnt"));
		}
  		ArrayList<WifiInfo> alw = (ArrayList<WifiInfo>)request.getAttribute("alw");
  		String noSession = (String)request.getAttribute("noSession");
	%>
	<h1>와이파이 정보 구하기</h1>
	<a href="#">홈</a>│<a href="/select.lh">위치 히스토리 목록</a>│<a href="javascript:wifiINsert();">Open API 와이파이 정보 가져오기</a><br>
	LAT:<input type ="number" id="lat" step="0.0000001" min="0" max="90" 
		<%
			if(lat==0){
		%>
			value =0.0>,
		<%
			}else{
		%> 
			value =<%=lat%>>,
		<%
			}
		%>
	LNT:<input type ="number" id="lnt" step="0.0000001" min="0" max="180"
		<%
			if(lnt==0){
		%>
			value =0.0>,
		<%
			}else{
		%> 
			value =<%=lnt%>>,
		<%
			}
		%> 
	<button type="button" onclick="locations()">내 위치 가져오기</button>
	<button type="button" onclick="locationSubmit()">근처 WIPI 정보 보기</button>

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
 	
	
        function locations() {
        	navigator.geolocation.getCurrentPosition(success, error)
        	function success(position){		
        		document.getElementById('lat').value = position.coords.latitude.toFixed(7);
        		document.getElementById('lnt').value = position.coords.longitude.toFixed(7);
        	}
     		 function error() {
     			 alert("내 위치를 가져오는데 실패하였습니다.");
            }
        }    
    	function locationSubmit(){
    		const lat = document.getElementById('lat').value;
    		const lnt = document.getElementById('lnt').value;
    		if(lat==0 || lnt==0){
    			alert("내 위치를 둘다 작성 해주세요.")
    		}else if(<%="no".equals(noSession)%>){
    			alert("open api를 다시조회하세요")
    		}else{
    			window.location.href = "/select.Wifi?lat="+lat+"&lnt="+lnt;
    		}
    		
    	}
        
        
    </script>
	<table id="customers">
  <tr align ="center">
    <th>거리(Km)</th>
    <th>관리번호</th>
    <th>자치구</th>
    <th>와이파이명</th>
    <th>도로명주소</th>
    <th>상세주소</th>
    <th>설치위치(층)</th>
    <th>설치유형</th>
    <th>설치기관</th>
    <th>서비스구분</th>
    <th>망종류</th>
    <th>설치년도</th>
    <th>실내외구분</th>
    <th>WIFI접속환경</th>
    <th>X좌표</th>
    <th>Y좌표</th>
    <th>작업일자</th>
  </tr>
  	<%	
		if(alw==null){
	%>
  <tr>
    <td colspan="17" align="center">위치 정보를 입력한 후에 조회해 주세요.</td>
  </tr>
  	<%}else{
  		for(WifiInfo wi:alw){
  	%>
  		<tr align ="center">
  			<td><%=wi.getDistance()%></td>
  			<td><%=wi.getxSwifiMgrNo()%></td>
  			<td><%=wi.getxSwifiWrdifc()%></td>
  			<td><%=wi.getxSwifiMainNm()%></td>
  			<td><%=wi.getxSwifiAdres1()%></td>
  			<td><%=wi.getxSwifiAdres2()%></td>
  			<td><%=wi.getxSwifiInstlFloor()%></td>
  			<td><%=wi.getxSwifiInstlTY()%></td>
  			<td><%=wi.getxSwifiInstlMBY()%></td>
  			<td><%=wi.getxSwifiSvcSe()%></td>
  			<td><%=wi.getxSwifiCMCWR()%></td>
  			<td><%=wi.getxSwifiCNSTCYear()%></td>
  			<td><%=wi.getxSwifiInOutDoor()%></td>
  			<td><%=wi.getxSwifiREMARS3()%></td>
  			<td><%=wi.getLnt()%></td>
  			<td><%=wi.getLat()%></td>
  			<td><%=wi.getWorkDttm()%></td>
  	<%}}%>
  	
</table>
</body>
</html>