package com.function;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

public class AbstractTaxesTest {

	@Test
	public void roundingDecimalLessThen5() {
		Assert.assertEquals(AbstractTaxes.round(new BigDecimal("11.81")).compareTo(new BigDecimal("11.85")) == 0, true);
	}
	
	@Test
	public void roundingDecimalGreaterThen5() {
		Assert.assertEquals(AbstractTaxes.round(new BigDecimal("11.86")).compareTo(new BigDecimal("11.90")) == 0, true);
	}
	
	@Test
	public void roundingThirdDecimalGreaterThen5() {
		Assert.assertEquals(AbstractTaxes.round(new BigDecimal("11.866")).compareTo(new BigDecimal("11.90")) == 0, true);
	}

}
