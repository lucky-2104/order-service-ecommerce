package com.commerce.order_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.commerce.order_service.client.ProductServiceErrorDecoder;

import feign.codec.ErrorDecoder;

@Configuration
public class FeignConfig {

	
	@Bean
	public ErrorDecoder errorDecoder() {
		return new ProductServiceErrorDecoder();
	}
}
