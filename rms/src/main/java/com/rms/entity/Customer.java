package com.rms.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NonNull
@NoArgsConstructor
@Data
@Entity
@Table(name="customer")
public class Customer implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Email
	@Column(name="email")
	private String email;
	
	@Min(value=10,message="Password should not be less than 10")
	@Column(name="password")
	private String password;
	
	@Max(value=10 ,message="Phone Number should not be greater than 10")
	@Column(name="phone_number",unique=true)
	private Long phoneNumber;
	
	
	@Column(name="created_on")
	private Timestamp createdOn;
	
	@Column(name="updated_on")
	private Timestamp updatedOn;
	
}
