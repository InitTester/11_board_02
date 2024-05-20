package com.pf.www.forum.notice.controller.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pf.www.forum.notice.service.BoardService;

@RestController
public class RestNoticeController {
	private final static Logger log = LoggerFactory.getLogger(RestNoticeController.class);

	@Autowired
	BoardService boardService;

	@GetMapping("/forum/notice/vote.do")
	@ResponseBody
	public int vote(
			@RequestParam("boardSeq") Integer boardSeq, 
			@RequestParam("boardTypeSeq") Integer boardTypeSeq,
			@RequestParam("isLike") String isLike,
			@RequestParam("thumb") String thumb,
			HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		int memberSeq = -1;
		
		try {
			memberSeq = (int) session.getAttribute("memberSeq");	
			log.info("boardSeq : " + boardSeq + ", boardTypeSeq : " + boardTypeSeq + ", isLike : " + isLike + ", thumb : " + thumb+ ", memberSeq : " + memberSeq);
			
		} 
		catch (NullPointerException nep) {
			log.info("사용자 없음");
		}
		
		String ip = request.getRemoteAddr();
		
		return boardService.vote(boardSeq, boardTypeSeq, isLike ,memberSeq, ip);
	}
	
}
