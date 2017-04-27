package com.github.ejahns;

import java.util.List;

import org.openqa.selenium.SearchContext;

import com.google.common.collect.ImmutableList;

public class SeleniumStatefulObject extends SeleniumStateful {

	protected SeleniumStatefulObject(SearchContext context) {
		super(context);
	}

	@State(key = "number")
	private int getNumber() {
		return 314;
	}

	@State(key = "list")
	private List getList() {
		return ImmutableList.of("One", "Two", "Three", "Four");
	}

	@State(key = "text")
	private String getText() {
		return "Some Text";
	}
}
