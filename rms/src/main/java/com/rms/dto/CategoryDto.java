package com.rms.dto;

import java.util.Set;

import com.rms.entity.Product;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


@NonNull
@NoArgsConstructor
@Data
public class CategoryDto {
	private Long id;
	private String name;
    private Set<Product> product;
}
