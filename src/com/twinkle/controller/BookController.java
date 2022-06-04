package com.twinkle.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.twinkle.domain.Book;
import com.twinkle.domain.User;
import com.twinkle.entity.PageResult;
import com.twinkle.entity.Result;
import com.twinkle.service.BookService;

@Controller
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@RequestMapping("selectNewbooks")
	public ModelAndView selectNewbooks(){
		 //��ѯ�����ϼܵ�5����ͼ����Ϣ
		int pageNum = 1;
		int pageSize = 5;
		System.out.println("�����˲�ѯ����5����ķ���");
		PageResult pageResult = bookService.selectNewBooks(pageNum, pageSize);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("books_new");
		modelAndView.addObject("pageResult",pageResult);
		return modelAndView;
		
	}
	
	
	@RequestMapping("/findById")
	@ResponseBody //���������ʾ��Ϣ
	public Result<Book> findById(String id){
		try {
			Book book = bookService.findById(id);
			if(book==null){
				return new Result(false,"��ѯͼ��ʧ��!");
			}
			return new Result(true,"��ѯͼ��ɹ�",book);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false,"��ѯͼ��ʧ��!");
		}
	}
	
	@RequestMapping("/borrowBook")
	@ResponseBody //���������ʾ��Ϣ
	public Result borrowBook(Book book,HttpSession session){
		//��ȡ��¼���û����� ���ڲ鿴˭�����Ȿ��
		String name = ((User)session.getAttribute("USER_SESSION")).getName();
		book.setBorrower(name);
		try {
			//1.Ҫ������Ҫ�ҵ��Ȿ���id ��ѯ���ݿ��Ƿ����Ȿ��
			Integer count = bookService.borrowBook(book);
			if(count!=1){
				return new Result(false,"����ͼ��ʧ��!");
			}
			return new Result(true,"����ͼ��ɹ����뵽��������ȡ��!");
			
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false,"��ѯͼ��ʧ��!");
		}
	}
	@RequestMapping("/search")
	public ModelAndView search(Book book,Integer pageNum,
			Integer pageSize,HttpServletRequest request){
		if(pageNum == null){
			pageNum = 1;
		}
		if(pageSize == null){
			pageSize = 10;
		}
		PageResult pageResult = bookService.search(book, pageNum, pageSize);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("books");
		modelAndView.addObject("pageResult",pageResult);
		modelAndView.addObject("search",book);
		modelAndView.addObject("pageNum",pageNum);
		modelAndView.addObject("gourl",request.getRequestURI());
		return modelAndView;
		
	}
	
	@ResponseBody
	@RequestMapping("/addBook")
	public Result addBook(Book book){
		try {
			Integer count = bookService.addBook(book);
			if(count!=1){
				return new Result(false,"���ͼ��ʧ��");
				
			}
			return new Result(true,"���ͼ��ɹ�");
		} catch (Exception e) {
			e.printStackTrace();
			//ϵͳ����
			return new Result(false,"���ͼ��ʧ��!");
		}
	}
	
	@ResponseBody
	@RequestMapping("/editBook")
	public Result editBook(Book book){
		try {
			Integer count = bookService.editBook(book);
			if(count!=1){
				return new Result(false,"�༭ͼ��ʧ��");
			}
			return new Result(true,"�༭ͼ��ɹ�");
		} catch (Exception e) {
			e.printStackTrace();
			//ϵͳ����
			return new Result(false,"�༭ͼ��ʧ��!");
		}
	}
	
	@RequestMapping("searchBorrowed")
	public ModelAndView searchBorrowed(Book book,Integer pageNum, Integer pageSize,HttpServletRequest request){
		//��ǰҳ
		if(pageNum == null){
			pageNum = 1;
		}
		//һҳ��ʾ����������
		if(pageSize == null){
			pageSize = 10;
		}
		//��ȡ��ǰ�û�
		User user =(User)request.getSession().getAttribute("USER_SESSION");
		PageResult pageResult = bookService.searchBorrowed(book, user, pageNum, pageSize);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("book_borrowed");
		modelAndView.addObject("pageResult",pageResult);
		modelAndView.addObject("search",book);
		modelAndView.addObject("pageNum",pageNum);
		modelAndView.addObject("gourl",request.getRequestURI());
		return modelAndView;
	}
	
	
	
	
	
}
