package com.util;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.entity.Item;
import com.entity.Type;
import com.function.BasicSalesTaxes;
import com.function.ImportedSalesTaxes;

public class UtilTest {

	@Test
	public void convertImportedAndFoodItemTest() {
		String item = "1 imported box of chocolates at 10.00";
		List<Item> i = Util.parseConversion(Arrays.asList(item));
		assertEquals(i.get(0).getDescription(), "imported box of chocolates ");
		assertEquals(i.get(0).getAmount().compareTo(1L) == 0, true);
		assertEquals(i.get(0).getPrice().compareTo(new BigDecimal("10.00")) == 0, true);
		assertEquals(i.get(0).isImported(), true);
		assertEquals(i.get(0).getType().equals(Type.FOOD), true);
	}
	
	@Test
	public void convertMedicalItemTest() {
		String item = "1 packet of headache pills at 9.75";
		List<Item> i = Util.parseConversion(Arrays.asList(item));
		assertEquals(i.get(0).getDescription(), "packet of headache pills ");
		assertEquals(i.get(0).getAmount().compareTo(1L) == 0, true);
		assertEquals(i.get(0).getPrice().compareTo(new BigDecimal("9.75")) == 0, true);
		assertEquals(i.get(0).isImported(), false);
		assertEquals(i.get(0).getType().equals(Type.MEDICALPRODUCT), true);
	}
	
	@Test
	public void convertCartWithTwoItemsTest() {
		List<String> items = Arrays.asList("1 packet of headache pills at 9.75", "1 imported box of chocolates at 10.00");
		List<Item> i = Util.parseConversion(items);
		assertEquals(i.size(), 2);
	}
	
	@Test
	public void tryConvertItemWithWrogFormatTest() {
		List<String> items = Arrays.asList("1 packet of headache pills 9.75");
		List<Item> i = Util.parseConversion(items);
		assertEquals(i.size(), 0);
	}
	
	@Test
	public void printReceiptDetailsForEmptyCartTest() {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        List<Item> cart = null;
        BasicSalesTaxes.calculatingTaxes(cart);
		ImportedSalesTaxes.calculatingTaxes(cart);
		Util.printReceiptDetails(cart);

        assertEquals("--------------------------------------------------------"+System.getProperty("line.separator")+"empty"+System.getProperty("line.separator")+"--------------------------------------------------------"+System.getProperty("line.separator"), outContent.toString());
    }
	
	@Test
	public void printReceiptDetailsForValidCartTest() {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        List<Item> cart = Arrays.asList(new Item("imported box of chocolates", new BigDecimal("10.00"), 1L, true, Type.FOOD),
        								new Item("imported bottle of perfume", new BigDecimal("47.50"), 1L, true, null));
        BasicSalesTaxes.calculatingTaxes(cart);
		ImportedSalesTaxes.calculatingTaxes(cart);
		Util.printReceiptDetails(cart);

        assertEquals(
        		"1 imported box of chocolates: 10.50" + System.getProperty("line.separator") +
        		"1 imported bottle of perfume: 54.65" + System.getProperty("line.separator") +
        		"Sales Taxes: 7.65" + System.getProperty("line.separator") +
        		"Total: 65.15" + System.getProperty("line.separator") +
        		" -------------------------------------------------------- " + System.getProperty("line.separator"), outContent.toString());
    }
}


