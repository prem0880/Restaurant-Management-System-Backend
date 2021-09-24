package com.rms.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.ForeignKey;

@NonNull
@NoArgsConstructor
@Data
@Entity
@Table(name="product")
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="image")
	private String image;
	
	@Column(name="type")
	private String type;
	
	@Column(name="price")
	private Double price;
	
	@Max(value=50 ,message="Description should not be greater than 50")
	@Column(name="description")
	private String description;
	
	@Column(name="tax")
	private Double tax;
	
	@ManyToOne
    @JoinColumn(name = "category_id",foreignKey = @ForeignKey(name = "FK_CATEGORY_ID"))
    private Category category;
	
	@ManyToOne
    @JoinColumn(name = "meal_id",foreignKey = @ForeignKey(name = "FK_MEAL_ID"))
    private Meal meal;
	
	
	
	
}
