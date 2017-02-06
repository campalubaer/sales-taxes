package com.function;

import java.math.BigDecimal;
import java.util.List;

import com.entity.Item;

public class ImportedSalesTaxes extends AbstractTaxes {
	
	protected static BigDecimal RATE = new BigDecimal("0.05");
	
	public static List<Item> calculatingTaxes(List<Item> cartItems){
		if(cartItems != null){
			cartItems.stream()
					 .filter(ci -> ci.isImported())
					 .forEach(ci -> {
						 ci.setSalesTax(round(ci.getSalesTax().add(ci.getPrice().multiply(new BigDecimal(ci.getAmount())).multiply(RATE))));
			});
		}
		return cartItems;
	}
}
