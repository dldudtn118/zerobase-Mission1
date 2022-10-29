package wifi.model.vo;

public class WifiInfo {
	private double distance; //거리;
	private String xSwifiMgrNo;    //관리번호
	private String xSwifiWrdifc;     //자치구
	private String xSwifiMainNm;     //와이파이명
	private String xSwifiAdres1;      //도로명주소
	private String xSwifiAdres2;      //상세주소
	private String xSwifiInstlFloor; //설치위치(층)
	private String xSwifiInstlTY;   // 설치유형
	private String xSwifiInstlMBY;   //설치기관
	private String xSwifiSvcSe;      // 서비스구분
	private String xSwifiCMCWR;      //망종류
	private String xSwifiCNSTCYear;  //설치년도
	private String xSwifiInOutDoor;  // 실내외구분
	private String xSwifiREMARS3;     //WIFI접속 환경
	private double lnt;                  // X좌표 경도 180도
	private double lat;                   // Y좌표 위도 90도
	private String workDttm;    // 작업일자
	private String iD;             // 사용자 번호
	
	public WifiInfo() {
		
	}

	public WifiInfo(double distance, String xSwifiMgrNo, String xSwifiWrdifc, String xSwifiMainNm, String xSwifiAdres1,
			String xSwifiAdres2, String xSwifiInstlFloor, String xSwifiInstlTY, String xSwifiInstlMBY,
			String xSwifiSvcSe, String xSwifiCMCWR, String xSwifiCNSTCYear, String xSwifiInOutDoor,
			String xSwifiREMARS3, double lnt, double lat, String workDttm, String iD) {
		super();
		this.distance = distance;
		this.xSwifiMgrNo = xSwifiMgrNo;
		this.xSwifiWrdifc = xSwifiWrdifc;
		this.xSwifiMainNm = xSwifiMainNm;
		this.xSwifiAdres1 = xSwifiAdres1;
		this.xSwifiAdres2 = xSwifiAdres2;
		this.xSwifiInstlFloor = xSwifiInstlFloor;
		this.xSwifiInstlTY = xSwifiInstlTY;
		this.xSwifiInstlMBY = xSwifiInstlMBY;
		this.xSwifiSvcSe = xSwifiSvcSe;
		this.xSwifiCMCWR = xSwifiCMCWR;
		this.xSwifiCNSTCYear = xSwifiCNSTCYear;
		this.xSwifiInOutDoor = xSwifiInOutDoor;
		this.xSwifiREMARS3 = xSwifiREMARS3;
		this.lnt = lnt;
		this.lat = lat;
		this.workDttm = workDttm;
		this.iD = iD;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}
	
