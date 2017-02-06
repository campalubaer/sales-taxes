package com.function;

import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class AbstractTaxes {
	
	public static BigDecimal ROUND_FACTOR = new BigDecimal("0.05");
	
	public static BigDecimal round(BigDecimal value) {
		BigDecimal divided = value.divide(ROUND_FACTOR, 0, RoundingMode.UP);
		BigDecimal result = divided.multiply(ROUND_FACTOR);
		return result;
	}
}
