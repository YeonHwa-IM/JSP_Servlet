package board.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import board.model.vo.Attachment;
import board.model.vo.Board;
import board.model.vo.Reply;

public class BoardDAO {

	private Properties prop = new Properties();
	
	public BoardDAO() {
		String fileName = BoardDAO.class.getResource("/sql/board/board-query.properties").getPath();
		
		try {
			prop.load(new FileReader(fileName));
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int getListCount(Connection conn) {
		//select count(*) form board where status='Y' and btype=1
		//1이번 일반 게시판 2면 사진게시판 등..
		Statement stmt = null;
		ResultSet rset = null;
		int result = 0;		
		
		String query = prop.getProperty("getListCount");
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query); 
			//값이 0 이어도 행을 가져오니까 카운트는 무조건 1개 
			
			if(rset.next()) {
				result = rset.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		return result;
	}

	public ArrayList<Board> selectList(Connection conn, int currentPage, int boardLimit) {
		// 1. 쿼리 넘나 어려운것 
		// 
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Board> list = null;
		
		String query = prop.getProperty("selectList");
		//selectList=SELECT * FROM BLIST WHERE RNUM BETWEEN ? AND ? AND BTYPE=1
		
		int startRow = (currentPage -1) * boardLimit +1;
		int endRow = startRow + boardLimit -1;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,  startRow);
			pstmt.setInt(2,  endRow);
			
			rset = pstmt.executeQuery();
			list = new ArrayList<Board>();
		
			while(rset.next()) {
				Board b = new Board(rset.getInt("bid"),
									rset.getInt("btype"),
									rset.getString("cname"),
									rset.getString("btitle"),
									rset.getString("bcontent"),
									rset.getString("nickname"),
									rset.getInt("bcount"),
									rset.getDate("create_date"),
									rset.getDate("modify_date"),
									rset.getString("status"));
				list.add(b);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public ArrayList selectBList(Connection conn) {
		//사진 '게시판' 리스트 정보 불러오기
		//select * from BLIST where btype =2 // 사진게시판이기때문에
		
		Statement stmt = null;
		ResultSet rset =null;
		ArrayList<Board> list = null;
		
		
		String query = prop.getProperty("selectBList");
		 try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			list = new ArrayList<Board>();
		 
			while(rset.next()) {
				list.add(new Board(rset.getInt("bid"),
									rset.getInt("btype"),
									rset.getString("cname"),
									rset.getString("btitle"),
									rset.getString("bcontent"),
									rset.getString("nickname"),
									rset.getInt("bcount"),
									rset.getDate("create_date"),
									rset.getDate("modify_date"),
									rset.getString("status")));
			}
		 } catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		return list;
	}

	public ArrayList selectFList(Connection conn) {
		//2. '사진' 리스트 정보 불러오기
		//쿼리? select * from attachment where file_level=0 and status='Y'
		
		Statement stmt = null; //위치홀더가 없으니..
		ResultSet rs = null;
		ArrayList<Attachment> list = null;
		
		String query = prop.getProperty("selectFList");
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
			list = new ArrayList<Attachment>();
			
			while(rs.next()) {
				list.add(new Attachment(rs.getInt("bid"), rs.getString("change_name")));
				//파일명이 바뀌어 있기 때문에 체인지 네임을 가져와야함.
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
		}
		
		return list;
	}
	public int insertAttachment(Connection conn, ArrayList<Attachment> fileList) { 
		//첨부파일 인서트, 어레이리스트로 들어왔기떄문에 포문을 돌려서 여러개 값을 넣어주어야함
		//쿼리 insert into attachment values(seq_fid.nextval, seq_bid.currval, ?, ?, ?, sysdate, ?, default, default)
				PreparedStatement pstmt = null;
				int result = 0;
				
				String query = prop.getProperty("insertAttachment");
				
				try {
					
					for(int i =0; i< fileList.size(); i++) {
						Attachment at = fileList.get(i); 
						pstmt = conn.prepareStatement(query); //포문을 얘부터 감싸서 돌려야함
						pstmt.setString(1, at.getOriginName());
						pstmt.setString(2, at.getChangeName());
						pstmt.setString(3, at.getFilePath());
						pstmt.setInt(4, at.getFileLevel());
						
						result += pstmt.executeUpdate();
					}
					
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					close(pstmt);
				}
				
				return result;
	}

	public int insertThBoard(Connection conn, Board b) { //일반 게시판 방식과 동일
		//쿼리: insert into board values(seq_bid.nextval, 2, 10, ?, ?, ?, default, sysdate, sysdate, default)
				PreparedStatement pstmt = null;
				int result = 0;
				
				String query = prop.getProperty("insertThBoard");
				
				try {
					pstmt = conn.prepareStatement(query);
					pstmt.setString(1, b.getbTitle());
					pstmt.setString(2, b.getbContent());
					pstmt.setString(3, b.getbWriter());
					
					result = pstmt.executeUpdate();
				
				} catch (SQLException e) {
					e.printStackTrace();
				} finally{
					close(pstmt);
				}
				return result;
		
	}

	public ArrayList<Attachment> selectThumbnail(Connection conn, int bid) {
		//select * from attachment where bid = ? and status = 'Y' order by fid
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Attachment> list = null;
		
		String query = prop.getProperty("selectThumbnail");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bid);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<Attachment>();
			
			while(rset.next()) {
				Attachment at = new Attachment();
				at.setfId(rset.getInt("fId"));
				at.setOriginName(rset.getString("origin_name"));
				at.setChangeName(rset.getString("chang_name"));
				at.setFilePath(rset.getString("file_path"));
				at.setUploadDate(rset.getDate("upload_date"));
				
				list.add(at);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public int insertBoard(Connection conn, Board b) {
		//쿼리 먼저 insert into board values(seq_bid.nextval, 1, ?, ?, ? ,?, default, sysdate, sysdate, default)
		PreparedStatement pstmt = null;
		//위치 홀더 쓰이니까 pstmt
		int result = 0;// 결과 값을 담을 변수 선언
		
		//프로퍼티에 적힌 쿼리를 불러와서 쿼리변수에 담는다.
		String query = prop.getProperty("insertBoard");
		
		try {
			pstmt = conn.prepareStatement(query);
			//커넥션 안에 있는 pstmt()를 통해서 쿼리문을 디비에 보낼 객체에 넣어준다.
			pstmt.setInt(1, Integer.parseInt(b.getCategory()));
			//pstmt로 set(보내다)할건데, 
			//1번 위치홀더에  보드 b에서 get(가져온), 카테고리 넘버를 보낸다.  
			pstmt.setString(2, b.getbTitle());
			pstmt.setString(3, b.getbContent());
			pstmt.setString(4, b.getbWriter());
			
			result = pstmt.executeUpdate();
			//디비 결과가 테이블의 내용만 변경되는 거기 때문에 
			//pstmt executeUpdate()로 받아온 값을 준비해 놓은 변수에 담아준다.
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int updateCount(Connection conn, int bid) {
		// update board set bcount= bcount+1 where bid =?
		PreparedStatement pstmt =null;
		int result = 0;
		
		String query = prop.getProperty("updateCount");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bid);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public Board selectBoard(Connection conn, int bid) {
		//쿼리가 너무 길어서 썜이 뷰로 만들어 주심
		//select * form bdetail where bid =?
		
		PreparedStatement pstmt =null;
		//위치홀더 = pstmt
		ResultSet rset = null;
		//select 하기 때문에 resultset에 담음
		Board board =null; 
		//받아온 결과는 보드에 담음
		
		String query = prop.getProperty("selectBoard");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bid);
			
			rset = pstmt.executeQuery();
			//select이용시 executeQuery!
			
			if(rset.next()) {
			//만약에 받아온 값에 다음이 있으면 값을 받아와서 보드에 담음
				board = new Board(rset.getInt("bid"),
								  rset.getInt("btype"),
								  rset.getString("cname"),
								  rset.getString("btitle"),
								  rset.getString("bcontent"),
								  rset.getString("nickName"),
								  rset.getInt("bcount"),
								  rset.getDate("create_date"),
								  rset.getDate("modify_date"),
								  rset.getString("status"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return board;
	}

	public ArrayList<Reply> selectReplyList(Connection conn, int bid) {
		//query = select *  from reply where ref_bid =?
		// 뷰 만들어서 쿼리 문 바뀜
		//select * from rlist where ref_bid = ?
		
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		ArrayList<Reply> list = null;
		
		String query = prop.getProperty("selectReplyList");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bid);
			rs = pstmt.executeQuery();
			
			list = new ArrayList<Reply>();
			
			while(rs.next()) {
				list.add(new Reply(rs.getInt("rid"),
								   rs.getString("rcontent"),
								   rs.getInt("ref_bid"),
								   rs.getString("nickname"),
								   rs.getDate("create_date"),
								   rs.getDate("modify_date"),
								   rs.getString("status")));
						
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return list;
	}

	public int insertReply(Connection conn, Reply r) {
		PreparedStatement pstmt = null;
		int result =0;
		String query = prop.getProperty("insertReply");
		
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, r.getrContent());
			pstmt.setInt(2, r.getRefBid());
			pstmt.setString(3, r.getrWriter());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(pstmt);
		}
			
		return result;
	}

}
