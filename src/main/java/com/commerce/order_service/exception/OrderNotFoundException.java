package com.commerce.order_service.exception;

public class OrderNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7809289821957414154L;

	public OrderNotFoundException(String message) {
		super(message);
	}
}
