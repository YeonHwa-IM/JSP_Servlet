package board.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import board.model.dao.BoardDAO;
import board.model.vo.Attachment;
import board.model.vo.Board;
import board.model.vo.Reply;

public class BoardService {

	public int getListCount() {
		Connection conn = getConnection();
		
		int result = new BoardDAO().getListCount(conn);
		close(conn);
		
		return result;
	}

	public ArrayList<Board> selectList(int currentPage, int boardLimit) {
		Connection conn = getConnection();
		
		ArrayList<Board> list = new BoardDAO().selectList(conn, currentPage, boardLimit);
		
		close(conn);
		
		
		return list;
	}

	public ArrayList selectTList(int i) { //제네릭 빼는거 맞음.
		Connection conn = getConnection();
		ArrayList list = null;
		BoardDAO dao = new BoardDAO(); // 받아온 매개변수 i에따라 두번 DAO에 다녀올거라서
		
		if(i == 1) {
			list = dao.selectBList(conn); //1. 사진 '게시판' 리스트 정보 불러오기
		}else {
			list = dao.selectFList(conn); //2. '사진' 리스트 정보 불러오기
		}
		
		close(conn);
		
		
		return list;
	}

	public int insertThumbnail(Board b, ArrayList<Attachment> fileList) {
		Connection conn= getConnection();
		
		BoardDAO dao = new BoardDAO();
		
		int result1 = dao.insertThBoard(conn, b);
		int result2 = dao.insertAttachment(conn, fileList);
		
		if(result1 > 0 &&  result2 > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		
		
		return result1; //리턴을 result1만 할경우 result2가 잘 안되어도 성공했다고 뜰 수 있음.
	}

	public ArrayList<Attachment> selectThumbnail(int bid) {
		Connection conn = getConnection();
		ArrayList<Attachment> list = new BoardDAO().selectThumbnail(conn, bid);
		
		close(conn);
		
		return list;
	}
	
	public int insertBoard(Board b) {
		Connection conn = getConnection();
		int result = new BoardDAO().insertBoard(conn, b);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}

	public Board selectBoard(int bid) {
		Connection conn = getConnection();
		
		BoardDAO dao = new BoardDAO(); //BoardDAO 두개를 호출해야하기 때문에 참조 변수로 선언
		
		
		int result = dao.updateCount(conn, bid);
		//게시판 상세보기 시 조회수가 증가해야하기 때문에 게시판 상세보기 서비스에는 조회수 증가하는 기능도 구현해야함
		
		Board board = null;
		
		if(result > 0) {
			board = dao.selectBoard(conn, bid);
			if(board != null) {
				commit(conn);
			}else {
				rollback(conn);
			}
		}else {
			rollback(conn);
		}
		
		close(conn);
		return board;
	}

	public ArrayList<Reply> selectReplyList(int bid) {
		Connection conn = getConnection();
		ArrayList<Reply> list = new BoardDAO().selectReplyList(conn, bid);
		
		close(conn);
		return list;
	}

	public ArrayList<Reply> insertReply(Reply r) {
		Connection conn = getConnection();
		BoardDAO bDAO = new BoardDAO(); //서비스에서 두개 로 나누어 질지언정 서블릿에서는 하나로 보내줘야함.
		
		int result = bDAO.insertReply(conn, r);
		ArrayList<Reply> rList = null;
		
		if(result> 0) {
			commit(conn);
			rList = bDAO.selectReplyList(conn, r.getRefBid());
		}else {
			rollback(conn);
		}
		close(conn);
		
		return rList;
	}



}
