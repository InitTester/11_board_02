package com.pf.www.forum.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	private final static Logger log = LoggerFactory.getLogger(IndexController.class);
	
	public IndexController() {		
		// TODO Auto-generated constructor stub
		log.info("\n\n---------------------------10 board add pj start---------------------------\n\n");
	}
	
	@RequestMapping("/index.do")
	public String indexPage() {
		return "index";
	}
	
	@RequestMapping("/mainPage.do")
	public String mainPage(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		/* LoginFilter에서 세션을 통해 로그인 되어있지 않으면 페이지 이동시킨다. */
//	    HttpSession session = request.getSession();
//	    if(ObjectUtils.isEmpty(session.getAttribute("memberId"))) {
//	        response.sendRedirect(request.getContextPath() + "/loginPage.do");
//	    }
	    return "main";
	}
}