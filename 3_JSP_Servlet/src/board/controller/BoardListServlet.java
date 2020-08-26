package board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.Board;
import board.model.vo.PageInfo;

/**
 * Servlet implementation class BoardListServlet
 */
@WebServlet("/list.bo")
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//페이징 처리할 부분 작성할것이다. 게시글 수를 알아야
				// 1. 게시글의 총 개수
				// 2. 각 페이지에 따른 (1,2....) 게시글 목록
		
		BoardService service = new BoardService(); // 두 개의 서비스를 호출하기 때문에 참조변수로 호출
		//new를 한번만쓰는 방법으로 
		
		//게시글 총개수
		int listCount = service.getListCount();
		System.out.println(listCount);
		//페이징 처리 시작
		
		/* 페이징 처리 구조
			ㅡㅡㅡㅡㅡㅡ게시글1ㅡㅡㅡㅡㅡㅡ
			ㅡㅡㅡㅡㅡㅡ게시글2ㅡㅡㅡㅡㅡㅡ
			ㅡㅡㅡㅡㅡㅡ게시글3ㅡㅡㅡㅡㅡㅡ  boardLimit
			ㅡㅡㅡㅡㅡㅡ게시글4ㅡㅡㅡㅡㅡㅡ
			ㅡㅡㅡㅡㅡㅡ게시글5ㅡㅡㅡㅡㅡㅡ
		currentPage
		< 1 [2] 3 4 5 6 7 8 9 10 > 다음버튼을 누르면
		startPage			  endPage
		
		(11 12 13 14 15 16 17 18 19 20
					pageLimit
		....
									41p)
									maxPage
		*이름 잘 외워두기
		- 현재 보고 있는 페이지 currentPage
		- 한페이지 안에서 보여지는 페이지 수 boardLimit (게시글1~5)
		-  pageLimit 페이징된 갯수(10페이지 , 20페이지, 5페이지 등)
		- 현재 페이지에서 보여지는 페이지의 시작, startPage =1, 끝나는 페이지를 endPage=2, 마지막의 페이지는 maxPage=41
		
		*/
		
		int currentPage; 		//현재 페이지
		int pageLimit = 10; 	//한페이지에 표시될 페이징 수
		int maxPage;	 		// 전체 페이지 중 마지막 페이지
		int startPage;	 		// 페이징 된 페이지 중 시작 페이지
		int endPage;	 		// 페이징 된 페이지 중 마지막 페이지
		int boardLimit = 10;  	// 한 페이지에 보일 게시글 수
	
		currentPage = 1; // 현재페이지는 url에 보여줘야함 currentPage= 확인해서 변경되도록해야함
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} // 현재 페이지가 널이 아니면= 1이든지 값이 있으면  현재 페이지를 넣어줘라.
		
		//* 녹음
		maxPage = (int)((double)listCount / boardLimit + 0.9);
		
		//currentPage 와 pageLimit를 이용해서  startPage 만들기
		//startPage = (int)((double)currentPage / pageLimit +0.9);//=1
		startPage = (((int)((double)currentPage / pageLimit +0.9)) -1) * pageLimit +1;
					
		endPage = pageLimit + startPage -1;
		
		if(maxPage < endPage) {
			endPage = maxPage;
		}
		
		PageInfo pi = new PageInfo(currentPage, listCount, pageLimit, maxPage, startPage, endPage, boardLimit);
		
		ArrayList<Board> list = service.selectList(currentPage, boardLimit);
		
		String page = null;
		if(list != null) {
			page= "views/board/boardListView.jsp";
			request.setAttribute("list", list);
			request.setAttribute("pi", pi);
		}else {
			page= "views/common/errorPage.jsp";
			request.setAttribute("msg", "게시판 조회에 실패하였습니다.");
		}
		RequestDispatcher view = request.getRequestDispatcher(page);
		view.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
