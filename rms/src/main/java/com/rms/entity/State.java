package com.rms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NonNull
@NoArgsConstructor
@Data
@Entity
@Table(name = "state")
public class State {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
		
	@Column(name = "name")
	private String name;
		
	@ManyToOne
	@JoinColumn(name = "country_id",foreignKey = @ForeignKey(name = "FK_COUNTRY_ID")) 
	private Country country;
	
	
}
