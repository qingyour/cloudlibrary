package com.twinkle.entity;

import java.io.Serializable;

public class Result<T> implements Serializable {
	private boolean success; // ��ʶ�Ƿ�ɹ�����
	private String message; // ��Ҫ���ݵ���Ϣ
	private T data; // ��Ҫ���ݵ�����

	public Result(boolean success, String message) {
		super();
		this.success = success;
		this.message = message;
	}

	public Result(boolean success, String message, T data) {
		this.success = success;
		this.message = message;
		this.data = data;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	
}