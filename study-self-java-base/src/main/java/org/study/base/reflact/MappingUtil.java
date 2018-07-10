package org.study.base.reflact;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class MappingUtil {

	public static Object convertValue(final Object value, final Class<?> targetType) {
		if (targetType.equals(value.getClass())) {
			// Types match
			return value;
		}

		// Types do not match, perform conversion where needed
		if (targetType.equals(String.class)) {
			return value.toString();
		} else if (targetType.equals(BigDecimal.class)) {
			return new BigDecimal(value.toString());
		} else if (targetType.equals(Date.class)) {
			return convertToDate(value);
		} else if (targetType.equals(LocalDateTime.class)) {
			return LocalDateTime.parse(value.toString());
		} else if (targetType.equals(LocalDate.class)) {
			return LocalDate.parse(value.toString());

			// Use Number intermediary where possible
		} else if (targetType.equals(Integer.class)) {
			if (value instanceof Number) {
				return Integer.valueOf(((Number) value).intValue());
			} else {
				return Integer.valueOf(value.toString());
			}
		} else if (targetType.equals(Long.class)) {
			if (value instanceof Number) {
				return Long.valueOf(((Number) value).longValue());
			} else {
				return Long.valueOf(value.toString());
			}
		} else if (targetType.equals(Double.class)) {
			if (value instanceof Number) {
				return Double.valueOf(((Number) value).doubleValue());
			} else {
				return Double.valueOf(value.toString());
			}
		} else if (targetType.equals(Float.class)) {
			if (value instanceof Number) {
				return Float.valueOf(((Number) value).floatValue());
			} else {
				return Float.valueOf(value.toString());
			}
		} else {
			return value;
		}
	}

	private static Date convertToDate(Object value) {
		Date date = null;
		if (value != null && !"".equals(value)) {
			if (value instanceof Long) {
				date = new Date(((Long) value).longValue());

			} else if (value instanceof String) {
				String val = (String) value;
				int dateLength = String.valueOf(Long.MAX_VALUE).length();
				if (dateLength == val.length()) {
					date = new Date(Long.valueOf(val).longValue());
				} else {
					date = getDate(val);
				}
			} else {
				date = (Date) value;
			}
		}
		return date;
	}

	private static Date getDate(String val) {
		try {
			// Use ES internal converter
			return new Date(val);
		} catch (Throwable t) {
		}
		return null;
	}

}
