package com.commerce.order_service.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.commerce.order_service.client.ProductServiceClient;
import com.commerce.order_service.dto.request.CreateOrderRequest;
import com.commerce.order_service.dto.request.OrderItemRequest;
import com.commerce.order_service.entity.Order;
import com.commerce.order_service.entity.OrderItem;
import com.commerce.order_service.entity.OrderStatus;
import com.commerce.order_service.exception.InsufficientStockException;
import com.commerce.order_service.exception.OrderNotFoundException;
import com.commerce.order_service.repository.OrderRepository;
import com.commerce.order_service.response.OrderItemResponse;
import com.commerce.order_service.response.OrderResponse;
import com.commerce.order_service.response.ProductResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final OrderRepository orderRepository;
	private final ProductServiceClient productServiceClient;
	
	private OrderItemResponse mapOrderItemToOrderItemResponse(OrderItem orderItem) {
		
		return OrderItemResponse.builder()
				.price(orderItem.getPriceAtOrderTime())
				.productId(orderItem.getProductId())
				.quantity(orderItem.getQuantity())
				.productName(orderItem.getProductName())
				.build();
		
	}
	
	private OrderResponse mapOrderToOrderResponse(Order orders) {
		
		List<OrderItemResponse> orderItemResponseList = orders.getItems().stream().map(this::mapOrderItemToOrderItemResponse).toList();
		
		
		return OrderResponse.builder()
				.id(orders.getId())
				.status(orders.getStatus())
				.totalAmount(orders.getTotalAmount())
				.items(orderItemResponseList)
				.createdAt(orders.getCreatedAt())				
				.build();
		
	}
	
	
	public OrderResponse createOrder(CreateOrderRequest request){
		

		
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		String email = (String) authentication.getPrincipal();
		
		
		//List got from the request (request by the customer List of products)
		List<OrderItemRequest> orderList = request.items();
		
		//Order list that will be created by program and will be stored
		List<OrderItem> orderListCreated = new ArrayList<>();
		
		//Total amount of all the order items 
		BigDecimal totalOfAllTheOrderItems = BigDecimal.ZERO;
		
		Order orderCreated = new Order();
		
		for(OrderItemRequest order : orderList) {
			
			ProductResponse product = productServiceClient.getProductByID(order.productId());
			
			Integer productStockQuantity = product.stockQuantity();
			Integer quantityRequested = order.quantity();
			
			if(quantityRequested > productStockQuantity) throw new InsufficientStockException("Insufficient Stock for product : "+ product.name());
			
			OrderItem orderItem = OrderItem.builder()
					.order(orderCreated)
					.priceAtOrderTime(product.price())
					.productId(product.id())
					.quantity(quantityRequested) // Quantity that customer wants to order not the quantity of product in database
					.productName(product.name())
					.build();
			
			orderListCreated.add(orderItem);
			
			totalOfAllTheOrderItems = totalOfAllTheOrderItems.add(product.price().multiply(BigDecimal.valueOf(quantityRequested)));
		}
		
		orderCreated.setItems(orderListCreated);
		orderCreated.setStatus(OrderStatus.PENDING);
		orderCreated.setTotalAmount(totalOfAllTheOrderItems);
		orderCreated.setUserEmail(email);
		
		Order savedOrder = orderRepository.save(orderCreated);	
		
		return mapOrderToOrderResponse(savedOrder);
		
	}
	
	public OrderResponse getOrderById(UUID id) {
			
		Order fetchedOrder = orderRepository.findById(id).orElseThrow(()-> new OrderNotFoundException("Order with particular ID not found"));
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		boolean isAdmin = authentication
				.getAuthorities()
				.stream().anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));
		
		String email = (String) authentication.getPrincipal();
		
		if(!isAdmin && !email.equals(fetchedOrder.getUserEmail())) throw new OrderNotFoundException("Order with particular ID not found");
		
		return mapOrderToOrderResponse(fetchedOrder);
	}
	
	public List<OrderResponse> getMyOrder() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = (String) authentication.getPrincipal();
		
		List<Order> fetchedOrder = orderRepository.findByUserEmail(email);
		
		return fetchedOrder.stream().map(this::mapOrderToOrderResponse).toList();
			
	}
	
	
	@PreAuthorize("hasRole('ADMIN')")
	public List<OrderResponse> getAllOrder(){
		
		List<Order> fetchedOrder = orderRepository.findAll();
		
		return fetchedOrder.stream().map(this::mapOrderToOrderResponse).toList();
	}
	
}
