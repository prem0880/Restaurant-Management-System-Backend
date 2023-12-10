package com.rms.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;


import com.rms.entity.Address;
import com.rms.entity.Customer;
import com.rms.entity.OrderItem;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@NonNull
@NoArgsConstructor
@Getter
@Setter
public class OrderDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Timestamp date;
	private Double totalPrice;
	private String modeOfPayment;
	private String status;
	private Customer customer;
	private Address address;
	private List<OrderItem> orderItem;
	private Timestamp createdOn;
	private Timestamp updatedOn;

}
