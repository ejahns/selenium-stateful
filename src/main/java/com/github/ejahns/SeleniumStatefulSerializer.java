package com.github.ejahns;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class SeleniumStatefulSerializer implements JsonSerializer<SeleniumStateful> {

	@Override
	public JsonElement serialize(
		SeleniumStateful seleniumStateful, Type type, JsonSerializationContext jsonSerializationContext
	) {
		JsonObject obj = new JsonObject();
		seleniumStateful.beforeStateObtained();
		try {
			mapFieldsToState(seleniumStateful, obj, jsonSerializationContext, getStateAnnotatedFields(seleniumStateful));
			mapMethodsToState(seleniumStateful, obj, jsonSerializationContext, getStateAnnotatedMethods(seleniumStateful));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		seleniumStateful.afterStateObtained();
		return obj;
	}

	private void mapMethodsToState(
		SeleniumStateful seleniumStateful,
		JsonObject obj,
		JsonSerializationContext context,
		Collection<Method> methods
	) throws Exception {
		for (Method m : methods) {
			m.setAccessible(true);
			State annotation = m.getAnnotation(State.class);
			Object value = m.invoke(seleniumStateful);
			String key = annotation.key().equals("") ? m.toString() : annotation.key();
			obj.add(key, context.serialize(value));
		}
	}

	private void mapFieldsToState(
		SeleniumStateful seleniumStateful,
		JsonObject obj,
		JsonSerializationContext context,
		Collection<Field> fields
	) throws Exception {
		for (Field f : fields) {
			f.setAccessible(true);
			Serializable value = null;
			StateSelector annotation = f.getAnnotation(StateSelector.class);
			WebElementFunction webElementFunction = annotation.function().newInstance();
			Object fCast = f.get(this);
			if (fCast instanceof By) {
				By cast = (By) fCast;
				List<WebElement> elements = seleniumStateful.findElements(cast);
				if (annotation.purpose().equals(StateSelector.Purpose.PRESENCE)) {
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
			obj.add(key, context.serialize(value));
		}
	}

	private Collection<Method> getStateAnnotatedMethods(SeleniumStateful seleniumStateful) {
		Method[] declaredMethods = seleniumStateful.getClass().getDeclaredMethods();
		Method[] methods = seleniumStateful.getClass().getMethods();
		Set<Method> set = new HashSet<>();
		for (Method m : declaredMethods) {
			if (m.isAnnotationPresent(State.class)) {
				set.add(m);
			}
		}
		for (Method m : methods) {
			if (m.isAnnotationPresent(State.class)) {
				set.add(m);
			}
		}
		return set;
	}

	private Collection<Field> getStateAnnotatedFields(SeleniumStateful seleniumStateful) {
		Field[] declaredFields = seleniumStateful.getClass().getDeclaredFields();
		Field[] fields = seleniumStateful.getClass().getFields();
		Set<Field> set = new HashSet<>();
		for (Field f : declaredFields) {
			if (f.isAnnotationPresent(StateSelector.class)) {
				set.add(f);
			}
		}
		for (Field f : fields) {
			if (f.isAnnotationPresent(StateSelector.class)) {
				set.add(f);
			}
		}
		return set;
	}
}
