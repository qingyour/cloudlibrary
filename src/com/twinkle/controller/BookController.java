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
		 //查询最新上架的5个的图书信息
		int pageNum = 1;
		int pageSize = 5;
		System.out.println("调用了查询最新5本书的方法");
		PageResult pageResult = bookService.selectNewBooks(pageNum, pageSize);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("books_new");
		modelAndView.addObject("pageResult",pageResult);
		return modelAndView;
		
	}
	
	
	@RequestMapping("/findById")
	@ResponseBody //向浏览器提示信息
	public Result<Book> findById(String id){
		try {
			Book book = bookService.findById(id);
			if(book==null){
				return new Result(false,"查询图书失败!");
			}
			return new Result(true,"查询图书成功",book);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false,"查询图书失败!");
		}
	}
	
	@RequestMapping("/borrowBook")
	@ResponseBody //向浏览器提示信息
	public Result borrowBook(Book book,HttpSession session){
		//获取登录的用户姓名 用于查看谁借了这本书
		String name = ((User)session.getAttribute("USER_SESSION")).getName();
		book.setBorrower(name);
		try {
			//1.要借书先要找到这本书的id 查询数据库是否有这本书
			Integer count = bookService.borrowBook(book);
			if(count!=1){
				return new Result(false,"借阅图书失败!");
			}
			return new Result(true,"借阅图书成功，请到行政中心取书!");
			
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false,"查询图书失败!");
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
				return new Result(false,"添加图书失败");
				
			}
			return new Result(true,"添加图书成功");
		} catch (Exception e) {
			e.printStackTrace();
			//系统错误
			return new Result(false,"添加图书失败!");
		}
	}
	
	@ResponseBody
	@RequestMapping("/editBook")
	public Result editBook(Book book){
		try {
			Integer count = bookService.editBook(book);
			if(count!=1){
				return new Result(false,"编辑图书失败");
			}
			return new Result(true,"编辑图书成功");
		} catch (Exception e) {
			e.printStackTrace();
			//系统错误
			return new Result(false,"编辑图书失败!");
		}
	}
	
	@RequestMapping("searchBorrowed")
	public ModelAndView searchBorrowed(Book book,Integer pageNum, Integer pageSize,HttpServletRequest request){
		//当前页
		if(pageNum == null){
			pageNum = 1;
		}
		//一页显示多少行数据
		if(pageSize == null){
			pageSize = 10;
		}
		//获取当前用户
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
