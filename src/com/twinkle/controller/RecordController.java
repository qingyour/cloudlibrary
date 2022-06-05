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
		System.out.println("============调用了查询借阅数据的方法===================");
		
		if(pageNum == null){
			pageNum = 1;
		}
		if(pageSize == null){
			pageSize = 10;
		}
		//1.获取当前登录的用户
		User user =(User)request.getSession().getAttribute("USER_SESSION");

		//2.搜索借阅记录
		PageResult pageResult = recordService.searchRecords(record, user, pageNum, pageSize);
		//3.将查询到的记录放入modelAndView中
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("record");
		modelAndView.addObject("pageResult",pageResult);//把结果添加
		modelAndView.addObject("search",record);
		modelAndView.addObject("pageNum",pageNum);
		modelAndView.addObject("gourl",request.getRequestURI());
		return modelAndView;
		
		
		//4.返回
	}
}
