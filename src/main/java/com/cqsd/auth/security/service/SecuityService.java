package com.cqsd.auth.security.service;

public interface SecuityService {
 boolean hasAuthority(String expression);
 boolean hasRole(String role);

}
