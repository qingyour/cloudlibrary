package com.twinkle.domain;

import java.io.Serializable;

public class User implements Serializable {
	private Integer id;       	               //�û�id
    private String name;      	     	//�û�����
    private String password;  		//�û�����
    private String email;     		//�û����䣨�û��˺ţ�
    private String role;      		//�û���ɫ
    private String status;    		//�û�״̬
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", email=" + email + ", role=" + role
				+ ", status=" + status + "]";
	}
    
    
		
}
