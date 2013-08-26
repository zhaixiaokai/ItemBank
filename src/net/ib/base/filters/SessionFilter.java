package net.ib.base.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;


public class SessionFilter implements Filter {
	private static final Logger logger = Logger.getLogger(SessionFilter.class);
	public static String exclude_endsWith = null;
	public static String exclude_indexOf = null;

	public void init(FilterConfig parm) throws ServletException {
		exclude_endsWith = parm.getInitParameter("exclude-endsWith");
		exclude_indexOf = parm.getInitParameter("exclude-indexOf");
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();

		String path = req.getServletPath();
		if (!path.matches(exclude_indexOf) && !path.matches(exclude_endsWith)) {
			if (session.getAttribute("session_data") == null) {
				if (!res.isCommitted()) {
					res.sendRedirect("/ItemBank/login/HomePage.jsp");
					return;
				}
			}
		}

		HttpServletResponseWrapper wrappedResponse = new HttpServletResponseWrapper(
				res) {
			@Override
			public String encodeRedirectUrl(String url) {
				return url;
			}

			@Override
			public String encodeRedirectURL(String url) {
				return url;
			}

			@Override
			public String encodeUrl(String url) {
				return url;
			}

			@Override
			public String encodeURL(String url) {
				return url;
			}
		};

		chain.doFilter(request, wrappedResponse);
	}

	public void destroy() {
		// TODO: Add your code here
	}

}