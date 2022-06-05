package com.twinkle.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.twinkle.domain.Record;
import com.twinkle.domain.User;
import com.twinkle.entity.PageResult;
import com.twinkle.service.RecordService;

@Controller
@RequestMapping("/record")
public class RecordController {
	@Autowired
	private RecordService recordService;
	
	
	@RequestMapping("/searchRecords")
	private ModelAndView searchRecords(Record record,Integer pageNum,Integer pageSize,HttpServletRequest request){
		System.out.println("============�����˲�ѯ�������ݵķ���===================");
		
		if(pageNum == null){
			pageNum = 1;
		}
		if(pageSize == null){
			pageSize = 10;
		}
		//1.��ȡ��ǰ��¼���û�
		User user =(User)request.getSession().getAttribute("USER_SESSION");

		//2.�������ļ�¼
		PageResult pageResult = recordService.searchRecords(record, user, pageNum, pageSize);
		//3.����ѯ���ļ�¼����modelAndView��
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("record");
		modelAndView.addObject("pageResult",pageResult);//�ѽ�����
		modelAndView.addObject("search",record);
		modelAndView.addObject("pageNum",pageNum);
		modelAndView.addObject("gourl",request.getRequestURI());
		return modelAndView;
		
		
		//4.����
	}
}
