package com.pleasetoilet.vo;

public class MemberVO {
	private int mno;
	private String id;
	private String pw;
	private String email;
	public MemberVO(int mno, String id, String pw, String email) {
		super();
		this.mno = mno;
		this.id = id;
		this.pw = pw;
		this.email = email;
	}
	public MemberVO() {
		super();
	}
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "MemberVO [mno=" + mno + ", id=" + id + ", password=" + pw + ", email=" + email + "]";
	}
	
}
