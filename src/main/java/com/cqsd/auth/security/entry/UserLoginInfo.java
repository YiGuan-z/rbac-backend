package com.cqsd.auth.security.entry;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author caseycheng
 * @date 2022/11/4-19:45
 **/
@Getter
@Setter
public class UserLoginInfo implements UserDetails {
	private String username;
	private String password;
	private Long id;
	@JsonIgnore
	private List<GrantedAuthority> authorities;
	private List<String> expressions;
	
	public UserLoginInfo(String username, String password, List<String> authorities) {
		this.username = username;
		this.password = password;
		if (!CollectionUtils.isEmpty(authorities)){
			this.authorities = authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
		}
	}
	
	public void setAuthorities(List<String> authorities) {
		if (CollectionUtils.isEmpty(authorities)){
			this.authorities=Collections.emptyList();
			return;
		}
		this.authorities=authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}
	
	/**
	 * Returns the authorities granted to the user. Cannot return <code>null</code>.
	 *
	 * @return the authorities, sorted by natural key (never <code>null</code>)
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	/**
	 * Returns the password used to authenticate the user.
	 *
	 * @return the password
	 */
	@Override
	public String getPassword() {
		return password;
	}
	
	/**
	 * Returns the username used to authenticate the user. Cannot return
	 * <code>null</code>.
	 *
	 * @return the username (never <code>null</code>)
	 */
	@Override
	public String getUsername() {
		return username;
	}
	
	/**
	 * Indicates whether the user's account has expired. An expired account cannot be
	 * authenticated.
	 *
	 * @return <code>true</code> if the user's account is valid (ie non-expired),
	 * <code>false</code> if no longer valid (ie expired)
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	/**
	 * Indicates whether the user is locked or unlocked. A locked user cannot be
	 * authenticated.
	 *
	 * @return <code>true</code> if the user is not locked, <code>false</code> otherwise
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	/**
	 * Indicates whether the user's credentials (password) has expired. Expired
	 * credentials prevent authentication.
	 *
	 * @return <code>true</code> if the user's credentials are valid (ie non-expired),
	 * <code>false</code> if no longer valid (ie expired)
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	/**
	 * Indicates whether the user is enabled or disabled. A disabled user cannot be
	 * authenticated.
	 *
	 * @return <code>true</code> if the user is enabled, <code>false</code> otherwise
	 */
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	
}
