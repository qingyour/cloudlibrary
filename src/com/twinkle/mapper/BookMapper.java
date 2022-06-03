package com.twinkle.mapper;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.github.pagehelper.Page;
import com.twinkle.domain.Book;

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
	//����id��ѯͼ����Ϣ
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
    //��ҳ��ѯͼ��
    Page<Book> searchBooks(Book book);
	
	
	
}
