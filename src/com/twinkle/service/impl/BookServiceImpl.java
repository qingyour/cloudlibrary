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
import com.twinkle.entity.PageResult;
import com.twinkle.mapper.BookMapper;
import com.twinkle.service.BookService;

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
		System.out.println("��ǰҪ���ĵ��鼮idΪ��"+book.getId());
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
		// ���÷�ҳ��ѯ�Ĳ�����������ѯ�ͻᰴ��ҳ�������з�ҳ��ѯ
		PageHelper.startPage(pageNum, pageSize);
		// ����ҳ������ѯһҳ��¼
		Page<Book> page = bookMapper.searchBooks(book);
		// ����ѯ���ļ�¼���������ݼ���װ��PageResult�����з���
		return new PageResult(page.getTotal(),page.getResult());
	}

}
