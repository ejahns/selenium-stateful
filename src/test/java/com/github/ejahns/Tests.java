package com.github.ejahns;

import org.junit.Test;

public class Tests {

	@Test
	public void test1() {
		SeleniumStatefulObject seleniumStatefulObject = new SeleniumStatefulObject(new FakeSearchContext());
		JsonObject1 jsonObject1 = new JsonObject1();
		assert (seleniumStatefulObject.isEquivalent(jsonObject1));
	}

	@Test
	public void test2() {
		SeleniumStatefulObject seleniumStatefulObject = new SeleniumStatefulObject(new FakeSearchContext());
		JsonObject2 jsonObject2 = new JsonObject2();
		assert (!seleniumStatefulObject.isEquivalent(jsonObject2));
	}

	@Test
	public void test3() {
		SeleniumStatefulObject seleniumStatefulObject = new SeleniumStatefulObject(new FakeSearchContext());
		JsonObject3 jsonObject3 = new JsonObject3();
		assert (seleniumStatefulObject.isEquivalent(jsonObject3));
	}
}
