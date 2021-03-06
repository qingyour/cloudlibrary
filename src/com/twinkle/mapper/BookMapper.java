package com.twinkle.mapper;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.github.pagehelper.Page;
import com.twinkle.domain.Book;
import com.twinkle.domain.User;
import com.twinkle.entity.PageResult;

public interface BookMapper {
	@Select("select * from book where book_status !='3' order by book_uploadtime DESC")
	@Results(id="bookMap",value={
			@Result(id = true,column = "book_id",property = "id"),
			@Result(column = "book_name",property = "name"),
			@Result(column = "book_isbn",property = "isbn"),
			@Result(column = "book_press",property = "press"),
			@Result(column = "book_author",property = "author"),
			@Result(column = "book_pagination",property = "pagination"),
			@Result(column = "book_price",property = "price"),
			@Result(column = "book_uploadTime",property = "uploadTime"),
			@Result(column = "book_status",property = "status"),
			@Result(column = "book_borrower",property = "borrower"),
			@Result(column = "book_borrowTime",property = "borrowTime"),
			@Result(column = "book_returnTime",property = "returnTime")
	})
	Page<Book> selectNewBooks();
	
	@Select("select * from book where book_id=#{id}")
	@ResultMap("bookMap")
	//根据id查询图书信息
	Book findById(String id);
	
	
	Integer editBook(Book book);
	
	@Select({"<script>" +
	        "SELECT * FROM book " +
	        "where book_status !='3'" +
	        "<if test=\"name != null\"> AND  book_name  like  CONCAT('%',#{name},'%')</if>" +
	        "<if test=\"press != null\"> AND book_press like  CONCAT('%', #{press},'%') </if>" +
	        "<if test=\"author != null\"> AND book_author like  CONCAT('%', #{author},'%')</if>" +
	        "order by book_borrowtime" +
	        "</script>"
	})
    @ResultMap("bookMap")
    //分页查询图书
    Page<Book> searchBooks(Book book);
	
	
//	新增图书
	Integer addBook(Book book);
	
	//查询所有借阅但未归还的图书和所有待确认归还的图书
	Page<Book> selectBorrowed(Book book);
	
	//查询我的借阅但未归还的图书
	Page<Book> selectMyBorrowed(Book book);

	
	
	
	
}
