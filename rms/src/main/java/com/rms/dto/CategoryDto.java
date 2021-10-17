package com.rms.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class CategoryDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	@NotBlank(message="Category Name should not be null")
	private String name;
	private Timestamp createdOn;
	private Timestamp updatedOn;
}
