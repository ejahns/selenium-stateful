package com.github.ejahns;

import java.util.List;

import org.openqa.selenium.SearchContext;

import com.google.common.collect.ImmutableList;

public class SeleniumStatefulObject extends SeleniumStateful {

	protected SeleniumStatefulObject(SearchContext context) {
		super(context);
	}

	@State(value = "number")
	private int getNumber() {
		return 314;
	}

	@State(value = "list")
	private List getList() {
		return ImmutableList.of("One", "Two", "Three", "Four");
	}

	@State(value = "text")
	private String getText() {
		return "Some Text";
	}
}
