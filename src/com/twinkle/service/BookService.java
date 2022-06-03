package com.twinkle.service;

import com.twinkle.domain.Book;
import com.twinkle.entity.PageResult;

public interface BookService {
	PageResult selectNewBooks(Integer pageNum,Integer pageSize);
	//����id����
	Book findById(String id);
	//����
	Integer borrowBook(Book book);
	
	//��ҳ��ѯͼ��
	PageResult search(Book book, Integer pageNum, Integer pageSize);

}
