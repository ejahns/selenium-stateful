package com.github.ejahns;

import java.util.List;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.FindsByClassName;
import org.openqa.selenium.internal.FindsByCssSelector;
import org.openqa.selenium.internal.FindsById;
import org.openqa.selenium.internal.FindsByLinkText;
import org.openqa.selenium.internal.FindsByName;
import org.openqa.selenium.internal.FindsByTagName;
import org.openqa.selenium.internal.FindsByXPath;
import org.openqa.selenium.internal.HasIdentity;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.internal.WrapsDriver;
import org.openqa.selenium.internal.WrapsElement;
import org.openqa.selenium.remote.RemoteWebElement;

public class StatefulRemoteWebElement extends SeleniumStateful implements WrapsElement, WebElement, FindsByLinkText, FindsById, FindsByName,
	FindsByTagName, FindsByClassName, FindsByCssSelector,
	FindsByXPath, WrapsDriver, Locatable, HasIdentity {

	private final RemoteWebElement delegate;

	protected StatefulRemoteWebElement(RemoteWebElement delegate) {
		super(delegate);
		this.delegate = delegate;
	}

	@Override
	public RemoteWebElement getWrappedElement() {
		return delegate;
	}

	@Override
	public WebElement findElementByClassName(String using) {
		return delegate.findElementByClassName(using);
	}

	@Override
	public List<WebElement> findElementsByClassName(String using) {
		return delegate.findElementsByClassName(using);
	}

	@Override
	public WebElement findElementByCssSelector(String using) {
		return delegate.findElementByCssSelector(using);
	}

	@Override
	public List<WebElement> findElementsByCssSelector(String using) {
		return delegate.findElementsByCssSelector(using);
	}

	@Override
	public WebElement findElementById(String using) {
		return delegate.findElementById(using);
	}

	@Override
	public List<WebElement> findElementsById(String using) {
		return delegate.findElementsById(using);
	}

	@Override
	public WebElement findElementByLinkText(String using) {
		return delegate.findElementByLinkText(using);
	}

	@Override
	public List<WebElement> findElementsByLinkText(String using) {
		return delegate.findElementsByLinkText(using);
	}

	@Override
	public WebElement findElementByPartialLinkText(String using) {
		return delegate.findElementByPartialLinkText(using);
	}

	@Override
	public List<WebElement> findElementsByPartialLinkText(String using) {
		return delegate.findElementsByPartialLinkText(using);
	}

	@Override
	public WebElement findElementByName(String using) {
		return delegate.findElementByName(using);
	}

	@Override
	public List<WebElement> findElementsByName(String using) {
		return delegate.findElementsByName(using);
	}

	@Override
	public WebElement findElementByTagName(String using) {
		return delegate.findElementByTagName(using);
	}

	@Override
	public List<WebElement> findElementsByTagName(String using) {
		return delegate.findElementsByTagName(using);
	}

	@Override
	public WebElement findElementByXPath(String using) {
		return delegate.findElementByXPath(using);
	}

	@Override
	public List<WebElement> findElementsByXPath(String using) {
		return delegate.findElementsByXPath(using);
	}

	@Override
	public String getId() {
		return delegate.getId();
	}

	@Override
	public Coordinates getCoordinates() {
		return delegate.getCoordinates();
	}

	@Override
	public void click() {
		delegate.click();
	}

	@Override
	public void submit() {
		delegate.submit();
	}

	@Override
	public void sendKeys(CharSequence... keysToSend) {
		delegate.sendKeys(keysToSend);
	}

	@Override
	public void clear() {
		delegate.clear();
	}

	@Override
	public String getTagName() {
		return delegate.getTagName();
	}

	@Override
	public String getAttribute(String name) {
		return delegate.getAttribute(name);
	}

	@Override
	public boolean isSelected() {
		return delegate.isSelected();
	}

	@Override
	public boolean isEnabled() {
		return delegate.isEnabled();
	}

	@Override
	public String getText() {
		return delegate.getText();
	}

	@Override
	public boolean isDisplayed() {
		return delegate.isDisplayed();
	}

	@Override
	public Point getLocation() {
		return delegate.getLocation();
	}

	@Override
	public Dimension getSize() {
		return delegate.getSize();
	}

	@Override
	public Rectangle getRect() {
		return delegate.getRect();
	}

	@Override
	public String getCssValue(String propertyName) {
		return delegate.getCssValue(propertyName);
	}

	@Override
	public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
		return delegate.getScreenshotAs(target);
	}

	@Override
	public WebDriver getWrappedDriver() {
		return delegate.getWrappedDriver();
	}
}
