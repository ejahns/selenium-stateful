package com.github.ejahns;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.gson.annotations.Expose;

/**
 * Created by ejahns on 4/27/2017.
 */
public class JsonObject2 {

	@Expose
	private List list = ImmutableList.of("One", "Two", "Three", "Four");

	@Expose
	private int number = 314;

	@Expose
	private String text = "Some Other Text";

}
