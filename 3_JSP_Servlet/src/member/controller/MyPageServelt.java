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
 * Servlet implementation class MyPageServelt
 */
@WebServlet("/myPage.me")
public class MyPageServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyPageServelt() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//2. 디비에서 받아와 세션에 넣은  내 정보가 올라와 있기때문에 바로 받아와도 되고, 
		//세션에서 가져왔을때의 문제, 정보 수정했을때 내정보페이지에서 확인을 해야하는데,
		// 업데이트해서 디비로 넘어가고, 다시 리절트1을 받아와서 , 화면만 바꿔줄건데
		//내정보 바뀐건 디비이지, 세션은 바뀐게 아니라서 반영이 안되어 있음.
		//수정을 했을 경우 디비에서 가져온걸 세션에 한번더 저장해야함.
		
		//1. 디비에서 다시 가져와도 됨.
		// 내정보 입력하면서 아이디를 한번더 입력하지 않을거임.
		//세션에 저장된 아이디는 가져오기
		HttpSession session = request.getSession();
		Member sessionMember =(Member)session.getAttribute("loginUser");
		//로그인 유저 형변환
		String loginUserId = sessionMember.getUserId();
		
		Member member = new MemberService().selectMember(loginUserId);
		
		String page = null;
		if(member != null) {
			page ="views/member/memberView.jsp";
			request.setAttribute("member", member);
		} else {
			page ="views/common/errorPage.jsp";
			request.setAttribute("msg", "회원조회에 실패했습니다.");
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
