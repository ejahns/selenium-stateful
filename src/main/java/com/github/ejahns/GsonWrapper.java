package com.github.ejahns;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

class GsonWrapper {

	private static final Gson GSON =
		new GsonBuilder()
			.registerTypeHierarchyAdapter(SeleniumStateful.class, new SeleniumStatefulSerializer())
			.create();

	static JsonElement seleniumStatefultoJsonElement(SeleniumStateful seleniumStateful) {
		return GSON.toJsonTree(seleniumStateful);
	}
}