	public void setDistance(double ulat, double ulnt) {//자바로 거리구하기
		double dLat = Math.toRadians(this.lat-ulat);
		double dLnt = Math.toRadians(this.lnt-ulnt);
		
		double a = Math.sin(dLat/2)* Math.sin(dLat/2)+ Math.cos(Math.toRadians(ulat))*
				Math.cos(Math.toRadians(this.lat))* Math.sin(dLnt/2)* Math.sin(dLnt/2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		double distance = Math.round((6371.0*c)*10000)/10000.0;
		this.distance = distance;
	}

	public String getxSwifiMgrNo() {
		return xSwifiMgrNo;
	}

	public void setxSwifiMgrNo(String xSwifiMgrNo) {
		this.xSwifiMgrNo = xSwifiMgrNo;
	}

	public String getxSwifiWrdifc() {
		return xSwifiWrdifc;
	}

	public void setxSwifiWrdifc(String xSwifiWrdifc) {
		this.xSwifiWrdifc = xSwifiWrdifc;
	}

	public String getxSwifiMainNm() {
		return xSwifiMainNm;
	}

	public void setxSwifiMainNm(String xSwifiMainNm) {
		this.xSwifiMainNm = xSwifiMainNm;
	}

	public String getxSwifiAdres1() {
		return xSwifiAdres1;
	}

	public void setxSwifiAdres1(String xSwifiAdres1) {
		this.xSwifiAdres1 = xSwifiAdres1;
	}

	public String getxSwifiAdres2() {
		return xSwifiAdres2;
	}

	public void setxSwifiAdres2(String xSwifiAdres2) {
		this.xSwifiAdres2 = xSwifiAdres2;
	}

	public String getxSwifiInstlFloor() {
		return xSwifiInstlFloor;
	}

	public void setxSwifiInstlFloor(String xSwifiInstlFloor) {
		this.xSwifiInstlFloor = xSwifiInstlFloor;
	}

	public String getxSwifiInstlTY() {
		return xSwifiInstlTY;
	}

	public void setxSwifiInstlTY(String xSwifiInstlTY) {
		this.xSwifiInstlTY = xSwifiInstlTY;
	}

	public String getxSwifiInstlMBY() {
		return xSwifiInstlMBY;
	}

	public void setxSwifiInstlMBY(String xSwifiInstlMBY) {
		this.xSwifiInstlMBY = xSwifiInstlMBY;
	}

	public String getxSwifiSvcSe() {
		return xSwifiSvcSe;
	}

	public void setxSwifiSvcSe(String xSwifiSvcSe) {
		this.xSwifiSvcSe = xSwifiSvcSe;
	}

	public String getxSwifiCMCWR() {
		return xSwifiCMCWR;
	}

	public void setxSwifiCMCWR(String xSwifiCMCWR) {
		this.xSwifiCMCWR = xSwifiCMCWR;
	}

	public String getxSwifiCNSTCYear() {
		return xSwifiCNSTCYear;
	}

	public void setxSwifiCNSTCYear(String xSwifiCNSTCYear) {
		this.xSwifiCNSTCYear = xSwifiCNSTCYear;
	}

	public String getxSwifiInOutDoor() {
		return xSwifiInOutDoor;
	}

	public void setxSwifiInOutDoor(String xSwifiInOutDoor) {
		this.xSwifiInOutDoor = xSwifiInOutDoor;
	}

	public String getxSwifiREMARS3() {
		return xSwifiREMARS3;
	}

	public void setxSwifiREMARS3(String xSwifiREMARS3) {
		this.xSwifiREMARS3 = xSwifiREMARS3;
	}

	public double getLnt() {
		return lnt;
	}

	public void setLnt(double lnt) {
		this.lnt = lnt;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public String getWorkDttm() {
		return workDttm;
	}

	public void setWorkDttm(String workDttm) {
		this.workDttm = workDttm;
	}

	public String getiD() {
		return iD;
	}

	public void setiD(String iD) {
		this.iD = iD;
	}

	@Override
	public String toString() {
		return "WifiInfo [distance=" + distance + ", xSwifiMgrNo=" + xSwifiMgrNo + ", xSwifiWrdifc=" + xSwifiWrdifc
				+ ", xSwifiMainNm=" + xSwifiMainNm + ", xSwifiAdres1=" + xSwifiAdres1 + ", xSwifiAdres2=" + xSwifiAdres2
				+ ", xSwifiInstlFloor=" + xSwifiInstlFloor + ", xSwifiInstlTY=" + xSwifiInstlTY + ", xSwifiInstlMBY="
				+ xSwifiInstlMBY + ", xSwifiSvcSe=" + xSwifiSvcSe + ", xSwifiCMCWR=" + xSwifiCMCWR
				+ ", xSwifiCNSTCYear=" + xSwifiCNSTCYear + ", xSwifiInOutDoor=" + xSwifiInOutDoor + ", xSwifiREMARS3="
				+ xSwifiREMARS3 + ", lnt=" + lnt + ", lat=" + lat + ", workDttm=" + workDttm + ", iD=" + iD + "]";
	}

	
	
}
