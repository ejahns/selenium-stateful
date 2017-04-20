package com.github.ejahns;

import java.io.Serializable;

import org.openqa.selenium.WebElement;

public class WebElementTextFunction extends WebElementFunction {

	@Override
	public Serializable apply(WebElement element) {
		return element.getText();
	}
}
