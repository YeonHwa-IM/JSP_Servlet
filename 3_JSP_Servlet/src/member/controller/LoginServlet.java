package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(urlPatterns = "/login.me", name="LoginServlet")
//원래 urlPatterns = 타입이 적어야함. 
//name =  으로서블릿 이름도 구분지었어야함.
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
		System.out.println(userId + ", " +userPwd);
		
		Member m = new Member(userId, userPwd);
		
		//서비스로 이어줘야함
		
		Member loginUser = new MemberService().loginMember(m);
		//**리퀘스트는 한번만 요청 가능하다!!! 세션을 만들어서 로그인 정보를 넣을것.
		if(loginUser != null) {
			/*
			 * request.setAttribute("loginUser", loginUser); RequestDispatcher view =
			 * request.getRequestDispatcher("index.jsp"); view.forward(request, response);
			 */
			//세션영역에 로그인 정보 담음 
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", loginUser);
			session.setMaxInactiveInterval(600); 
			//10분동안 session 유지
			
		//response.sendRedirect("index.jsp");
		response.sendRedirect(request.getContextPath());
			//** 왜 그런지 이해해야함.
			//데이터를 보낼게 있는지 없는지 차이점 때문
			//보내줄게 있는데도 왜 sendRedirect를 썼는지?
			//sendRedirect는 내가 아무리 리퀘스트 영역에 보낼것을 담아도 
			//넘어가면 결국 새로운 영역(리퀘스트 개체)를 만들기 때문에 , 기존에 담은게 의미가 없다.
			// 데이터를 담은 영역에 세션이고, 리퀘스트 영역과 세션영역은 다름.
			// 만약에 sendRedirect가 세션영역은 상관없이 리퀘스트 영역만 무효과 시키기 때문에 
			// 세션에 담긴 정보는 살아있음. 그래서 sendRedirect를 사용해도 아무 문제가 없음.
			
		}else {
			request.setAttribute("msg", "로그인 실패");
			RequestDispatcher view = request.getRequestDispatcher("views/common/errorPage.jsp");
			view.forward(request, response);
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
