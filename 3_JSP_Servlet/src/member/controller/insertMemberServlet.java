package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class insertMemberServlet
 */
@WebServlet(urlPatterns = "/insert.me", name = "insertMemberServlet")
//원래 urlPatterns = 타입이 적어야함. 
//name =  으로서블릿 이름도 구분지었어야함.
public class insertMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public insertMemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setCharacterEncoding("UTF-8");
		
		String joinUserId = request.getParameter("joinUserId");
		String joinUserPwd = request.getParameter("joinUserPwd");
		/* String joinUserPwd2 = request.getParameter("joinUserPwd2"); */
		String userName = request.getParameter("userName");
		String nickName = request.getParameter("nickName");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String[] interestArr = request.getParameterValues("interest");
		String interest = String.join(", ", interestArr);
		
		Member member = new Member(joinUserId, joinUserPwd, 
				userName,  nickName, phone, email, address, 
					interest, null, null, null);
		
		//반환값 예측해서 int result 적기
		int result = new MemberService().insertMember(member);
		
		String page = "";
		String msg = "";
		
		
		if(result > 0) {
			page= "/";
			/* page= request.getContextPath(); */
			msg= "회원가입에 성공했습니다.";
			
			//보낼게 없지만 메세지 만들어서 보내보기
			/*
			 * request.setAttribute("msg", "회원가입에 성공했습니다."); RequestDispatcher view =
			 * request.getRequestDispatcher(request.getContextPath()); 
			 * view.forward(request, response);
			 */
		} else {
			page= "views/common/errorPage.jsp";
			/* page= request.getContextPath(); */
			msg= "회원가입에 실패했습니다..";
			
			/*
			 * request.setAttribute("msg", "회원가입에 실패했습니다."); RequestDispatcher view =
			 * request.getRequestDispatcher("view/common/errorPage.jsp");
			 * view.forward(request, response);
			 */
		}
		request.setAttribute("msg", msg);
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
