package com.pf.www.forum.controller;

import java.util.HashMap;
import java.util.concurrent.TimeoutException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pf.www.forum.service.JoinService;

@Controller
public class JoinController {
	private final static Logger log = LoggerFactory.getLogger(JoinController.class);
	
	@Autowired
	JoinService joinService;
	
	@RequestMapping("/join.do")
	public ModelAndView join(@RequestParam HashMap<String,String> params,HttpServletRequest request) {

		/* 회원가입 서비스를 통해 작동하고 반환된 값을 기준으로 JSP 파일에 msg 전달 */
		
		int result = joinService.join(params, request);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("result", result);
		
		if(result == -99) {
			mv.addObject("msg","중복된 id 입니다");
			
		}else {
			mv.addObject("msg", result == 1 ? "회원가입 되었습니다." : "실패");
		}
		
		mv.setViewName("login");		
		return mv;
	}
	
	@RequestMapping("/emailAuth.do")
	public ModelAndView emailAuth(@RequestParam("uri") String uri) {
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		
		boolean result = false;
		
		/* Exception 각각 처리 */
		try {
			result = joinService.emailAuth(uri);
			
			mv.addObject("result",result);
			mv.addObject("msg","인증되었습니다");	
			
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			mv.addObject("result",result);
			mv.addObject("msg","uri 값이 올바르지 않습니다.");
			
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			mv.addObject("result",result);
			mv.addObject("msg","잘못된 uri 입니다.");
			
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			mv.addObject("result",result);
			mv.addObject("msg","인증메일 유효시간 만료");
		}
		
		return mv;
	}
}
