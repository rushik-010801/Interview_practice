package com.learning.dto;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

//Adding Lombok annotations for generating setter and getter, constructors, toString method
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

//This is used for ORM mapping
@Entity
//For customizing the table name
@Table(name = "register", uniqueConstraints = {@UniqueConstraint (columnNames = {"password", "email"})})

public class Register {
	
	@Id //this is for primary key
	@Column(name = "regId") // used for column name custimization
	@GeneratedValue(strategy = GenerationType.AUTO) // for generating sequential values
	private int regId;
	
	@Size(max = 50)
	@NotBlank
	@Email
	private String email;
	
	@Size(max = 50)
	@NotBlank
	private String name;
	
	@Size(max = 100)
	@NotBlank
	private String password;
	
	@Size(max = 150)
	@NotBlank
	private String address;
	
}
