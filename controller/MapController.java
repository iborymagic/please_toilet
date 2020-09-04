package com.pleasetoilet.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pleasetoilet.dao.ToiletDAO;
import com.pleasetoilet.vo.ToiletVO;

@Controller
public class MapController {
	
	@Autowired
	ToiletDAO dao;

	@RequestMapping(value = "map", method = RequestMethod.GET)
	public String mapView() {
		return "map";
	}
	
	@RequestMapping(value = "map", method = RequestMethod.POST)
	@ResponseBody
	public List<ToiletVO> goMap(double latitude, double longitude, int range) {
		List<ToiletVO> list = dao.findToilet(37.5017169, 127.03945110000001, range);
		return list;
	}
}
