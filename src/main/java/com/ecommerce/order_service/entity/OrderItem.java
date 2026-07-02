package com.ecommerce.order_service.entity;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="order_items")
public class OrderItem {
	
	@Id
	@GeneratedValue(strategy=GenerationType.UUID)
	@Column(name="id",nullable=false,updatable=false)
	private UUID id;
	
	@ManyToOne
	@JoinColumn(name="order_id")
	private Order order;
	
	
	@Column(name="product_id",nullable=false,updatable=false)
	private UUID productId;
	
	@Column(name="product_name",nullable=false,updatable=true)
	private String productName;
	
	@Column(name="quantity",nullable=false,updatable=false)
	private Integer quantity;
	
	@Column(name="price_at_order_time",nullable=false,updatable=false)
	private BigDecimal priceAtOrderTime;

}
