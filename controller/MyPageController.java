package com.pleasetoilet.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pleasetoilet.dao.MemberDAO;
import com.pleasetoilet.vo.useToiletVO;


@Controller
public class MyPageController {
	@Autowired
	MemberDAO dao;

	@RequestMapping("mypage")
	public String mypage(HttpSession session) {
		if (session.getAttribute("id") == null)
			return "redirect:/home";
		else
			return "mypage/mypage";
	}

	@RequestMapping(value = "mypage/usedlog", method = RequestMethod.GET)
	public ModelAndView getlog(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		List<useToiletVO> list = dao.getUseList((String)session.getAttribute("id"));
		mv.addObject("logList", list);
		mv.setViewName("mypage/usedlog");
		
		return mv;
	}
	
	/* map 페이지에서 이용내역 등록 */
	@RequestMapping(value = "mypage/usedlog", method = RequestMethod.POST)
	public void savelog(HttpSession session, String tno) {
		if(session.getAttribute("id") != null)
			dao.saveUseList(tno, (String)session.getAttribute("id"));
	}

	@RequestMapping(value = "mypage/changepassword", method = RequestMethod.GET)
	public String changePW() {
		return "mypage/changePassword";
	}

	@RequestMapping(value = "mypage/signout", method = RequestMethod.GET)
	public void signOut() {

	}

	@RequestMapping(value = "mypage/changepassword", method = RequestMethod.POST)
	public ModelAndView pwChangePost(HttpSession session, String oldpw, String newpw, String newpwcheck) {
		ModelAndView mv = new ModelAndView();
		String id = (String)session.getAttribute("id");
		boolean check = dao.checkIdAndPW(id, oldpw);
		if(check == true) {
			dao.changePW(id, newpw);
			mv.addObject("msg", "success");
		} else mv.addObject("msg", "fail");
		mv.setViewName("mypage/mypage");
		return mv;
		
	}
	
	
	@RequestMapping(value = "mypage/signout", method = RequestMethod.POST)
	public ModelAndView signoutPost(HttpSession session, String pw) {
		ModelAndView mv = new ModelAndView();
		String id = (String)session.getAttribute("id");
		boolean check = dao.checkIdAndPW(id, pw);
		if(check == true) {
			dao.deleteMember(id);
			session.invalidate();
			mv.setViewName("home");
		} else {
			mv.setViewName("mypage/mypage");
			mv.addObject("msg", "fail");
		}
		return mv;
	}
}
