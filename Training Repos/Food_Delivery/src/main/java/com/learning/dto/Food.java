package com.learning.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "food")

public class Food {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int foodId;
	
	@Size(max = 50)
	@NotBlank
	private String foodName;
	
	@Min(value = 10)
	@NotNull
	private float foodCost;
	
	@Enumerated(EnumType.STRING)// for converting enum to string in DB
	@Column(length = 20)// specifying column length
	private FOODTYPE foodType;
	
	@Size(max = 200)
	@NotBlank
	private String description;
	
	@Size(max = 100)
	private String foodPic;
	
}