package com.github.ejahns;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public abstract class StatefulClass implements Stateful {

	protected final Class<? extends StatefulClass> clazz;
	protected Node root;

	protected StatefulClass() {
		this.clazz = this.getClass();
	}

	@Override
	public Node getState() throws Exception {
		root = new Node(clazz.toString(), null);
		beforeStateObtained();
		mapMethodsToState(getStateAnnotatedMethods());
		root.unpack();
		afterStateObtained();
		return root;
	}

	protected void mapMethodsToState(Collection<Method> methods) throws Exception {
		for (Method m : methods) {
			m.setAccessible(true);
			State annotation = m.getAnnotation(State.class);
			Object value = m.invoke(this);
			String key = annotation.key().equals("") ? m.toString() : annotation.key();
			root.addChild(new Node(key, value));
		}
	}

	protected Collection<Method> getStateAnnotatedMethods() {
		Method[] declaredMethods = clazz.getDeclaredMethods();
		Method[] methods = clazz.getMethods();
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

}
