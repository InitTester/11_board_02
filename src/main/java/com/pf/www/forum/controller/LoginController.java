package com.pf.www.forum.controller;

import java.util.Calendar;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pf.www.forum.dto.MemberDto;
import com.pf.www.forum.message.MessageEnum;
import com.pf.www.forum.notice.service.BoardService;
import com.pf.www.forum.service.LoginService;

@Controller
public class LoginController {
	private final static Logger log = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/loginPage.do")
	public String loginPage() {
		return "login";
	}
	
	/*jsp는 page를 열때 .do로 열고, 어떤 액션을 실핼 할때도 .do로 한다.
	페이지는 ~ 페이지.do / 실행은 .do로 한다 
	추후 나중에 loginPage.do 같은 것은 rest로 */

	@RequestMapping("/login.do")
	public ModelAndView login(@RequestParam HashMap<String, String> params, HttpServletRequest request){
		
		log.info("email : " + params.get("email"));
		
		/*  ok - 사용자가 있는 경우 : 비밀번호, fail - 사용자가 없는 경우
		 * 예외의 전파는 예외를 던지고 받는 것, catch 문에서 예외던지기는 아직 안했음
		 */
		ModelAndView mv = new ModelAndView();
		
		try {
			log.info("login 메서드 접속");
			MemberDto memberDto = loginService.login(params);
			log.info("login ssss 접속");

			/*
			 * ObjectUtils.isEmpty() 메서드는 Srping 프로엠워크의 유틸 클래스, 
			 * 다양한 유형의 객체를 인수로 받아 객체가 null 또는 길이가 0인 배열인지 확인
			 * if(null || 0)
			 */
			if(!ObjectUtils.isEmpty(memberDto)) {
				
				// 세션 처리				
				HttpSession session = request.getSession();
				session.setAttribute("memberId", memberDto.getMemberId());
				session.setAttribute("memberSeq", memberDto.getMemberSeq());
				mv.addObject("key", Calendar.getInstance().getTimeInMillis());
				
				
//				mv.setViewName(request.getContextPath());
				mv.setViewName("index");
//				mv.setViewName("main");
				
				log.info("memberId : " + session.getAttribute("memberId"));
				log.info("memberSeq : " + session.getAttribute("memberSeq"));
			}else {
				// pwd 틀릴 시 
				mv.setViewName("login");
				mv.addObject("code",MessageEnum.USER_PASSWD_WRONG.getCode());
				mv.addObject("msg",MessageEnum.USER_PASSWD_WRONG.getDescription());						
			}
			
			return mv;
			
		} catch (EmptyResultDataAccessException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			// 조회된 값이 없을 때 에러 처리
			// 검색되는 회원정보가 없다.
			mv.setViewName("login");
			mv.addObject("code",MessageEnum.USER_NOT_FOUND.getCode());
			mv.addObject("msg",MessageEnum.USER_NOT_FOUND.getDescription());	
			return mv;
		}
	}
	
	@RequestMapping("/logout.do")
	public ModelAndView logout(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		
		// 세션 무효화
		request.getSession().invalidate();
		return mv;		
	}
}
