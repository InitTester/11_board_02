package com.pf.www.forum.notice.message;

public enum NoticeMessageEnum {
	SUCCESS("0000", "게시글 작성이 완료 되었습니다"),
	MODIFY_SUCCESS("0001", "게시글 수정이 완료 되었습니다"),
	DELETE_SUCCESS("0001", "게시글 삭제가 완료 되었습니다"),
	FAIL("9999", "게시글 작성이 실패 되었습니다"),
	DELETE_FAIL("9999", "게시글 삭제를 실패 했습니다"),
	MODIFY_FAIL("9997", "게시글 수정이 실패 되었습니다"),
	USER_SESSION_FAIL("9998", "게시글 작성은 로그인 후 가능합니다.");
	
	private String code;
	private String description;
	
	private NoticeMessageEnum(String code, String description) {
		// TODO Auto-generated constructor stub
		this.code = code;
		this.description = description;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
