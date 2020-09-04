package com.pleasetoilet.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pleasetoilet.dao.ReviewDAO;
import com.pleasetoilet.vo.ReviewVO;

@Controller
public class ReviewController {
	@Autowired
	ReviewDAO dao;
	
	@RequestMapping("review")
	public ModelAndView getReview(HttpSession session, String tno) {
		ModelAndView mv = new ModelAndView();
		if(session.getAttribute("id") == null) {
			mv.setViewName("redirect:/home");
		} else {
			List<ReviewVO> list = dao.getReview(tno, (String)session.getAttribute("id"));
			mv.addObject("reviewList", list);
			mv.setViewName("review");
		}
		return mv;
	}
	
	@RequestMapping(value = "writereview",method = RequestMethod.GET)
	public ModelAndView writeReview(HttpSession session, String uno) {
		ModelAndView mv = new ModelAndView();
		if(session.getAttribute("id") == null)
			mv.setViewName("redirect:/home");
		else {
			mv.addObject("uno", uno);
			mv.setViewName("writereview");
		}
		return mv;
	}
	
	@RequestMapping(value = "writereview", method = RequestMethod.POST)
	public String saveReview(String uno, String contents) {
		dao.saveReview(uno, contents);
		return "mypage/mypage";
	}
}
