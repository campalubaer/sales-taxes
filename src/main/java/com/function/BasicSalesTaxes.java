package com.function;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.EnumUtils;

import com.entity.Item;
import com.entity.Type;

public class BasicSalesTaxes extends AbstractTaxes {
	
	protected static BigDecimal RATE = new BigDecimal("0.1");
	
	public static List<Item> calculatingTaxes(List<Item> cartItems){
		if(cartItems != null){
			cartItems.stream()
					 .filter(ci -> ci.getType() == null || !EnumUtils.isValidEnum(Type.class, ci.getType().toString()))
					 .forEach(ci -> {
						 ci.setSalesTax(round(ci.getSalesTax().add(ci.getPrice().multiply(new BigDecimal(ci.getAmount())).multiply(RATE))));
			});
		}
		return cartItems;
	}

}
