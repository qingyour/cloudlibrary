package com.twinkle.service;

import com.twinkle.domain.Book;
import com.twinkle.entity.PageResult;

public interface BookService {
	PageResult selectNewBooks(Integer pageNum,Integer pageSize);
	//根据id查书
	Book findById(String id);
	//借书
	Integer borrowBook(Book book);
	
	//分页查询图书
	PageResult search(Book book, Integer pageNum, Integer pageSize);

}
