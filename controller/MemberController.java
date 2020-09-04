package com.pleasetoilet.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pleasetoilet.dao.MemberDAO;
import com.pleasetoilet.vo.MemberVO;



@Controller
public class MemberController {
	@Autowired
	MemberDAO dao;
	
	class CheckIt{
		
		public boolean isKey() {
			return key;
		}

		public void setKey(boolean key) {
			this.key = key;
		}

		boolean key;
	}
	@RequestMapping(value = "/idcheck")  
	@ResponseBody 
	public CheckIt idCheck(String id) {
		  CheckIt c = new CheckIt(); 
		  c.key = dao.checkId(id); 
		  return c;		 
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signUP(HttpSession session) {
		if(session.getAttribute("id") != null) {
			return "home";
		}
		else return "signUp";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST) 
	public String goHome(MemberVO vo) { 
		dao.signUp(vo);
		return "redirect:/home";
	}
/*	@RequestMapping(value="/mypage/changepassword",method=RequestMethod.GET)
	public String changePW1() {
		return "changepw";
	}
	@RequestMapping(value="/mypage/changepassword",method=RequestMethod.POST)
	public String changePW2(int mno, String pw) {
		dao.changePW(mno, pw);
		return "redirect:/mypage";
	}*/
}
