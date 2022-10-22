package com.cqsd.data.annotation.hander;

import com.cqsd.data.annotation.enummodel.Level;

import java.util.Date;

public record LoggerModel(Level level, String message, Date time, Class<?> clazz) {
	public static LoggerModel of(Level level, String message, Date time, Class<?> clazz) {
		return new LoggerModel(level, message, time, clazz);
	}
}
