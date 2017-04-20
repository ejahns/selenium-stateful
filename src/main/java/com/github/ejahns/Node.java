package com.github.ejahns;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.common.collect.ImmutableSet;

public class Node {

	private String key;
	private Object value;
	private boolean ordered = false;
	private List<Node> children = new ArrayList<>();

	public Node(String key, Object value) {
		this.key = key;
		this.value = value;
	}

	void addChild(Node n) {
		children.add(n);
	}

	void unpack() throws Exception {
		if (value instanceof Collection) {
			Collection cast = (Collection) value;
			if (allStateful(cast)) {
				ordered = true;
				for (Object o : cast) {
					children.add(((Stateful) o).getState());
				}
				this.value = null;
			}
		}
		if (value instanceof Stateful) {
			children.add(((Stateful) value).getState());
			value = null;
		}
		for (Node n : children) {
			n.unpack();
		}
	}

	private boolean allStateful(Collection<Object> objects) {
		for (Object o : objects) {
			if (!(o instanceof Stateful)) {
				return false;
			}
		}
		return true;
	}

	public <T> boolean isEquivalent(T t, CustomStateEquivalence<T> equivalence) throws Exception {
		return equivalence.apply(this, t);
	}

	@Override
	public boolean equals(Object obj) {
		if (null == obj) {
			return false;
		}
		if (!(obj instanceof Node)) {
			return false;
		}
		Node that = (Node) obj;
		if (!this.key.equals(that.key)) {
			return false;
		}
		if (null != this.value && null != that.value) {
			if (!this.value.equals(that.value)) {
				return false;
			}
		}
		if ((null == this.value && null != that.value) || (null != this.value && null == that.value)) {
			return false;
		}
		if ((null == this.children && null != that.children) || (null != this.children && null == that.children)) {
			return false;
		}
		if (null != this.children && null != that.value) {
			if (ordered) {
				return this.children.equals(that.children);
			}
			else {
				return ImmutableSet.copyOf(this.children).equals(ImmutableSet.copyOf(that.children));
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + key.hashCode();
		if (null != value) {
			result = 31 * result + value.hashCode();
		}
		result = 31 * result + children.hashCode();
		return result;
	}

	public String getKey() {
		return key;
	}

	public Object getValue() {
		return value;
	}
}
