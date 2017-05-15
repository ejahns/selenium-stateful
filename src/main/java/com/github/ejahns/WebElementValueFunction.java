package com.github.ejahns;

import java.io.Serializable;

import org.openqa.selenium.WebElement;

public class WebElementValueFunction extends WebElementFunction {

	@Override
	public Serializable apply(WebElement webElement) {
		return webElement.getAttribute("value");
	}
}
