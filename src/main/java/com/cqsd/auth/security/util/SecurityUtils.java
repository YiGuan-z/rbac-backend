package com.cqsd.auth.security.util;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;

/**
 * @author caseycheng
 * @date 2022/11/3-17:21
 **/
public class SecurityUtils {
	private static final PasswordEncoder encoder = new BCryptPasswordEncoder();
	
	public static UserDetails getLoginUser() {
		try {
			return (UserDetails) getContext()
					.getAuthentication()
					.getPrincipal();
		} catch (Exception e) {
			throw new RuntimeException("用户未登陆");
		}
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends UserDetails> T getLoginUser(Class<T> clazz) {
		try {
			return (T) getContext()
					.getAuthentication()
					.getPrincipal();
		} catch (Exception e) {
			throw new RuntimeException("用户未登陆");
		}
	}
	
	public static <T extends UserDetails> UsernamePasswordAuthenticationToken ofUsernamePasswordAuthenticationToken(T principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
		return UsernamePasswordAuthenticationToken.authenticated(principal, credentials, authorities);
	}
	
	public static String encodePassword(String password) {
		return encoder.encode(password);
	}
	
	public static SecurityContext getContext() {
		return SecurityContextHolder.getContext();
	}
	
	public static boolean checkPassword(String rawPwd, String encodepwd) {
		return encoder.matches(rawPwd, encodepwd);
	}
	
	public static void setAuthentication(Authentication usernamePasswordAuthenticationToken) {
		getContext().setAuthentication(usernamePasswordAuthenticationToken);
	}
}
