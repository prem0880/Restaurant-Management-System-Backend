package com.rms.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NonNull
@NoArgsConstructor
@Data
public class CategoryDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private Timestamp createdOn;
	private Timestamp updatedOn;
}
