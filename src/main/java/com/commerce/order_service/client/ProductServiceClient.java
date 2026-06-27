package com.commerce.order_service.client;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.commerce.order_service.response.ProductResponse;

@Component
@FeignClient(name="product-service")
public interface ProductServiceClient {

	@GetMapping("/api/v1/products/{id}")
	ProductResponse getProductByID(@PathVariable UUID id);
	
}
