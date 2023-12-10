package com.rms.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.rms.entity.Category;
import com.rms.entity.Meal;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NonNull
@NoArgsConstructor
@Data
public class ProductDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	@NotBlank(message="Product Name should not be null")
	private String name;
	@NotBlank(message = "Product Image can't be null")
	private String image;
	@Min(0)
	private Double price;
	@NotBlank
	private String type;
	@Min(0)
	private Double tax;
	@NotBlank
	private String description;
	private Category category;
	private Meal meal;
	private Timestamp createdOn;
	private Timestamp updatedOn;

}
