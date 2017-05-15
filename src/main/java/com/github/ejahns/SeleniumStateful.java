package com.github.ejahns;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializer;

public abstract class SeleniumStateful implements SearchContext {

	private final SearchContext context;

	protected SeleniumStateful(SearchContext context) {
		this.context = context;
	}

	public JsonElement toJsonElement() {
		return GsonWrapper.seleniumStatefultoJsonElement(this);
	}

	public boolean isEquivalent(Object o) {
		return toJsonElement().equals(GsonWrapper.toJsonElement(o));
	}

	public <S, T extends JsonSerializer<S>> boolean isEquivalent(S s, T t) {
		Gson gson = new GsonBuilder().registerTypeAdapter(s.getClass(), t).create();
		return toJsonElement().equals(gson.toJsonTree(s));
	}

	public MapDifference compare(Object o) {
		Map left = toMap();
		Map right = GsonWrapper.toMap(o);
		return Maps.difference(left, right);
	}

	private Map toMap() {
		return GsonWrapper.seleniumStatefulToMap(this);
	}

	/**
	 * Method to override if subclass must perform and cleanup after obtaining its state
	 */
	public void beforeStateObtained() {

	}

	/**
	 * Method to override if subclass must perform any cleanup after obtaining its state
	 */
	public void afterStateObtained() {

	}

	@Override
	public List<WebElement> findElements(By by) {
		return context.findElements(by);
	}

	@Override
	public WebElement findElement(By by) {
		return context.findElement(by);
	}
}
