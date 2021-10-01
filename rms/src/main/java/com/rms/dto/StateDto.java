package com.rms.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import com.rms.entity.Country;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NonNull
@NoArgsConstructor
@Data
public class StateDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private Country country;
	private Timestamp createdOn;
	private Timestamp updatedOn;
}
