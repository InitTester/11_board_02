package com.pf.www.forum.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.pf.www.forum.dto.MemberAuthDto;

public class MemberAuthDao extends JdbcTemplate {
	private final static Logger log = LoggerFactory.getLogger(MemberAuthDao.class);
	
	public MemberAuthDao() {
		// TODO Auto-generated constructor stub
	}
	
	public MemberAuthDao(DataSource ds) {
		super(ds);
	}
	
	public int addMemberAuth(MemberAuthDto authDto) {
		String sql = " INSERT INTO member_auth "
				+ " (member_seq, auth_num, auth_uri, reg_dtm, expire_dtm, auth_yn) "
				+ " VALUES(? , '', ? , DATE_FORMAT(now(),'%Y%m%d%H%i%s'), ?, 'N'); ";
		Object[] args = { authDto.getMemberSeq(), authDto.getAuthUri(), authDto.getExpireDtm()};
		
		return update(sql, args);
	}
	
	public int updateMemberAuthYN(String auth_uri) {
		String sql = " UPDATE member_auth " 
				+ " SET auth_yn = 'Y' "
				+ " WHERE auth_uri = ? "
				+ " AND IFNULL(auth_yn,'N') = 'N' ";
		Object[] args = { auth_uri};
		
		return update(sql, args);
	}
	
	public MemberAuthDto getMemberAuth(String uri) {
		String sql = " SELECT auth_seq, member_seq, auth_num, auth_uri, reg_dtm, expire_dtm, auth_yn "
				+ " FROM member_auth "
				+ " WHERE auth_uri = ? "
				+ " AND auth_yn = 'N'; ";
		
		Object[] args = {uri};
		
		return queryForObject(sql, new MemberAuthRowMapper(), args);
	}
		
	/* 1개의 row 값 selct */
	class MemberAuthRowMapper implements RowMapper<MemberAuthDto>{
		@Override
		public MemberAuthDto mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			MemberAuthDto dto = new MemberAuthDto();
			
			dto.setAuthSeq(rs.getInt("auth_seq"));
			dto.setMemberSeq(rs.getInt("member_seq"));
			dto.setAuthNum(rs.getString("auth_num"));
			dto.setAuthUri(rs.getString("auth_uri"));
			dto.setRegDtm(rs.getString("reg_dtm"));
			dto.setExpireDtm(rs.getLong("expire_dtm"));
			dto.setAuthYn(rs.getString("auth_yn"));
		
			return dto;
		}
	}
}
