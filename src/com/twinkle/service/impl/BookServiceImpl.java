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
		//1.�ҳ�����һ����
		Book book = this.findById(id);
		//2. ���κ˶Խ����˺ͻ������Ƿ���ͬ
		boolean hd = book.getBorrower().equals(user.getName());
		//3.���һ��
		if(hd){
			//3.1 �ı����״̬ ���������ݿ������
			book.setStatus("2");
			bookMapper.editBook(book);
		}
		//4.���ؽ��
		return hd;
	}
	
	@Autowired
	private RecordService recordService;
	
	//�黹ȷ��
	@Override
	public Integer returnConfirm(String id) {
		//1.����ͼ��id ��ѯͼ���������Ϣ
		Book book = this.findById(id);
		
		Record record = this.setRecord(book);
		
		//2.��ͼ��Ľ���״̬�޸�Ϊ�ɽ���0
		book.setStatus("0");
		//3.��������˺ͽ���ʱ���Լ�Ԥ�ƹ黹ʱ�����Ϣ
		book.setBorrower("");
		book.setBorrowTime("");
		book.setReturnTime("");
		
		// 5. �鿴�Ƿ��޸ĳɹ�
		Integer count = bookMapper.editBook(book);
		if(count==1){
			return recordService.addRecord(record);
		}
		return 0;
		
		/*
		//4.�޸�ͼ����Ϣ
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
