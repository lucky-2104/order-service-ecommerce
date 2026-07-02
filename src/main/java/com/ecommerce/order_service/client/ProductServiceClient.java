package com.ecommerce.order_service.client;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ecommerce.order_service.config.FeignConfig;
import com.ecommerce.order_service.dto.response.ProductResponse;

@Component
@FeignClient(name="product-service",configuration=FeignConfig.class)
public interface ProductServiceClient {

	@GetMapping("/api/v1/products/{id}")
	ProductResponse getProductByID(@PathVariable UUID id);
	
}
