package action.el.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.model.vo.Person;

/**
 * Servlet implementation class MyElServlet
 */
@WebServlet("/myEL.do")
public class MyElServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyElServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String name = request.getParameter("name");
		String gend = request.getParameter("gender");
		char gender = gend.charAt(0);
		int age = Integer.parseInt(request.getParameter("age"));
		String beverage = request.getParameter("beverage");
		
		String[] product = request.getParameterValues("product");
//		String product = String.join(", ", productArr);
		
		String book = request.getParameter("book");
		String movie = request.getParameter("movie");
		
		Person rosemary = new Person(name, gender, age);
		request.setAttribute("person", rosemary);
		request.setAttribute("beverage", beverage);
		request.setAttribute("year", 2020);
		request.setAttribute("product", product);
		
		//Session Scope
		HttpSession session = request.getSession();
		session.setAttribute("book", book);
		
		//Application Scope
		ServletContext application = request.getServletContext();
		application.setAttribute("movie", movie);
		
		request.setAttribute("beverage", "아메리카노");
		session.setAttribute("beverage", "핫식스");
		application.setAttribute("beverage", "물");
		
		request.getRequestDispatcher("/02_el/01_2_elEnd.jsp").forward(request, response);
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
