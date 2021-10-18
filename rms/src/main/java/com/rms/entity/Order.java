package com.rms.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@NonNull
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "date")
	private Timestamp date;

	@Column(name = "total_price")
	private Double totalPrice;

	@Column(name = "mode_of_payment")
	private String modeOfPayment;

	@Column(name = "status")
	private String status;

	@ManyToOne
	@JoinColumn(name = "customer_id", foreignKey = @ForeignKey(name = "FK_ORDER_CUSTOMER_ID"))
	@JsonIgnore
	private Customer customer;

	@ManyToOne
	@JoinColumn(name = "address_id", foreignKey = @ForeignKey(name = "FK_ORDER_ADDRESS_ID"))
	@JsonIgnore
	private Address address;

	@OneToMany(mappedBy = "order")
	@Column(name = "orders")
	private List<OrderItem> orderItem;

	@Column(name = "created_on")
	private Timestamp createdOn;

	@Column(name = "updated_on")
	private Timestamp updatedOn;

}
