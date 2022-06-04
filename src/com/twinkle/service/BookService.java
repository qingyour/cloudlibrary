package com.twinkle.service;

import com.twinkle.domain.Book;
import com.twinkle.domain.User;
import com.twinkle.entity.PageResult;

public interface BookService {
	PageResult selectNewBooks(Integer pageNum,Integer pageSize);
	//根据id查书
	Book findById(String id);
	//借书
	Integer borrowBook(Book book);
	
	//分页查询图书
	PageResult search(Book book, Integer pageNum, Integer pageSize);
	
	//添加图书
	Integer addBook(Book book);
	
	//编辑图书
	Integer editBook(Book book);
	
	//查询当前借阅的图书
	PageResult searchBorrowed(Book book,User user,Integer pageNum,Integer pageSize);
	
	boolean returnBook(String id,User user);
}
