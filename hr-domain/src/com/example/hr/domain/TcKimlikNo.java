package com.example.hr.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// Value Object
//  i) has no identity
// ii) immutable
public final class TcKimlikNo {
	private final static Map<String,TcKimlikNo> cache=new HashMap<>();
	private final String value;

	private TcKimlikNo(String value) {
		this.value = value;
	}

	public static TcKimlikNo valueOf(String value) {
		if (!isValid(value)) 
			throw new IllegalArgumentException(
					"This is not a valid identity no!");
		var tcKimlikNo = cache.get(value);
		if (Objects.isNull(tcKimlikNo)) {
			tcKimlikNo = new TcKimlikNo(value);
			cache.put(value, tcKimlikNo);
		}
		return tcKimlikNo;
	}

	private static boolean isValid(String value) {
		return true;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "TcKimlikNo [value=" + value + "]";
	}
	
}
















