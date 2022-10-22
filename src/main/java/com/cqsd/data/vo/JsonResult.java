package com.cqsd.data.vo;

import java.util.Objects;

import static com.cqsd.data.vo.ResultEnum.*;
/**
 * @param code    Server status code
 * @param message Server return message
 * @param data    Server return Data
 * @param <T>     need Data Type
 */
public record JsonResult<T>(Integer code, String message, T data) {
	
	public JsonResult {
	}
	
	public static <T> JsonResult<T> of(Integer code, String message, T data) {
		return new JsonResult<>(code, message, data);
	}
	
	public static <T> JsonResult<T> success(Integer code, String message) {
		return of(code, message, null);
	}
	
	public static <T> JsonResult<T> success(Integer code, T data) {
		return of(code, SUCCESS_MESSAGE, data);
	}
	
	//	public static <T> JsonResult<T> success(String message){
//		return success(SUCCESS_CODE,message);
//	}
	public static <T> JsonResult<T> success(T data) {
		return of(SUCCESS_CODE, SUCCESS_MESSAGE, data);
	}
	
	public static <T> JsonResult<T> success() {
		return of(SUCCESS_CODE, SUCCESS_MESSAGE, null);
	}
	
	public static <T> JsonResult<T> code(Integer code) {
		return of(code, null, null);
	}
	
	public static <T> JsonResult<T> failed(Integer code, String message) {
		return of(code, message, null);
	}
	
	public static <T> JsonResult<T> failed(Integer code, T data) {
		return of(code, FAILED_MESSAGE, data);
	}
	
	public static <T> JsonResult<T> failed(String message) {
		return success(FAILED_CODE, message);
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (obj == null || obj.getClass() != this.getClass()) return false;
		var that = (JsonResult) obj;
		return Objects.equals(this.code, that.code) &&
				Objects.equals(this.message, that.message) &&
				Objects.equals(this.data, that.data);
	}
	
	@Override
	public String toString() {
		return "JsonResult[" +
				"code=" + code + ", " +
				"message=" + message + ", " +
				"data=" + data + ']';
	}
	
}
