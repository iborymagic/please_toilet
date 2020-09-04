package com.pleasetoilet.vo;

public class ToiletVO {
	private String tno;
	private String latitude;
	private String longitude;
	private String bigName;
	private String smallName;
	private int dis;
	public ToiletVO(String tno, String latitude, String longitude, String bigName, String smallName, int dis) {
		super();
		this.tno = tno;
		this.latitude = latitude;
		this.longitude = longitude;
		this.bigName = bigName;
		this.smallName = smallName;
		this.dis = dis;
	}
	public ToiletVO() {
		super();
	}
	public String getTno() {
		return tno;
	}
	public void setTno(String tno) {
		this.tno = tno;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getBigName() {
		return bigName;
	}
	public void setBigName(String bigName) {
		this.bigName = bigName;
	}
	public String getSmallName() {
		return smallName;
	}
	public void setSmallName(String smallName) {
		this.smallName = smallName;
	}
	public int getDis() {
		return dis;
	}
	public void setDis(int dis) {
		this.dis = dis;
	}
	@Override
	public String toString() {
		return "ToiletVO [tno=" + tno + ", latitude=" + latitude + ", longitude=" + longitude + ", bigName=" + bigName
				+ ", smallName=" + smallName + ", dis=" + dis + "]";
	}
	
	
	

}
