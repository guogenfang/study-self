package org.study.base.collection.map;

import java.util.Map;
import java.util.Objects;

/**
 * [简要描述]：
 * 
 * @author ggf 2018年7月26日
 */
public class NodeDemo implements Map.Entry<Integer, String> {

	final int hash;
	
	final Integer key;
	String value;
	NodeDemo next;

	NodeDemo(int hash, Integer key, String value, NodeDemo next) {
		this.hash = hash;
		this.key = key;
		this.value = value;
		this.next = next;
	}

	public final Integer getKey() {
		return key;
	}

	public final String getValue() {
		return value;
	}

	public final String toString() {
		return key + "=" + value;
	}

	public final int hashCode() {
		return Objects.hashCode(key) ^ Objects.hashCode(value);
	}

	public final String setValue(String newValue) {
		String oldValue = value;
		value = newValue;
		return oldValue;
	}

	public final boolean equals(Object o) {
		if (o == this)
			return true;
		if (o instanceof Map.Entry) {
			Map.Entry<?, ?> e = (Map.Entry<?, ?>) o;
			if (Objects.equals(key, e.getKey()) && Objects.equals(value, e.getValue()))
				return true;
		}
		return false;
	}

}
