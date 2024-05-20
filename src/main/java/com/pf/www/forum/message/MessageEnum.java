package com.pf.www.forum.message;

/*Enum 은 bean 등록이 필요 없다(싱글톤이라서 런타임 되면 JVM에 자동으로 올라간다)
 * 그래서 선언 필요 없고, 객체 생성 안해도 사용 가능 */
public enum MessageEnum {
	SUCCESS("0000", "Request Success"),
	EXISTS_LOGIN_NM("0101", "이미 사용중인 아이디입니다. 다른 아이디를 사용해주세요."),
	FAIL_SEND_EMAIL("0110", "이메일 발송에 실패하였습니다."),
	EXISTS_EMAIL_ADDR("0114", "이미 사용중인 이메일입니다. 다른 이메일을 사용해주세요."),
	USER_PASSWD_WRONG("0121", "입력하신 아이디와 비밀번호를 확인할 수 없습니다. 아이디와 비밀번호를 다시 입력해주세요."),
	USER_NOT_FOUND("0202", "존재하지 않는 사용자 입니다."),
	INVALID_ID_OR_PASSWORD("0203", "아이디 또는 비밀번호가 일치하지 않습니다. 다시 입력해 주세요."),
	PAGEING_ERROR("9999","잘못된 페이지 접근입니다.");
	
	private String code;
	private String description;

	MessageEnum(String code, String description) {
		this.code = code;
		this.description = description;
	}
	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return "MessageEnum{" + "Code='" + code + '\'' + ", Description='"
			+ description + '\'' + '}';
	}
}
/*
 * // usage MessageEnum.SUCCESS.getCode(); 
 * 	MessageEnum.SUCCESS.getDescription();
 */