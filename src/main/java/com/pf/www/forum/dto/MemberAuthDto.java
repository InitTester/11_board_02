package com.pf.www.forum.dto;

public class MemberAuthDto {
	private int authSeq;
	private int memberSeq;
	private String authNum;
	private String authUri;
	private String regDtm;
	/* 타입이 long인 이유는 bigint 타입의 시간데이터는 자바에서 일반적으로 사용하며, 
	 * 데이터 유지 보수성과 코드 일관성을 유지하기 위한것 */	
	private long expireDtm; // 시간
	private String authYn;
	
	public int getAuthSeq() {
		return authSeq;
	}
	public void setAuthSeq(int authSeq) {
		this.authSeq = authSeq;
	}
	public int getMemberSeq() {
		return memberSeq;
	}
	public void setMemberSeq(int memberSeq) {
		this.memberSeq = memberSeq;
	}
	public String getAuthNum() {
		return authNum;
	}
	public void setAuthNum(String authNum) {
		this.authNum = authNum;
	}
	public String getAuthUri() {
		return authUri;
	}
	public void setAuthUri(String authUri) {
		this.authUri = authUri;
	}
	public String getRegDtm() {
		return regDtm;
	}
	public void setRegDtm(String regDtm) {
		this.regDtm = regDtm;
	}
	public long getExpireDtm() {
		return expireDtm;
	}
	public void setExpireDtm(long expireDtm) {
		this.expireDtm = expireDtm;
	}
	public String getAuthYn() {
		return authYn;
	}
	public void setAuthYn(String authYn) {
		this.authYn = authYn;
	}
}
