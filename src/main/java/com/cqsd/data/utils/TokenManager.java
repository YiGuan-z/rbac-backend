package com.cqsd.data.utils;

import com.cqsd.auth.security.entry.UserLoginInfo;

import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author caseycheng
 * @date 2022/11/4-20:02
 **/
abstract public class TokenManager {
	public static final String TOKEN_NAME = "X-Token";
	public static Map<String, UserLoginInfo> TOKEN_MAP = new ConcurrentHashMap<>(100);
	
	public static String createToken() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	public static void addUser(String token, UserLoginInfo userInfo) {
		TOKEN_MAP.put(token, userInfo);
	}
	
	public static UserLoginInfo getUser(String token) {
		return TOKEN_MAP.get(token);
	}
	
	public static void remove(String token) {
		TOKEN_MAP.remove(token);
	}
	
	public static boolean containsKey(String key) {
		return TOKEN_MAP.containsKey(key);
	}
	
	public static int size() {
		return TOKEN_MAP.size();
	}
	
	public static boolean include(String token) {
		return TOKEN_MAP.containsKey(token);
	}
	public static boolean includeAll(String... tokens){
		return Arrays.stream(tokens).allMatch(TokenManager::include);
	}
}
