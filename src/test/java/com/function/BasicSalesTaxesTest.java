package com.function;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.entity.Item;
import com.entity.Type;

public class BasicSalesTaxesTest {

	@Test
	public void calculateSalesTaxesWithOnlyExemptTaxesItemsTest(){
		List<Item> cartItems = Arrays.asList(new Item("book", new BigDecimal("12.29"), 1L, false, Type.BOOK));
		BasicSalesTaxes.calculatingTaxes(cartItems);
		assertEquals(cartItems.get(0).getSalesTax().compareTo(new BigDecimal("0.00")), 0);
	}
	
	@Test
	public void calculateSalesTaxesTest(){
		List<Item> cartItems = Arrays.asList(new Item("music CD", new BigDecimal("14.99"), 1L, false, null));
		BasicSalesTaxes.calculatingTaxes(cartItems);
		assertEquals(cartItems.get(0).getSalesTax().compareTo(new BigDecimal("1.50")), 0);
	}
}
