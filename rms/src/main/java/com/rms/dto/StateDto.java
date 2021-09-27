package com.rms.dto;


import com.rms.entity.Country;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NonNull
@NoArgsConstructor
@Data
public class StateDto {
	private Long id;
	private String name;
	private Country country;
}
