package com.twinkle.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.twinkle.domain.User;

public class ResourcesInterceptor extends HandlerInterceptorAdapter {
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response,
			Object handler)throws Exception{
		
		User user = (User)request.getSession().getAttribute("USER_SESSION");
		String uri = request.getRequestURI();
		
		if(user!=null){
			return true;
		}
		if(uri.indexOf("login")>=0){
			return true;
		}
		request.setAttribute("msg", "您还没有登录，请先进行登录");
		request.getRequestDispatcher("/admin/login.jsp").forward(request, response);
		
		return false;
		
	}
}
