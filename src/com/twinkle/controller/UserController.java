package com.twinkle.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.twinkle.domain.User;
import com.twinkle.service.UserService;
@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/login")
	public String getIndexPage(User user,HttpServletRequest request){
		try {
            User u=userService.login(user);
            /*
		                �û��˺ź������Ƿ��ѯ���û���Ϣ
		                �ǣ����û���Ϣ����Session������ת����̨��ҳ
		                ��Request���������ʾ��Ϣ����ת������¼ҳ��
             */
            if(u!=null){
                request.getSession().setAttribute("USER_SESSION",u);
                 return "redirect:/admin/main.jsp";
            }
            request.setAttribute("msg","�û������������");
            return  "forward:/admin/login.jsp";
        }catch(Exception e){
            e.printStackTrace();
            request.setAttribute("msg","ϵͳ����");
            return  "forward:/admin/login.jsp";
        }
	}
	
	@RequestMapping("/toMainPage")
	public String  toMainPage(){
		return "main";
	}
	
	@RequestMapping("/logout")
	public String logout( HttpServletRequest request) {
	    try { 
	    	HttpSession session = request.getSession();
	        session.invalidate();//����Session
	        return  "forward:/admin/login.jsp";
	    } catch(Exception e) { 
	    	e.printStackTrace();
	        request.setAttribute("msg","ϵͳ����");
	        return  "forward:/admin/login.jsp";
	}}


	
}
