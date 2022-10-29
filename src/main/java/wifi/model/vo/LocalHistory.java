package wifi.model.vo;

public class LocalHistory {
	private int pNum;
	private double lnt;
	private double lat;
	private String checkTime;
	
	public LocalHistory() {
		
	}
	
	public LocalHistory(int pNum, double lnt, double lat, String checkTime) {
		super();
		this.pNum = pNum;
		this.lnt = lnt;
		this.lat = lat;
		this.checkTime = checkTime;
	}

	public int getpNum() {
		return pNum;
	}

	public void setpNum(int pNum) {
		this.pNum = pNum;
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

	public String getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}

	@Override
	public String toString() {
		return "LocalHistory [pNum=" + pNum + ", lnt=" + lnt + ", lat=" + lat + ", checkTime=" + checkTime + "]";
	}
	
	
}
