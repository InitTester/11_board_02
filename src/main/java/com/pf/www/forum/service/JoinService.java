package com.pf.www.forum.service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.transaction.annotation.Transactional;

import com.pf.www.forum.dao.MemberAuthDao;
import com.pf.www.forum.dao.MemberDao;
import com.pf.www.forum.dto.EmailDto;
import com.pf.www.forum.dto.MemberAuthDto;
import com.pf.www.forum.util.EmailUtil;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class JoinService {
	private static final Logger log = LoggerFactory.getLogger(JoinService.class);
	
	private MemberDao memberDao;
	private MemberAuthDao memberAuthDao;
	private EmailUtil emailUtil;
	
	// memberDao setter
	public void setMemberDao(MemberDao memberDao) {
		
		this.memberDao = memberDao;
	}
	
	// memberAuthDao setter
	public  void setMemberAuthDao(MemberAuthDao memberAuthDao) {
		this.memberAuthDao = memberAuthDao;
	}
	
	//EmailUtil setter
	public void setEmailUtil(EmailUtil emailUtil) {
		this.emailUtil = emailUtil;
	}
	 
	@Transactional(rollbackFor = {Exception.class, DataIntegrityViolationException.class, MailAuthenticationException.class})
	/* 회원가입 insert + 이메일 전송 */
	public int join(HashMap<String,String> params, HttpServletRequest request) {
		
		/*변수 선언*/
		String passwd = params.get("passwd");
		String email = params.get("email");
		
		log.info("passwd >>>>>>>>> " + passwd);
		log.info("email >>>>>>>>> " + email);
		
		/* email,pwd 암호화 */
		String encPasswd  = BCrypt.withDefaults().hashToString(12,passwd.toCharArray());
		String encmail  = BCrypt.withDefaults().hashToString(12,email.toCharArray());
		log.info("encPasswd >>>>>>>>> " + encPasswd);
		log.info("encmail >>>>>>>>> " + encmail);
		
		BCrypt.Result result = BCrypt.verifyer().verify(passwd.toCharArray(), encPasswd);
		BCrypt.Result mailresult = BCrypt.verifyer().verify(email.toCharArray(), encmail);
		log.info("result.verified >>>>>>> " + result.verified);
		log.info("mailresult.verified >>>>>>> " + mailresult.verified);
		
		/* 암호화된 값 저장 */
		params.put("passwd", encPasswd);
//		params.put("email", encmail);
		params.put("email", email);
		
		log.info("memberId : " + params.get("memberId"));
		/* 중복 처리  
		 * member 테이블에 저장된 memberSeq 불러오기
		 * 해당 값이 있으면 중복된 사용자가 있기 때문에 확인 하는 작업 */
		int memberCnt = memberDao.getMemberCnt(params.get("memberId"));
		log.info("memeberSeq : " + memberCnt);
		
		if(memberCnt > 0) {
			return -99;
		}
		
		/*가입된 사용자 정보가 없다면 회원 가입 진행
		 * - member 테이블 추가 되었으면 auth 테이블에 추가 구현
		 * */
		int cnt = memberDao.join(params);
		int memberSeq = memberDao.getMemberSeq(params.get("memberId"));
		
		/* 가입 성공 row 1 */
		if(cnt == 1) {
			/* 인증 메일 구조 */
			MemberAuthDto authDto = new MemberAuthDto();
			
			/*
			 * MemberSeq : 회원 가입된 memberSeq 
			 * AuthUri : UUID로 유니크 부여
			 * ExpireDtm : 인증 유효 시간 (현재시간 기준 + 30분), set은 long 타입
			 */
			
			authDto.setMemberSeq(memberSeq); 
			authDto.setAuthUri(UUID.randomUUID().toString().replaceAll("-", "")); 
			
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MINUTE, 30); 
			authDto.setExpireDtm(calendar.getTimeInMillis());
			
			log.info("getExpireDtm : " + authDto.getExpireDtm());
			
			/* db 추가 */
			try {
				memberAuthDao.addMemberAuth(authDto);
			} catch (Exception e) {
				// TODO Auto-generated catch block
			}
			
			
			log.info("System.getenv(\"JASYPT_ENCRYPTION_EMAIL\")+\"@naver.com\") :: "+System.getenv("JASYPT_ENCRYPTION_EMAIL")+"@naver.com");
			/* 인증 메일 발송 */
			EmailDto emailDto = new EmailDto();
			emailDto.setFrom(System.getenv("JASYPT_ENCRYPTION_EMAIL")+"@naver.com");
			emailDto.setReceiver(email);
			emailDto.setSubject("회원 가입 인증 확인해주세요.");
			
			/* 인증 확인을 위한 url 링크 처리 
			 * 현재 로컬에서 하기 때문에 주소는 localhost 사용이지만
			 * 추후 실제로 사용될 서버 주소로 변경하면 된다.*/
			
			log.info("contextroot : " + request.getContextPath());			
			
			String html = "<a href='http://localhost:8080/"+request.getContextPath()+"/emailAuth.do?uri="+authDto.getAuthUri()+"'>인증하기</a>";
			log.info("html : " + html);
			
			emailDto.setText(html);			
			emailUtil.sendMail(emailDto,true);
		}
		return cnt;	
	}
	
	public boolean emailAuth(String uri) throws TimeoutException {
		
		/*uri 가 null 또는 값이 없을 경우*/
		if(uri==null||uri.isEmpty()) {
			throw new NullPointerException();
		}
		
		/*해당 uri가 auth 테이블에 있는지 확인*/
		MemberAuthDto dto = memberAuthDao.getMemberAuth(uri);
		String dtoUri = dto.getAuthUri();
		
		if(dtoUri ==null || dtoUri.isEmpty()) {
			throw new IllegalArgumentException();
		}

		int memberSeq = dto.getMemberSeq();
		long expireDtm = dto.getExpireDtm();
		
		log.info("getMemberSeq : " + memberSeq + ", getAuthYn : " + dto.getAuthYn()+ ", getExpireDtm : " + expireDtm);
		
		long now = Calendar.getInstance().getTimeInMillis(); // long 
		boolean result = now < expireDtm;
		
		log.info("resutl : " + result);
		
		/*시간 초과*/
		if(!result) {
			throw new TimeoutException();
		}
		
		/*member ,memberAuth 테이블 yn 처리*/
		memberDao.updateMemberYN(memberSeq);
		memberAuthDao.updateMemberAuthYN(uri);
		
		return result;
	}
}
