package com.ecommerce.order_service.service;


import com.ecommerce.order_service.client.ProductServiceClient;
import com.ecommerce.order_service.dto.response.ProductResponse;
import com.ecommerce.order_service.exception.ProductServiceUnavailableException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
@Slf4j
public class ProductLookupService {

    final private ProductServiceClient productServiceClient;


    @Retry(name="product-service")
    @CircuitBreaker(name="product-service",fallbackMethod = "productLookupFallback")
    public ProductResponse getProductDetails(UUID productId)
    {
        return productServiceClient.getProductByID(productId);
    }

    public ProductResponse productLookupFallback(UUID productId, Exception ex){
        log.error("Product service Unavailable",ex);
        throw new ProductServiceUnavailableException("Product Service is Unavailable");
    }






}
