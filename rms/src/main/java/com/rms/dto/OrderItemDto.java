package com.rms.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.validation.constraints.Min;

import com.rms.entity.Order;

import com.rms.entity.Product;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NonNull
@NoArgsConstructor
@Data
public class OrderItemDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	@Min(0)
	private Integer quantity;
	@Min(0)
	private Double price;
	private Order order;
	private Product product;
	private Timestamp createdOn;
	private Timestamp updatedOn;

}
