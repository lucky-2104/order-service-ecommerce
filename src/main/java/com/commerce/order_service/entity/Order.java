package com.commerce.order_service.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
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
@Table(name="orders")
public class Order {

	@Id
	@GeneratedValue(strategy=GenerationType.UUID)
	@Column(name="id",nullable=false,updatable=false)
	private UUID id;
	
	@Column(name="user_email",nullable=false,updatable=true)
	@Email
	private String userEmail;
	
	@Column(name="status",nullable=false,updatable=true)
	@Enumerated(EnumType.STRING)
	private OrderStatus status;
	
	@Column(name="total_amount",nullable=false,updatable=true)
	private BigDecimal totalAmount;
	
	@OneToMany(mappedBy="order", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OrderItem> items;
	
	@CreationTimestamp
	@Column(name="created_at",nullable=false,updatable=false)
	private LocalDateTime createdAt;
	
	@UpdateTimestamp
	@Column(name="updated_at",nullable=false,updatable=true)
	private LocalDateTime updatedAt;
	
	
	
}
