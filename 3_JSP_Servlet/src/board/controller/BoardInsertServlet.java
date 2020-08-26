package board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.model.service.BoardService;
import board.model.vo.Board;
import member.model.vo.Member;



/**
 * Servlet implementation class BoardInsertServlet
 */
@WebServlet("/insert.bo")
public class BoardInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setCharacterEncoding("UTF-8"); //post방식이라면!
		//글번호, 카테고리, 글제목, 작성자, 조회수, 작성일
		
		String category = request.getParameter("category");
		String title = request.getParameter("title");
		//String userId = ((Member)request.getSession().getAttribute("loginUser")).getUserId();
		String content = request.getParameter("content");
		//String date = request.getParameter("date");
		
		HttpSession session = request.getSession();
		Member loginUser = (Member)session.getAttribute("loginUser");
		String writer = loginUser.getUserId();
		
		Board b = new Board();
		b.setbContent(content);
		b.setbTitle(title);
		b.setCategory(category);
		b.setbWriter(writer);
		
		int result = new BoardService().insertBoard(b);
		
		if(result > 0) {
			response.sendRedirect("list.bo?currentPage=1");
		}else {
			request.setAttribute("mst", "게시판 작성에 실패하였습니다.");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
