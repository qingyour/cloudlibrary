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
		//��ȡ����·��
		String uri = request.getRequestURI();
		System.out.println("��ǰ�û������·��"+uri+"=================");
		//���û���¼��������󣬷���
		if(uri.indexOf("login")>=0){
			return true;
		}
		//��ȡ��ǰ��¼�û�
		User user = (User)request.getSession().getAttribute("USER_SESSION");
//		String uri = request.getRequestURI();
		//����Ѿ���¼
		if(user!=null){
			//����ǹ���Ա������
			if("ADMIN".equals(user.getRole())){
				return true;
			}
			else if(!"ADMIN".equals(user.getRole())){
				//�������ͨ�û�
				for(String url : ignoreUrl){
					//���ҷ��ʵ���Դ���ǹ���ԱȨ�޵���Դ������
					System.out.println("��ǰurl:"+url);
					System.out.println("��ͨ�û���ѯ����������"+uri.indexOf(url));
					if(uri.indexOf(url)>=0){
						System.out.println("��ͨ�û���������");
						return true;
					}
				}
			}
//			return true;
		}
//		if(uri.indexOf("login")>=0){
//			return true;
//		}
		//������������ֱ�ӻص���¼ҳ��
		System.out.println("=================��¼ʧ��");
		request.setAttribute("msg", "����û�е�¼�����Ƚ��е�¼");
		request.getRequestDispatcher("/admin/login.jsp").forward(request, response);
		
		return false;	
	}
}
