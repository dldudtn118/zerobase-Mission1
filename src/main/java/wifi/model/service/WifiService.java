package wifi.model.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import wifi.model.dao.WifiDao;
import wifi.model.vo.LocalHistory;
import wifi.model.vo.WifiInfo;

public class WifiService {
	WifiDao wd = new WifiDao();

	public int getWifiInfo(String sessionId) {

		
		if(0==wd.sessionIdInsert(sessionId)) {
			return 0;
		}
		
	     ArrayList<WifiInfo> alw = new ArrayList<WifiInfo>();
		  
		 try { 
			  alw = WifiAPI(alw); //api값 리스트에 넣기 
		} catch (IOException e) {
		  e.printStackTrace(); 
		  }
		  
		  if(alw == null || alw.size()==0) {//api 가져올때 에러나 0일경우
			  return -1;
		  }
			
			  if(alw.size() != wd.wifiInsert(alw,sessionId)) { //db저장에러
				  return 0;
				  }
			 
		 		
		return alw.size();
	}
	
	public void deleteSession(String sessionId) {
		if(!sessionId.equals(wd.sessionIdSelect(sessionId))) {
			return;
		}
		
		if(0==wd.sessionIdDelete(sessionId)) {
			System.out.println("DB세션삭제 에러");
		}else {
			System.out.println("제거된 세션:"+sessionId);
		}
	}
	
	public ArrayList<WifiInfo> selectWifiInfo(String sessionId,double ulat,double ulnt) {
		String dbSessionId = wd.sessionIdSelect(sessionId);
		if(dbSessionId.equals("")) { //세션이 바껴 db가삭제됬으니 다시조회명령
			return null;
		}
		if(0==wd.localHistoryInsert(ulat,ulnt)) {
			System.out.println("위치히스토리목록 저장실패");
		}
		return wd.wifiInfoSelect(sessionId,ulat,ulnt);
	}
	
	public ArrayList<LocalHistory> selectLocalHistory(){
		return wd.localHistorySelect();
	}
	
	public int deleteLocalHistory(int pNum) {
		return wd.LocalHistoryDelete(pNum);
	}
	
    public ArrayList<WifiInfo> WifiAPI(ArrayList<WifiInfo> alw) throws IOException {
		int listTotalCount=0;
		int pageStart=1;
		int pageFinish=1;
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		String date = simpleDateFormat.format(new Date());
		
		while(true) {
		StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088");
		/* URL */
		urlBuilder.append(
				"/" + URLEncoder.encode("5543774851646c643130347267644e67", "UTF-8")); /*
																						 * 인증키 (sample사용시에는 호출시 제한됩니다.)
																						 */
		urlBuilder.append("/" + URLEncoder.encode("json", "UTF-8")); /*
																		 * 요청파일타입 (xml,xmlf,xls,json)
																		 */
		urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo", "UTF-8"));
		/* 서비스명 (대소문자 구분 필수입니다.) */

		urlBuilder.append("/" + URLEncoder.encode(String.valueOf(pageStart), "UTF-8"));//시작페이지
		urlBuilder.append("/" + URLEncoder.encode(String.valueOf(pageFinish), "UTF-8"));//끝페이지 최고 총1000개만가능

		// *요청종료위치(sample인증키 사용시 5이상 숫자 선택 안 됨)*/
		// 상위 5개는 필수적으로 순서바꾸지 않고 호출해야 합니다.
		// 서비스별 추가 요청 인자이며 자세한 내용은 각 서비스별 '요청인자'부분에
		// 자세히 나와 있습니다.	
			
		urlBuilder.append("/" + URLEncoder.encode(date, "UTF-8")); /*
																 * 서비스별 추가 요청인자들
																 */
		
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/xml");
		//System.out.println("Response code: " + conn.getResponseCode()); /*
		//																 * 연결 자체에 대한 확인이 필요하므로 추가합니다.
		//																 */
		BufferedReader rd;
		// 서비스코드가 정상이면 200~300사이의 숫자가 나옵니다.
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			return null;
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();
		String wifiAllString = sb.toString();
		try {
			// 1. 문자열 형태의 JSON을 파싱하기 위한 JSONParser 객체 생성.
			JSONParser parser = new JSONParser();

			// 2. 문자열을 JSON 형태로 JSONObject 객체에 저장.
			JSONObject obj = (JSONObject) parser.parse(wifiAllString);

			Map<String, Object> WifiArrMap = (Map) obj.get("TbPublicWifiInfo");
			
			listTotalCount = (int)((long) WifiArrMap.get("list_total_count"));
			if(listTotalCount == 0) {// 와이파이0개면 메소드종료
				return null;
			}
			
			if(pageFinish==1) {//처음시작이면
				if(listTotalCount<1001) { // 1~1000까지 가능 
					pageFinish = listTotalCount;
				}else {
					pageFinish += 999;
				}
				continue;
			}else {//반복이면
				pageStart += 1000;
				pageFinish += 1000;
				if(pageFinish>=listTotalCount) {
					pageFinish=listTotalCount;
				}
			}
			JSONArray jsonArr = (JSONArray) WifiArrMap.get("row");
			for(int i =0; i<jsonArr.size(); i++) { 
				//배열 안에 있는것도 JSON형식 이기 때문에 JSON Object 로 추출 
			JSONObject wifiObject =	(JSONObject)jsonArr.get(i);
			WifiInfo wi = new WifiInfo();
			wi.setxSwifiMgrNo((String)wifiObject.get("X_SWIFI_MGR_NO"));
			wi.setxSwifiWrdifc((String)wifiObject.get("X_SWIFI_WRDOFC"));
			wi.setxSwifiMainNm((String)wifiObject.get("X_SWIFI_MAIN_NM"));
			wi.setxSwifiAdres1((String)wifiObject.get("X_SWIFI_ADRES1"));
			wi.setxSwifiAdres2((String)wifiObject.get("X_SWIFI_ADRES2"));
			wi.setxSwifiInstlFloor((String)wifiObject.get("X_SWIFI_INSTL_FLOOR"));
			wi.setxSwifiInstlTY((String)wifiObject.get("X_SWIFI_INSTL_TY"));
			wi.setxSwifiInstlMBY((String)wifiObject.get("X_SWIFI_INSTL_MBY"));
			wi.setxSwifiSvcSe((String)wifiObject.get("X_SWIFI_SVC_SE"));
			wi.setxSwifiCMCWR((String)wifiObject.get("X_SWIFI_CMCWR"));
			wi.setxSwifiCNSTCYear((String)wifiObject.get("X_SWIFI_CNSTC_YEAR"));
			wi.setxSwifiInOutDoor((String)wifiObject.get("X_SWIFI_INOUT_DOOR"));
			wi.setxSwifiREMARS3((String)wifiObject.get("X_SWIFI_REMARS3"));
			wi.setLnt(Double.parseDouble((String)wifiObject.get("LNT")));//api에서 lnt lat가 반대로되어있음.
			wi.setLat(Double.parseDouble((String)wifiObject.get("LAT")));
			wi.setWorkDttm((String)wifiObject.get("WORK_DTTM"));
			
			alw.add(wi);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(pageStart>pageFinish) {//탈출조건
			break;
		}
		}
		return alw;
	}

}
