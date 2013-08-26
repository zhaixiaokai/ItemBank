/*
 * Created on 2012-7-20
 *
 * LoginCheckFilter.java
 *
 * Copyright (C) 2012 by Company of www.edatongtu.com (BeiJing) Limited.
 * All rights reserved. Company of www.edatongtu.com (BeiJing) Limited 
 * claims copyright in this computer program as an unpublished work. Claim of copyright 
 * does not imply waiver of other rights.
 *
 * NOTICE OF PROPRIETARY RIGHTS
 *
 * This program is a confidential trade secret and the property of 
 * Company of www.edatongtu.com (BeiJing) Limited. Use, examination, 
 * reproduction, disassembly, decompiling, transfer and/or disclosure to others of 
 * all or any part of this software program are strictly prohibited except by express 
 * written agreement with Company of www.edatongtu.com (BeiJing) Limited.
 */
/*
 * ---------------------------------------------------------------------------------
 * Modification History
 * Date               Author                     Version     Description
 * 2012-7-20       zhouyunbo                    1.0         New
 * ---------------------------------------------------------------------------------
 */
package net.ib.base.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCheckFilter implements Filter {
	
	protected FilterConfig filterConfig = null;    
    private String redirectURL = null;    
    private List<String> noCheckURLList = new ArrayList<String>();    
    private String sessionKey = null; 
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		noCheckURLList.clear();
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) servletRequest;    
        HttpServletResponse response = (HttpServletResponse) servletResponse;
    	
        HttpSession session = request.getSession(); 
        //濡傛灉鏈畾涔塁heckSessionKey锛屽垯涓嶅仛鐧诲綍妫�煡
        if(sessionKey == null){    
            filterChain.doFilter(request, response);
            return;    
        }
        //濡傛灉鏈櫥褰曪紝骞朵笖璁块棶椤甸潰鏂囦欢涓嶅湪蹇界暐妫�煡鐨勬枃浠跺垪琛ㄤ腑锛屽垯灏嗗鑸埌绯荤粺鎻愮ず椤�
        if(session.getAttribute(sessionKey) != null || checkRequestURIInNotFilterList(request)){ 
            filterChain.doFilter(servletRequest, servletResponse);
        }else{
            response.sendRedirect(request.getContextPath() + redirectURL);
            return;
        }
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		this.filterConfig = filterConfig;    
        redirectURL = filterConfig.getInitParameter("RedirectURL");    
        sessionKey = filterConfig.getInitParameter("CheckSessionKey");    
        String notCheckURLListStr = filterConfig.getInitParameter("NoCheckURLList");   
   
        if(notCheckURLListStr != null){    
        	StringTokenizer st = new StringTokenizer(notCheckURLListStr, ";");   
        	noCheckURLList.clear();    
            while(st.hasMoreTokens()){
                noCheckURLList.add(st.nextToken());
            }    
        }
	}

	private boolean checkRequestURIInNotFilterList(HttpServletRequest request){    
		String uri = request.getServletPath() + (request.getPathInfo() == null ? "" : request.getPathInfo());
		for(int i=0;i<noCheckURLList.size();i++){
			if(uri.startsWith(noCheckURLList.get(i))){
				return true;
			}
		}
		return false;
    } 
}
