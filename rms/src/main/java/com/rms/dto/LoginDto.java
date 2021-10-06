package com.rms.dto;

import java.sql.Timestamp;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NonNull
@NoArgsConstructor
@Data
public class LoginDto {
	private Long id;
	private String email;
	private String password;
	private String role;
	private Timestamp createdOn;
	private Timestamp updatedOn;
}
