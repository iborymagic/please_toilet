package com.pleasetoilet.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pleasetoilet.dao.MemberDAO;

@Controller
public class LoginController {
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
	//로그인 페이지 이동
	@RequestMapping(value="login", method = RequestMethod.GET)
	public String login(HttpSession session) {
		
		if(session.getAttribute("id") != null) {
			return "redirect:/home";			
		}
		else {
			return "login/login";
		}
	}
	//로그인 데이터 값 처리
	@RequestMapping(value="login", method = RequestMethod.POST)
	public ModelAndView loginCheck(String id, String pw, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		boolean check = dao.loginCheck(id, pw);
		if(check == true) {
			mv.setViewName("home");
			mv.addObject("msg", "success");
			session.setAttribute("id", id);
			
		}else {
			mv.setViewName("login/login");
			mv.addObject("msg", "fail");
		}
		return mv;
	}
	
	//로그아웃
	@RequestMapping(value = "logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "home";
	}
	
	@RequestMapping(value = "login/findpw", method=RequestMethod.GET)
	public String findPW(HttpSession session) {
		
		if(session.getAttribute("id") != null) {
			return "redirect:/home";			
		}
		return "login/findPW";
	}
	
	
	@RequestMapping(value = "login/findpw", method=RequestMethod.POST)
	public ModelAndView checkIDAndEmail(String id, String email) {
		boolean key = dao.checkIDAndEmail(id, email);
		ModelAndView mv = new ModelAndView();
		if(key) {
			mv.addObject("id", id);
			mv.setViewName("login/changePassword");
		}else {
			mv.addObject("msg", "fail");
			mv.setViewName("login/findPW");
		}
		return mv;
	}
	
	
	@RequestMapping(value = "login/changepassword", method=RequestMethod.POST)
	public String changePassword(String id, String pw1) {
		dao.changePW(id, pw1);
		return "home";
	}
	
	@RequestMapping(value = "login/findid", method = RequestMethod.GET)
	public String findID(HttpSession session) {
		if(session.getAttribute("id") != null) {
			return "redirect:/home";			
		}
		return "login/findID";
	}
	
	@RequestMapping(value = "login/findid",method = RequestMethod.POST)
	public ModelAndView findIDbyEmail(String email) {
		ModelAndView mv = new ModelAndView();
		List<String> list = dao.findByEmail(email);
		mv.addObject("list", list);
		mv.setViewName("login/getID");
		return mv;
	}
}