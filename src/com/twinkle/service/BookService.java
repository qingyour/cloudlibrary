package com.twinkle.service;

import com.twinkle.domain.Book;
import com.twinkle.domain.User;
import com.twinkle.entity.PageResult;

public interface BookService {
	PageResult selectNewBooks(Integer pageNum,Integer pageSize);
	//����id����
	Book findById(String id);
	//����
	Integer borrowBook(Book book);
	
	//��ҳ��ѯͼ��
	PageResult search(Book book, Integer pageNum, Integer pageSize);
	
	//���ͼ��
	Integer addBook(Book book);
	
	//�༭ͼ��
	Integer editBook(Book book);
	
	//��ѯ��ǰ���ĵ�ͼ��
	PageResult searchBorrowed(Book book,User user,Integer pageNum,Integer pageSize);
	
	boolean returnBook(String id,User user);
}
