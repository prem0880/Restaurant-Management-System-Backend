package com.rms.dto;

import java.sql.Timestamp;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NonNull
@NoArgsConstructor
@Data
public class MealDto {
	private Long id;
	private String name;
	private Timestamp createdOn;
	private	Timestamp updatedOn;
}
