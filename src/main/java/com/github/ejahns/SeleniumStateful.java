package com.github.ejahns;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import com.google.gson.JsonElement;

public abstract class SeleniumStateful implements SearchContext {

	private final SearchContext context;

	protected SeleniumStateful(SearchContext context) {
		this.context = context;
	}

	public JsonElement toJsonElement() {
		return GsonWrapper.seleniumStatefultoJsonElement(this);
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
