package com.pf.www.forum.util;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.pf.www.forum.dto.EmailDto;

public class EmailUtil {
	private final static Logger log = LoggerFactory.getLogger(EmailUtil.class);
	
	// property 설정으로 받아보기
	private JavaMailSender mailSender;
	
	public void setMailSender(JavaMailSender mailsender) {
		this.mailSender = mailsender;
	}
	
	public String sendMail(EmailDto email) {
		return sendMail(email,false);
	}
	
	public String sendMail(EmailDto email, boolean isHtml) {
		
		log.info("getReceiver : " + email.getReceiver()+ ", getFrom : " + email.getFrom()+ ", getSubject : " + email.getSubject());
		
		/* try-catch 사용하는 이유는 MimeMessageHelper에서 필수적인 요소이고, 에러가 났을때 처리하기 위해서 */
		
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
			helper.setTo(email.getReceiver());
			helper.setFrom(email.getFrom());
			helper.setSubject(email.getSubject()); // 메일 제목은 생략 가능
			

			/* setText 오버로딩으로 이메일 본문에 대한 텍스트,html 형식을 지원한다
			 * setText(String text)
			 * setText(String text, boolean html)			 *  
			 *  */
			helper.setText(email.getText(),isHtml);
			mailSender.send(message);
			
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			log.info(e.getMessage());
			return "Error";
		}
		return "Sucess";
	}	
}
