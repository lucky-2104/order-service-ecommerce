package com.ecommerce.order_service.exception;

public class InsufficientStockException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7366417250342062906L;

	public InsufficientStockException(String message) {
		super(message);
	}

}
