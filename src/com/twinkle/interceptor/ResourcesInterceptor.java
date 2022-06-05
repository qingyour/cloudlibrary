package com.twinkle.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.twinkle.domain.User;

public class ResourcesInterceptor extends HandlerInterceptorAdapter {
	
	private List<String> ignoreUrl;
	
	public ResourcesInterceptor(List<String> ignoreUrl){
		this.ignoreUrl = ignoreUrl;
	}
	
	
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response,Object handler)throws Exception{
		//获取请求路径
		String uri = request.getRequestURI();
		System.out.println("当前用户请求的路劲"+uri+"=================");
		//对用户登录的相关请求，放行
		if(uri.indexOf("login")>=0){
			return true;
		}
		//获取当前登录用户
		User user = (User)request.getSession().getAttribute("USER_SESSION");
//		String uri = request.getRequestURI();
		//如果已经登录
		if(user!=null){
			//如果是管理员，放行
			if("ADMIN".equals(user.getRole())){
				return true;
			}
			else if(!"ADMIN".equals(user.getRole())){
				//如果是普通用户
				for(String url : ignoreUrl){
					//并且访问的资源不是管理员权限的资源，放行
					System.out.println("当前url:"+url);
					System.out.println("普通用户查询到方法了吗："+uri.indexOf(url));
					if(uri.indexOf(url)>=0){
						System.out.println("普通用户返回了真");
						return true;
					}
				}
			}
//			return true;
		}
//		if(uri.indexOf("login")>=0){
//			return true;
//		}
		//如果是其他情况直接回到登录页面
		System.out.println("=================登录失败");
		request.setAttribute("msg", "您还没有登录，请先进行登录");
		request.getRequestDispatcher("/admin/login.jsp").forward(request, response);
		
		return false;	
	}
}
