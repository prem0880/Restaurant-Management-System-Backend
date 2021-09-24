package com.rms.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NonNull
@NoArgsConstructor
@Data
public class CustomerDto {

	private Long id;
	private String name;
	private String email;
	private String password;
	private Long phoneNumber;

}
