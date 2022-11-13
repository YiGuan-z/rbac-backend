package com.cqsd.data.utils;

import com.cqsd.auth.security.entry.UserLoginInfo;
import com.cqsd.net.entry.TokenInfo;
import com.cqsd.util.AutoCopy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author caseycheng
 * @date 2022/11/4-20:02
 **/
abstract public class TokenManager {
	public static final String TOKEN_NAME = "X-Token";
	private static final Map<String, UserLoginInfo> TOKEN_MAP = new ConcurrentHashMap<>(100);
	private static final Map<Long, TokenInfo> LOGGE_IN=new ConcurrentHashMap<>(100);
	
	/**
	 * 创建一个待验证用户
	 * @param code 用户识别码
	 * @param tokenInfo 待验证用户信息
	 */
	public static void createVerified(Long code,TokenInfo tokenInfo){
		LOGGE_IN.put(code,tokenInfo);
	}
	
	/**
	 * 获取一个待验证用户
	 * @param code 用户识别码
	 * @return 待验证用户信息
	 */
	public static TokenInfo getTokenInfo(Long code){
		return LOGGE_IN.get(code);
	}
	
	/**
	 * 使用了这个方法后，待验证用户会被转化为已验证用户并从待验证用户中移除
	 * @param code 唯一识别码
	 * @return 转化后的用户，转化后待验证会变成已验证
	 */
	public static UserLoginInfo tokenInfoToUserLoginInfo(Long code){
		final var tokenInfo = LOGGE_IN.remove(code);
		return AutoCopy.of(tokenInfo, UserLoginInfo.class);
	}
	
	
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
