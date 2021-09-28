package com.rms.dto;


import java.io.Serializable;
import java.sql.Timestamp;

import com.rms.entity.Category;
import com.rms.entity.Meal;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


@NonNull
@NoArgsConstructor
@Data
public class ProductDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private String image;
	private String type;
	private Double price;
	private Double tax;
	private String description;
    private Category category;
    private Meal meal;
	private Timestamp createdOn;
	private	Timestamp updatedOn;
    
        
    
    
}
