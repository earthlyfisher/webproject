package com.wyp.module.manager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class SessionInterceptor implements HandlerInterceptor {

	private static final String ADMINSESSION="currentUser";
	
	private static final String DEFAULT_PAGE="/login.jsp";
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		Object sessionObj = request.getSession().getAttribute(ADMINSESSION);
		if (sessionObj != null) {
			return true;
		}
		String redirectPath = request.getContextPath() + DEFAULT_PAGE;
		response.sendRedirect(redirectPath);
		return false;
	}
}
