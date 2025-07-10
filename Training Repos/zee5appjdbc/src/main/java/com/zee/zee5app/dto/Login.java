package com.zee.zee5app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Login {
	
	private String userName;
	private String password;
	private String regId;
	private ROLE role;
}
