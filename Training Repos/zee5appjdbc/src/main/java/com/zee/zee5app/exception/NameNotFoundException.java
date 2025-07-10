package com.zee.zee5app.exception;

import lombok.ToString;

@ToString(callSuper = true)
public class NameNotFoundException extends Exception {
	public NameNotFoundException(String msg) {
		// TODO Auto-generated constructor stub
		super(msg);
	}
}
