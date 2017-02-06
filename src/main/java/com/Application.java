package com;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.entity.Item;
import com.function.BasicSalesTaxes;
import com.function.ImportedSalesTaxes;
import com.util.Util;

public class Application {
	
	public static void main(String[] args) {
		List<List<String>> carts = new LinkedList<>();
		carts.add(Arrays.asList("1 book at 12.49", "1 music CD at 14.99", "1 chocolate bar at 0.85"));
		carts.add(Arrays.asList("1 imported box of chocolates at 10.00", "1 imported bottle of perfume at 47.50"));
		carts.add(Arrays.asList("1 imported bottle of perfume at 27.99", "1 bottle of perfume at 18.99", "1 packet of headache pills at 9.75", "1 box of imported chocolates at 11.25"));
		
		
		carts.stream().forEach(i -> {
			List<Item> cart = Util.parseConversion(i);
			BasicSalesTaxes.calculatingTaxes(cart);
			ImportedSalesTaxes.calculatingTaxes(cart);
			Util.printReceiptDetails(cart);
		});
	}

}
