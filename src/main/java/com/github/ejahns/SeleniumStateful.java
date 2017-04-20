package com.github.ejahns;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.ArrayUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import static com.github.ejahns.State.Purpose.*;

public abstract class SeleniumStateful extends StatefulClass implements SearchContext {

	private final SearchContext context;

	protected SeleniumStateful(SearchContext context) {
		this.context = context;
	}

	@Override
	public Node getState() throws Exception {
		root = new Node(clazz.toString(), null);
		beforeStateObtained();
		mapFieldsToState(getStateAnnotatedFields());
		mapMethodsToState(getStateAnnotatedMethods());
		root.unpack();
		afterStateObtained();
		return root;
	}

	private void mapFieldsToState(Collection<Field> fields) throws Exception {
		for (Field f : fields) {
			f.setAccessible(true);
			Serializable value = null;
			State annotation = f.getAnnotation(State.class);
			WebElementFunction webElementFunction = annotation.function().newInstance();
			Object fCast = f.get(this);
			if (fCast instanceof By) {
				By cast = (By) fCast;
				List<WebElement> elements = findElements(cast);
				if (ArrayUtils.contains(annotation.purpose(), PRESENCE)) {
					value = elements.size() > 0;
				}
				else {
					ArrayList<Serializable> results = new ArrayList<>();
					for (WebElement e : elements) {
						results.add(webElementFunction.apply(e));
					}
					value = results;
				}
			}
			else if (fCast instanceof WebElement) {
				value = webElementFunction.apply((WebElement) fCast);
			}
			else if (fCast instanceof Collection) {
				Collection<Serializable> results = (Collection<Serializable>) fCast.getClass().newInstance();
				for (Object o : (Collection) fCast) {
					if (!(o instanceof WebElement)) {
						throw new IllegalStateException();
					}
					results.add(webElementFunction.apply((WebElement) o));
				}
				if (results instanceof Serializable) {
					value = (Serializable) results;
				}
			}
			else {
				throw new IllegalStateException();
			}
			String key = annotation.key().equals("") ? f.toString() : annotation.key();
			root.addChild(new Node(key, value));
		}
	}

	private Collection<Field> getStateAnnotatedFields() {
		Field[] declaredFields = clazz.getDeclaredFields();
		Field[] fields = clazz.getFields();
		Set<Field> set = new HashSet<>();
		for (Field f : declaredFields) {
			if (f.isAnnotationPresent(State.class)) {
				set.add(f);
			}
		}
		for (Field f : fields) {
			if (f.isAnnotationPresent(State.class)) {
				set.add(f);
			}
		}
		return set;
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
