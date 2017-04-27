package com.github.ejahns;

import org.junit.Test;

public class Tests {

	@Test
	public void test() {
		SeleniumStatefulObject seleniumStatefulObject = new SeleniumStatefulObject(new FakeSearchContext());
		JsonObject jsonObject = new JsonObject();
		assert (seleniumStatefulObject.isEquivalent(jsonObject));
	}
}
