package com.cqsd.auth.service;

public interface SecuityService {
 boolean hasAuthority(String expression);
 boolean hasRole(String role);

}
