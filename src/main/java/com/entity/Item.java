package com.entity;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Item {
	private String description;
	private BigDecimal price;
	private Long amount;
	private boolean imported;
	private Type type;	
	private BigDecimal salesTax = new BigDecimal(0);
	
	public Item(String description, BigDecimal price, Long amount, boolean imported, Type type){
		this.description = description;
		this.price = price;
		this.amount = amount;
		this.imported = imported;
		this.type = type;
	}
}