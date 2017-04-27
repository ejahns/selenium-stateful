package com.github.ejahns;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.gson.annotations.Expose;

public class JsonObject {

	@Expose
	private List list = ImmutableList.of("One", "Two", "Three", "Four");

	@Expose
	private int number = 314;

	@Expose
	private String text = "Some Text";
}
