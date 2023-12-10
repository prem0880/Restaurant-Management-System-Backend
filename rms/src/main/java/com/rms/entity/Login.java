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

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NonNull
@Entity
@Table(name="Login")
@Data
@NoArgsConstructor
public class Login implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Email
	@Column(name = "email", unique=true)
	private String email;
	
	@Column(name="password")
	private String password;
	
	@Column(name="role")
	private String role;
	
	@Column(name = "created_on")
	private Timestamp createdOn;

	@Column(name = "updated_on")
	private Timestamp updatedOn;
}
