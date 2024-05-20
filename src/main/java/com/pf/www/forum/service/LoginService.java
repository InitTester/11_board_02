package com.pf.www.forum.service;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;

import com.pf.www.forum.dao.MemberDao;
import com.pf.www.forum.dto.MemberDto;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class LoginService {
	private final static Logger log = LoggerFactory.getLogger(LoginService.class);
	
	private MemberDao memberDao;
	
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	public MemberDto login(HashMap<String, String> params) {
		
		/*사용자 찾기
		 * 입력된 값 기준으로 사용자가 있는지 확인 */	
		try {

			log.info("params"+ params.get("email"));
			MemberDto memberDto = memberDao.getMemberInfo(params.get("email"));
			
			/* 비밀번호 비교 
			 * passwd : 사용자가 입력한 값
			 * dbPasswd : 암호화해서 저장된 값*/
			String passwd = params.get("passwd");
			String dbPasswd = memberDto.getPasswd();
			
			/* 사용자가 입력한 값을 암호화 -> 사용자가 입력한 비밀번호와 비교 
			 * 비밀번호가 일치하면 로그인 user정보 넘기고 그렇지 않으면 null 값 넘긴다.*/
			BCrypt.Result result = BCrypt.verifyer().verify(passwd.toCharArray(),dbPasswd);
			
			return result.verified ? memberDto : null ;
			
		} catch (EmptyResultDataAccessException e) {
			// TODO Auto-generated catch block
			throw new EmptyResultDataAccessException("존재하지 않는 사용자 입니다.",0202);
		}
	}
}
