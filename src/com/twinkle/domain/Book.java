package com.twinkle.domain;

import java.io.Serializable;

public class Book implements Serializable {
	private Integer id;                      // ͼ����
    private String name;                  // ͼ������
    private String isbn,  press;// ͼ���׼ISBN��š�ͼ�����
    private String author;                 // ͼ������
    private Integer pagination;        // ͼ��ҳ��
    private Double price;                 // ͼ��۸�
    private String uploadTime,  status; // ͼ���ϼ�ʱ�䡢ͼ��״̬
    private String borrower,  borrowTime;// ͼ������ˡ�ͼ�����ʱ��
    private String returnTime; //ͼ��Ԥ�ƹ黹ʱ��
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getPress() {
		return press;
	}
	public void setPress(String press) {
		this.press = press;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Integer getPagination() {
		return pagination;
	}
	public void setPagination(Integer pagination) {
		this.pagination = pagination;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBorrower() {
		return borrower;
	}
	public void setBorrower(String borrower) {
		this.borrower = borrower;
	}
	public String getBorrowTime() {
		return borrowTime;
	}
	public void setBorrowTime(String borrowTime) {
		this.borrowTime = borrowTime;
	}
	public String getReturnTime() {
		return returnTime;
	}
	public void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", isbn=" + isbn + ", press=" + press + ", author=" + author
				+ ", pagination=" + pagination + ", price=" + price + ", uploadTime=" + uploadTime + ", status="
				+ status + ", borrower=" + borrower + ", borrowTime=" + borrowTime + ", returnTime=" + returnTime + "]";
	}
    

}
