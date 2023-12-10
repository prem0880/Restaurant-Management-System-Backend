package com.rms.entity;

import java.io.Serializable;
import java.sql.Timestamp;

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
@Table(name = "product")
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "image")
	private String image;

	@Column(name = "type")
	private String type;

	@Column(name = "price")
	private Double price;

	@Column(name = "description")
	private String description;

	@Column(name = "tax")
	private Double tax;

	@ManyToOne
	@JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "FK_PRODUCT_CATEGORY_ID"))
	private Category category;

	@ManyToOne
	@JoinColumn(name = "meal_id", foreignKey = @ForeignKey(name = "FK_PRODUCT_MEAL_ID"))
	private Meal meal;

	@Column(name = "created_on")
	private Timestamp createdOn;

	@Column(name = "updated_on")
	private Timestamp updatedOn;

}
