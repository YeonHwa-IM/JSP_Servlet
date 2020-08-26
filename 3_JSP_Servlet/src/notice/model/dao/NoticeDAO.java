package notice.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import notice.model.vo.Notice;

public class NoticeDAO {
	private Properties prop = new Properties();
	
	public NoticeDAO() {
		String fileName = NoticeDAO.class.getResource("/sql/notice/notice-query.properties").getPath();
		
		try {
			prop.load(new FileReader(fileName));
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Notice> selectList(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Notice> list = null;
		
		String query = prop.getProperty("selectList");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
		
			list = new ArrayList<Notice>();
			Notice no = null;
			
			while(rset.next()) {
				int nNo = rset.getInt("nNO");
				String nTitle = rset.getString("nTitle");
				String nContent = rset.getString("nContent");
				String nWriter = rset.getString("nWriter");
				int nCount = rset.getInt("nCount");
				Date nDate = rset.getDate("nDate");
				
				no = new Notice(nNo, nTitle, nContent, nWriter, nCount, nDate);
				list.add(no);
				
//선생님방법		while(rset.next()) {
//				Notice no = new Notice(rset.getInt("nNO"),
//										rset.getString("nTitle"),
//									rset.getString("nContent"),
//									rset.getString("nWriter"),
//									rset.getInt("nCount"),
//									rset.getDate("nDate"));
//									list.add(no);	
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		return list;
	}

	public int insertNotice(Connection conn, Notice n) {
		PreparedStatement pstmt = null;
		int result=0;
		
		String query = prop.getProperty("insertNotice");
		
		try {
		
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, n.getnTitle());
			pstmt.setString(2, n.getnContent());
			pstmt.setString(3, n.getnWriter());
			pstmt.setDate(4, n.getnDate());
		
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public Notice detailNotice(Connection conn, String nNO) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Notice notice =null;
		
		
		String query = prop.getProperty("detailNotice");
		//detailNotice=select * from notice where NNO = ?
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(nNO));
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				int nNo = rset.getInt("nNO");
				String nTitle = rset.getString("nTitle");
				String nContent = rset.getString("nContent");
				String nWriter = rset.getString("nWriter");
				int nCount = rset.getInt("nCount");
				Date nDate = rset.getDate("nDate");
				
			notice = new Notice(nNo, nTitle, nContent, nWriter, nCount, nDate);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
	
		return notice;
	}

	public int updateCount(Connection conn, String nNO) {
		PreparedStatement pstmt = null;
		int count =0;
		
		String query = prop.getProperty("noticeCount");
		
		try {
		
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(nNO));
			
			count = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return count;
	}

	public int updateNotice(Connection conn, Notice n) {
		//쿼리부터 작성해보기
		//update notice set ntitle=? ncontent=? ndate=? where nno=?
		//위치홀더가 있으니까 PreparedStatement
		//update니까 int result
		PreparedStatement pstmt = null;
		int result = 0;
	
		String query = prop.getProperty("updateNotice");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, n.getnTitle());
			pstmt.setString(2, n.getnContent());
			pstmt.setDate(3, n.getnDate());
			pstmt.setInt(4, n.getnNO());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}

	public int deleteNotice(Connection conn, int nno) {
		//쿼리 먼저  update notice set status='N' where nno=?
		//위치홀더가 있으니 PreparedStatement
		//update니까 int result
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("deleteNotice");
		
		try {
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, nno);
			
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}

	
}
