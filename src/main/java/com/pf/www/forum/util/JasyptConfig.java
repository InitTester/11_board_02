package com.pf.www.forum.util;

import java.io.*;
import java.util.Properties;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.slf4j.*;

public class JasyptConfig {
	private final static Logger log = LoggerFactory.getLogger(JasyptConfig.class);
	
	public static void main(String[] args) {
		
		/*저장할 properties*/
		Properties properties = new Properties();
		
		/*필요한 변수 선언*/
		String evnEmail = System.getenv("JASYPT_ENCRYPTION_EMAIL");
		String evnEmailPwd = System.getenv("JASYPT_ENCRYPTION_PWD");
		String evnKey = System.getenv("APP_ENCRYPTION_PASSWORD");
		
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();

		log.info("evnKey : "+ evnKey);
		log.info("evnEmail : "+ evnEmail);
		log.info("evnEmailPwd : "+ evnEmailPwd);
		
		/* 암호화에 쓰일 key 값 지정 */
		encryptor.setPassword(evnKey);

		String encryptEmail = encryptor.encrypt(evnEmail);
		String encryptEmailPwd = encryptor.encrypt(evnEmailPwd);
		
		log.info("encryptEmail : "+ encryptEmail);
		log.info("encryptEmailPwd : "+ encryptEmailPwd);
		
		log.info("encryptEmail -> decrypt : "+ encryptor.decrypt(encryptEmail));
		log.info("encryptEmailPwd -> decrypt : "+ encryptor.decrypt(encryptEmailPwd));	
		
		/* 변환한 encrypt 값을 properties 값을 지정하고 저장하기 
		 * encrypt 값에는 "ENC(변환값)"으로 지정해 주어야 한다.
		 * */
		
		properties.setProperty("app.username", "ENC("+encryptEmail+")");
		properties.setProperty("app.password", "ENC("+encryptEmailPwd+")");
		properties.setProperty("app.encryptKey", evnKey);		
		
		/*지정값 저장*/
		try {
			properties.store(new FileOutputStream("src/main/resources/application.properties"), null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			log.info(e.getMessage());
		}
	}
}
