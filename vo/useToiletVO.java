package com.pleasetoilet.vo;

public class useToiletVO {
	private int uno;
	private String tno;
	private String tname;
	private String usedate;
	public useToiletVO(int uno, String tno, String tname, String usedate) {
		super();
		this.uno = uno;
		this.tno = tno;
		this.tname = tname;
		this.usedate = usedate;
	}
	public useToiletVO() {
		super();
	}
	public int getUno() {
		return uno;
	}
	public void setUno(int uno) {
		this.uno = uno;
	}
	public String getTno() {
		return tno;
	}
	public void setTno(String tno) {
		this.tno = tno;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public String getUsedate() {
		return usedate;
	}
	public void setUsedate(String usedate) {
		this.usedate = usedate;
	}
}
