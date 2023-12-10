package com.rms.dto;

import java.io.Serializable;

import java.sql.Timestamp;

import javax.validation.constraints.Min;

import com.rms.entity.Customer;
import com.rms.entity.State;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class AddressDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String addressLine;
	private String city;
	@Min(value=6)
	private Long pincode;
	private State state;
	private Customer customer;
	private Timestamp createdOn;
	private Timestamp updatedOn;
}
