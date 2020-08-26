package common.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class CommonFilter
 */
//@WebFilter(filterName = "encoding", urlPatterns = { "/*" })
public class CommonFilter implements Filter {

    /**
     * Default constructor. 
     */
    public CommonFilter() {
        // 필터가 작동하는지 확인해보자! 
    	System.out.println("Commonfilter 작동");
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here : 여기에 코드를 넣어라!
		
		request.setCharacterEncoding("UTF-8"); //받는 인코딩 형태
		response.setContentType("text/html; charset=UTF-8"); //보내는 인코딩형태
		//여기까지가 필터끝임.

		// pass the request along the filter chain : 필터체인에 따라 요청을 전달한다.
		chain.doFilter(request, response);
		
		//여기까지가 끝! 
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
