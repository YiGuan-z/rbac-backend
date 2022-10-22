package com.cqsd.data.utils;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

abstract public class TokenManager {
	public static final String TOKEN_NAME="X-Token";
	public static Map<String,UserInfo> TOKEN_MAP=new ConcurrentHashMap<>(100);
	public static String createToken(){
		return UUID.randomUUID().toString().replace("-","");
	}
	public static void addUser(String token,UserInfo userInfo){
		TOKEN_MAP.put(token, userInfo);
	}
	public static UserInfo getUser(String token){
		return TOKEN_MAP.get(token);
	}
	public static UserInfo remove(String token){
		return TOKEN_MAP.remove(token);
	}
	public static boolean include(String key){
		return TOKEN_MAP.containsKey(key);
	}
	public static int size(){
		return TOKEN_MAP.size();
	}
}
