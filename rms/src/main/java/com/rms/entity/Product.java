package com.rms.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


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
	
	@Column(name="name",nullable=false,length=20)
	private String name;
	
	@Column(name="image",nullable=false)
	private String image;
	
	@Column(name="type",nullable=false)
	private String type;
	
	@Column(name="price",nullable=false)
	private Double price;
	
	@Column(name="description",nullable=false,length=50)
	private String description;
	
	@Column(name="tax",nullable=false)
	private Double tax;
	
	@ManyToOne
    @JoinColumn(name = "category_id",referencedColumnName="id",foreignKey = @ForeignKey(name = "FK_CATEGORY"), nullable = false)
    private Category category;
	
	@ManyToOne
    @JoinColumn(name = "meal_id",referencedColumnName="id",foreignKey = @ForeignKey(name = "FK_MEAL"), nullable = false)
    private Meal meal;
	
	
	
	
}
