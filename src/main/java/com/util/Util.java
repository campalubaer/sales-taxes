package com.util;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.entity.Item;
import com.entity.Type;

public class Util {

	private static final String ITEM_DESCRIPTION_REGEX = "(\\d+)\\s((\\w+\\s)+)at\\s(\\d+.\\d+)";
	
	public static List<Item> parseConversion(List<String> list){
		List<Item> cartItems = new LinkedList<>();
		list.stream().forEach(l -> {
			try{
				Matcher m = parse(l);
				cartItems.add(new Item(m.group(2), new BigDecimal(m.group(4)), new Long(m.group(1)), m.group(2).contains("imported"), findType(m.group(2))));
			}catch (IllegalStateException e) {
				System.out.println("Error during parse function, item is ignored");
			}
		});
		return cartItems;
	}
	
	private static Matcher parse(String item){
		Pattern pattern = Pattern.compile(ITEM_DESCRIPTION_REGEX);
		Matcher matcher = pattern.matcher(item);
		matcher.find();
		return matcher;
	}
	
	private static Type findType(String name){
		if(name.contains("book")) 
			return Type.BOOK;
		if(name.contains("chocolate")
			|| name.contains("bread"))
			return Type.FOOD;
		if(name.contains("pills")
				|| name.contains("headache"))
			return Type.MEDICALPRODUCT;
		return null;
	}
	
	public static void printReceiptDetails(List<Item> cart){
		if(cart != null){
			cart.stream().forEach(i -> System.out.println(i.getAmount() + " " + i.getDescription() + ": " + i.getPrice().multiply(new BigDecimal(i.getAmount().toString())).add(i.getSalesTax())));
			System.out.println("Sales Taxes: " + cart.stream().map(e -> e.getSalesTax()).reduce(new BigDecimal(0), (x,y) -> x.add(y)));
			System.out.println("Total: " + cart.stream().map(e -> e.getPrice().multiply(new BigDecimal(e.getAmount().toString())).add(e.getSalesTax())).reduce(new BigDecimal(0), (x,y) -> x.add(y)));
			System.out.println(" -------------------------------------------------------- ");
		}else{
			System.out.println("--------------------------------------------------------");
			System.out.println("empty");
			System.out.println("--------------------------------------------------------");
		}
	}
}
