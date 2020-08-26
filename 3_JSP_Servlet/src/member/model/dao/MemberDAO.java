package member.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import member.model.vo.Member;

public class MemberDAO {

	//1 생성자
	private Properties prop = new Properties();
	
	public MemberDAO() { //2 프로퍼티 연결
		String fileName = MemberDAO.class.getResource("/sql/member/member-query.properties").getPath();
		
		try {
			prop.load(new FileReader(fileName));
		
		
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	public Member loginMember(Member m, Connection conn) {
		//3  로그인에 대한 쿼리 짜기
		/*
		query : 
			select userId, userPwd (x) -> * (O)  내정보는 수정하기 때문에 다 가져오는게 좋음 
			from member
			where userId = ? and userPwd = ?
		*/
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member loginUser = null;
		
		String query = prop.getProperty("loginMember");
		
		//프로퍼티에 있는 쿼리 가져오기
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getUserPwd());
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				loginUser = new Member(
						rset.getString("USER_ID"),
						rset.getString("USER_PWD"),
						rset.getString("USER_name"),
						rset.getString("nickName"),
						rset.getString("phone"),
						rset.getString("email"),
						rset.getString("address"),
						rset.getString("interest"),
						rset.getDate("enroll_date"),
						rset.getDate("modify_date"),
						rset.getString("status"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return loginUser;

	}
	
	public int insertMemeber(Connection conn, Member member) {
		PreparedStatement pstmt =null;
		int result =0;
		
		String query = prop.getProperty("insertMember");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getUserId());
			pstmt.setString(2, member.getUserPwd());
			pstmt.setString(3, member.getUserName());
			pstmt.setString(4, member.getNickName());
			pstmt.setString(5, member.getPhone());
			pstmt.setString(6, member.getEmail());
			pstmt.setString(7, member.getAddress());
			pstmt.setString(8, member.getInterest());
		
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}
	public int idCheck(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result =0;
		
		//넣는 아이디에 대한 갯수를 셈
		String query = prop.getProperty("idCheck");
		
		 try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery();
			//결과값이 0~1개
			
			if(rset.next()) {
				result = rset.getInt(1);
			}
		 } catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return result;
		
	}
	public Member selectMember(Connection conn, String loginUserId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member member = null;
		
		String query = prop.getProperty("selectMember");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, loginUserId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				member = new Member(rset.getString("user_id"),
						rset.getString("user_pwd"),
						rset.getString("user_name"),
						rset.getString("nickName"),
						rset.getString("phone"),
						rset.getString("email"),
						rset.getString("address"),
						rset.getString("interest"),
						rset.getDate("enroll_date"),
						rset.getDate("modify_date"),
						rset.getString("status"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return member;
	}
	public int updateMember(Connection conn, Member member) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateMember");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getUserName());
			pstmt.setString(2, member.getNickName());
			pstmt.setString(3, member.getPhone());
			pstmt.setString(4, member.getEmail());
			pstmt.setString(5, member.getAddress());
			pstmt.setString(6, member.getInterest());
			pstmt.setString(7, member.getUserId());
		
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}
	public int nickCheck(Connection conn, String userNick) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result =0;
		
		//넣는 아이디에 대한 갯수를 셈
		String query = prop.getProperty("nickCheck");
		
		 try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userNick);
			
			rset = pstmt.executeQuery();
			//결과값이 0~1개
			
			if(rset.next()) {
				result = rset.getInt(1);
			}
		 } catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return result;
		
	}

}
