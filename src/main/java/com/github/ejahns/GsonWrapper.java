package com.github.ejahns;

import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

class GsonWrapper {

	private static final Gson SELENIUM_STATEFUL_GSON =
		new GsonBuilder()
			.registerTypeHierarchyAdapter(SeleniumStateful.class, new SeleniumStatefulSerializer())
			.create();

	private static final Gson EXPOSE_ONLY_GSON =
		new GsonBuilder()
			.excludeFieldsWithoutExposeAnnotation()
			.create();

	static JsonElement seleniumStatefultoJsonElement(SeleniumStateful seleniumStateful) {
		return SELENIUM_STATEFUL_GSON.toJsonTree(seleniumStateful);
	}

	static Map seleniumStatefulToMap(SeleniumStateful seleniumStateful) {
		return SELENIUM_STATEFUL_GSON.fromJson(seleniumStatefultoJsonElement(seleniumStateful), Map.class);
	}

	static JsonElement toJsonElement(Object o) {
		return EXPOSE_ONLY_GSON.toJsonTree(o);
	}

	static Map toMap(Object o) {
		return EXPOSE_ONLY_GSON.fromJson(toJsonElement(o), Map.class);
	}
}
