package com.twinkle.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.twinkle.domain.Book;
import com.twinkle.domain.Record;
import com.twinkle.domain.User;
import com.twinkle.entity.PageResult;
import com.twinkle.mapper.BookMapper;
import com.twinkle.service.BookService;
import com.twinkle.service.RecordService;

@Service
@Transactional
public class BookServiceImpl implements BookService {

	@Autowired
	private BookMapper bookMapper;
	@Override
	public PageResult selectNewBooks(Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum,pageSize);
		Page<Book> page = bookMapper.selectNewBooks();
		return new PageResult(page.getTotal(), page.getResult());
	}
	@Override
	public Book findById(String id) {
		
		return bookMapper.findById(id);
	}
	@Override
	public Integer borrowBook(Book book) {
		System.out.println("当前要借阅的书籍id为："+book.getId());
		Book b = this.findById(book.getId()+"");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		book.setBorrowTime(dateFormat.format(new Date()));
		book.setStatus("1");
		book.setPrice(b.getPrice());
		book.setUploadTime(b.getUploadTime());
		return bookMapper.editBook(book);
	}
	@Override
	public PageResult search(Book book, Integer pageNum, Integer pageSize) {
		// 设置分页查询的参数，后续查询就会按分页参数进行分页查询
		PageHelper.startPage(pageNum, pageSize);
		// 按分页参数查询一页记录
		Page<Book> page = bookMapper.searchBooks(book);
		// 将查询出的记录总数和数据集封装到PageResult对象中返回
		return new PageResult(page.getTotal(),page.getResult());
	}
	
	@Override
	public Integer addBook(Book book) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		book.setUploadTime(dateFormat.format(new Date()));
		return bookMapper.addBook(book);
	}
	
	@Override
	public Integer editBook(Book book) {
		// TODO Auto-generated method stub
		return bookMapper.editBook(book);
	}
	
	
	@Override
	public PageResult searchBorrowed(Book book, User user, Integer pageNum, Integer pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum,pageSize);
		Page<Book> page;
		book.setBorrower(user.getName());
		
		if("ADMIN".equals(user.getRole())){
			page = bookMapper.selectBorrowed(book);
		}else{
			page = bookMapper.selectMyBorrowed(book);
		}
		
		
		return new PageResult(page.getTotal(),page.getResult());
	}
	
	
	@Override
	public boolean returnBook(String id, User user) {
		//1.找出是哪一本书
		Book book = this.findById(id);
		//2. 二次核对借书人和还书人是否相同
		boolean hd = book.getBorrower().equals(user.getName());
		//3.如果一致
		if(hd){
			//3.1 改变书的状态 并更改数据库的内容
			book.setStatus("2");
			bookMapper.editBook(book);
		}
		//4.返回结果
		return hd;
	}
	
	@Autowired
	private RecordService recordService;
	
	//归还确认
	@Override
	public Integer returnConfirm(String id) {
		//1.根据图书id 查询图书的完整信息
		Book book = this.findById(id);
		
		Record record = this.setRecord(book);
		
		//2.将图书的借阅状态修改为可借阅0
		book.setStatus("0");
		//3.清楚借阅人和借阅时间以及预计归还时间的信息
		book.setBorrower("");
		book.setBorrowTime("");
		book.setReturnTime("");
		
		// 5. 查看是否修改成功
		Integer count = bookMapper.editBook(book);
		if(count==1){
			return recordService.addRecord(record);
		}
		return 0;
		
		/*
		//4.修改图书信息
		return bookMapper.editBook(book);
		*/
	}
	
	private Record setRecord(Book book){
		Record record = new Record();
		record.setBookname(book.getName());
		record.setBookisbn(book.getIsbn());
		record.setBorrower(book.getBorrower());
		record.setBorrowTime(book.getBorrowTime());
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		record.setRemandTime(dateFormat.format(new Date()));
		return record;
	}
	
	
	
	
	
	

}
