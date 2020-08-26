package member.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.*;

import java.sql.Connection;

import member.model.dao.MemberDAO;
import member.model.vo.Member;

public class MemberService {

	
	public Member loginMember(Member m) {
		
		//커넥션 연결
		Connection conn = getConnection();
		
		//DAO랑 연결 
		Member loginUser = new MemberDAO().loginMember(m, conn);
		close(conn);
		
		return loginUser;
		//loginUser 받아와서 좌측에 놓고 임포트 클로즈, 리턴 //loginUser 하기 
		
	}
	
	public int insertMember(Member member) {
		Connection conn = getConnection();
		
		int result = new MemberDAO().insertMemeber(conn, member);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		return result;
		
	}

	public int idCheck(String userId) {
		Connection conn = getConnection();
		
		int result = new MemberDAO().idCheck(conn, userId);
		close(conn);
		return result;
	}

	public Member selectMember(String loginUserId) {
		Connection conn = getConnection();
		
		Member member = new MemberDAO().selectMember(conn, loginUserId);
		close(conn);
		
		
		return member;
	}

	public int updateMember(Member member) {
		Connection conn = getConnection();
		
		int result = new MemberDAO().updateMember(conn, member);

		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}

	public int nickCheck(String userNick) {
		Connection conn = getConnection();
		
		int result = new MemberDAO().nickCheck(conn, userNick);
		close(conn);
		return result;
	}
}
