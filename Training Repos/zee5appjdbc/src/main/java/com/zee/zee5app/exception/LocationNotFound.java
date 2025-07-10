package com.zee.zee5app.exception;

import lombok.ToString;

@ToString(callSuper = true)
public class LocationNotFound extends Exception {
	public LocationNotFound(String msg) {
		// TODO Auto-generated constructor stub
		super(msg);
	}
}
