package com.github.ejahns;

public interface CustomStateEquivalence<T> {

	boolean apply(Node node, T t) throws Exception;
}