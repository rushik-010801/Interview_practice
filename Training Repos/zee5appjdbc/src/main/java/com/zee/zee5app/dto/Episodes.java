package com.zee.zee5app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Episodes {
	private String epiId;
	private String serId;
	private String episodename;
	private float epilength;
	private String location;
	//private String trailer;
}
