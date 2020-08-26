package notice.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import notice.model.dao.NoticeDAO;
import notice.model.vo.Notice;

public class NoticeService {

	public ArrayList<Notice> selectList() {
		Connection conn = getConnection();
		ArrayList<Notice> list = new NoticeDAO().selectList(conn);
		close(conn);
		
			
		
		return list;
	}

	public int insertNotice(Notice n) {
		Connection conn = getConnection();
		int result = new NoticeDAO().insertNotice(conn, n);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
				
		
		return result;
	}

	public Notice detailNotice(String nNO) {
		Connection conn = getConnection();
		int count = new NoticeDAO().updateCount(conn, nNO); //리절트가 0보다 크면 dao보다 크다...
		Notice notice = null;
		
		if(count >0) {
			notice = new NoticeDAO().detailNotice(conn, nNO);
			if(notice != null) {
				commit(conn);
			}
		} else {
			rollback(conn);
		}
		close(conn);
		
			
		return notice;
	}

	public int updateNotice(Notice n) {
		Connection conn = getConnection();
		int result = new NoticeDAO().updateNotice(conn, n);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
				
		
		return result;
	}

	public int deleteNotice(int nno) {
		Connection conn = getConnection();
		
		int result = new NoticeDAO().deleteNotice(conn, nno);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}

	
	
}
