package com.rms.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NonNull
@NoArgsConstructor
@Data
public class CountryDto {
	private Long id;
	private String name;
}
