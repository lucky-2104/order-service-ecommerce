package com.commerce.order_service.client;

import com.commerce.order_service.exception.ProductLookupException;

import feign.Response;
import feign.codec.ErrorDecoder;

public class ProductServiceErrorDecoder implements ErrorDecoder {

	@Override
	public Exception decode(String methodKey, Response response) {
		
		if(response.status() == 404) {
			return new ProductLookupException("Product Not found Via feign Call");
		}
		
		return new Default().decode(methodKey, response);
	}

}
