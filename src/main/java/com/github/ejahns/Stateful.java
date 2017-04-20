package com.github.ejahns;

import java.io.Serializable;

public interface Stateful extends Serializable {

	Node getState() throws Exception;
}
