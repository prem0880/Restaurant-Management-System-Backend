package com.rms.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NonNull
@NoArgsConstructor
@Data
public class CustomerDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	@NotBlank(message="Category Name should not be null")
	private String name;
	@Email
	private String email;
	private String password;
	@Min(value=10)
	private Long phoneNumber;
	private Timestamp createdOn;
	private Timestamp updatedOn;

}
