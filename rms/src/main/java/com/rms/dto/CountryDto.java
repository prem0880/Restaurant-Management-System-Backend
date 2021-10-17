package com.rms.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NonNull
@NoArgsConstructor
@Data
public class CountryDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	@NotBlank(message="Country Name should not be null")
	private String name;
	private Timestamp createdOn;
	private Timestamp updatedOn;
}
