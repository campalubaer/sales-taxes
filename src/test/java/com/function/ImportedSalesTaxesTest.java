package com.function;

import static org.junit.Assert.assertEquals;

import com.entity.Item;
import com.function.ImportedSalesTaxes;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class ImportedSalesTaxesTest {

	@Test
	public void calculateSalesTaxesTest(){
		List<Item> cartItems = Arrays.asList(new Item("imported box of chocolates", new BigDecimal("10.00"), 1L, true, null));
		ImportedSalesTaxes.calculatingTaxes(cartItems);
		assertEquals(cartItems.get(0).getSalesTax().compareTo(new BigDecimal("0.50")), 0);
	}
	
	@Test
	public void calculateSalesTaxesForNotImportedItemTest(){
		List<Item> cartItems = Arrays.asList(new Item("box of chocolates", new BigDecimal("10.00"), 1L, false, null));
		ImportedSalesTaxes.calculatingTaxes(cartItems);
		assertEquals(cartItems.get(0).getSalesTax().compareTo(new BigDecimal("0.00")), 0);
	}
	
	@Test
	public void calculateSalesTaxesRoundedForNotImportedItemTest(){
		List<Item> cartItems = Arrays.asList(new Item("imported bottle of perfume", new BigDecimal("47.50"), 1L, true, null));
		ImportedSalesTaxes.calculatingTaxes(cartItems);
		assertEquals(cartItems.get(0).getSalesTax().compareTo(new BigDecimal("2.40")), 0);
	}
}
