package com.pleasetoilet.vo;

public class ReviewVO {
	private int uno;
	private String contents;
	private String id;
	private String tname;
	private String usedate;
	public ReviewVO(int uno, String contents, String id, String tname, String usedate) {
		super();
		this.uno = uno;
		this.contents = contents;
		this.id = id;
		this.tname = tname;
		this.usedate = usedate;
	}
	public ReviewVO() {
		super();
	}
	public int getUno() {
		return uno;
	}
	public void setUno(int uno) {
		this.uno = uno;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
