package com.rms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NonNull
@NoArgsConstructor
@Data
@Entity
@Table(name = "address")
public class Address {

	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	@Column(name = "id")
	private Long id;
	
	@Column(name = "address_line")
	private String addressLine;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "pincode")
	private Long pincode;
	
	@ManyToOne 
	@JoinColumn(name = "state_id",foreignKey = @ForeignKey(name = "FK_STATE_ID"))
	private State state;
	
	@ManyToOne
	@JoinColumn(name = "customer_id",foreignKey = @ForeignKey(name = "FK_CUSTOMER_ID")) 
	private Customer customer;

		
}
