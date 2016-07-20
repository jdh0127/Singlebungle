package spring;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;

public class MemberDao {
	private static JdbcTemplate jdbcTemplate;

	private RowMapper<Member> memRowMapper = new RowMapper<Member>() {
		
		@Override
		public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
			Member member = new Member(rs.getString("nick"),rs.getString("email"), rs.getString("name"),rs.getString("pwd"),rs.getString("birth"), rs.getString("phone"), rs.getString("loc"), rs.getInt("locCheck"), rs.getInt("point"), rs.getInt("admin"));
			member.setNick(rs.getString("nick"));
			return member;
		}
	};
	
	public MemberDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);

	}
	
	public List<Member> selectmember(){
		List<Member> result = jdbcTemplate.query("select * from member order by nick desc", memRowMapper);
		return result;
	}

	public Member selectByEmail(String email) {
		List<Member> results = jdbcTemplate.query("select * from member where email=?", memRowMapper, email);
				
		return results.isEmpty() ? null : results.get(0);
	}
	
	public  List<Member> selectall(String nick){		
		List<Member> result = jdbcTemplate.query("select * from member where nick=?", memRowMapper, nick);
		return result;
	}
	public int pwdcheck(String nick, String pwd){
		int result = 0;
		result = jdbcTemplate.queryForInt("select count(*) from member where nick = ? and pwd = ?" , nick, pwd);
		return result; 
	}

	public void insert(final Member member) {
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement("insert into member values(?, ?, ?, ?, ?, ?, ?, 0, 0, 0)");
				pstmt.setString(1, member.getNick());
				pstmt.setString(2, member.getEmail());
				pstmt.setString(3, member.getName());
				pstmt.setString(4, member.getPwd());
				pstmt.setString(5, member.getBirth());
				pstmt.setString(6, member.getPhone());
				pstmt.setString(7, member.getLoc());
				return pstmt;
			}
		});
	}
		
	public void update(Member member){
		jdbcTemplate.update("update member set name=?, pwd=?,birth=?, phone=?, loc=? where nick=?",member.getName(), member.getPwd(),member.getBirth(), member.getPhone(), member.getLoc(), member.getNick());
	}
	public void delete(String email){
		jdbcTemplate.update("delete from member where email=?", email);
	}
	public void deletemember(String nick){
		jdbcTemplate.update("delete from member where nick=?", nick);
	}
	
	public void locUpdate(Member member){
		jdbcTemplate.update("update member set locCheck=1 where nick=?", member.getNick());
	}
	
	public static int emailcheck(String email){
		int result = 0;
		result = jdbcTemplate.queryForInt("select count(*) from member where email = ?" , email);
		return result; 
	}

	public static int nickcheck(String nick){
		int result = 0;
		result = jdbcTemplate.queryForInt("select count(*) from member where nick = ?" , nick);
		return result; 
	}
	 
}
