package com.pf.www.forum.filter;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter(dispatcherTypes = {DispatcherType.REQUEST }
					, servletNames = { "pf" })
public class LoginFilter extends HttpFilter implements Filter {
	private final static Logger log = LoggerFactory.getLogger(LoginFilter.class);
       
    /**
     * @see HttpFilter#HttpFilter()
     */
	/* 생성자 */
    public LoginFilter() {
        super();
        log.info("  LoginFilter 생성자  ");
        // TODO Auto-generated constructor stub
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
	/* Filter 작동할 때 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
	    String uri = req.getRequestURI();
	    
	    log.info("uri : " + uri);
	    
	    
	    /*uri를 통해 특정 페이지 처리 */
		/*
		 * if(StringUtils.pathEquals(uri, "/05/mainPage.do")) {
		 * 
		 * } if(uri.contains("/05/mainPage.do")) {
		 * 
		 * } if(uri.indexOf("/05/mainPage.do") > 1) {
		 */
	    if(uri.contains("/mainPage.do")) {
	        HttpSession session = req.getSession();
	        if(ObjectUtils.isEmpty(session.getAttribute("memberId"))) {
				/* sendRedirect 할때 html도 가능하고 이미지만 보여주기도 가능, response는 get방식 */
	            resp.sendRedirect(req.getContextPath() + "/loginPage.do");
	            return ;
	        }
	    }

		// pass the request along the filter chain
		/*
		 * 이부분은 servletfilter에서 다음 필터를 요청을 전달하거나 
		 * 필터 체인의 끝에 도달하여 실제 서블릿이나 컨트롤러에 요청을 전달하는 역할을 한다
		 */
	    chain.doFilter(request, response);	    
	}
	
	
	/*
	 * 번외) 여러 페이지 필터링
	 * /mainPage.do, /develop.do, /support.do, /sales.do, /fresh.do, /stage.do, /prod.do, /qa.do
	 *
	 * 필터링 할 페이지들을 배열을 이용한다
	 * Arrays.asList() 는 배열을 List로 변환
	 * contains(uri.replace( , ))는 괄호앞에 있는걸 삭제 하고 뒤에 조건과 같은지 확인 하기 위한 부분
	 *
	 * private final String[] LOGIN_REQUIRED_URI = { "/mainPage.do", //보통 무슨 페이지인지
	 * 주석 달아줌 "/develop.do", "/support.do", "/sales.do", "/fresh.do", "/stage.do",
	 * "/prod.do", "/qa.do" };
	 * 
	 * public void doFilter(ServletRequest request, ServletResponse response,
	 * FilterChain chain) throws IOException, ServletException { HttpServletRequest
	 * req = (HttpServletRequest) request; HttpServletResponse resp =
	 * (HttpServletResponse) response; String uri = req.getRequestURI();
	 * System.out.println("----------------uri : " + uri);
	 * 
	 * if(Arrays.asList(LOGIN_REQUIRED_URI).contains(uri.replace("/05", ""))) {
	 * HttpSession session = req.getSession();
	 * if(ObjectUtils.isEmpty(session.getAttribute("memberId"))) {
	 * resp.sendRedirect(req.getContextPath() + "/loginPage.do"); return ; } }
	 * chain.doFilter(request, response); }
	 */
	
	
	
	
	
	

	/**
	 * @see Filter#init(FilterConfig)
	 */
	/* 필터 등록시 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		log.info("   LoginFilter init   ");
	}
}
