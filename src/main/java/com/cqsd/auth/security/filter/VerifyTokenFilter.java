package com.cqsd.auth.security.filter;

import com.cqsd.data.service.EmployeeService;
import com.cqsd.auth.security.util.SecurityUtils;
import com.cqsd.data.utils.TokenManager;
import com.cqsd.data.vo.JsonResult;
import com.cqsd.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author caseycheng
 * @date 2022/11/4-20:42
 **/
@Component
public class VerifyTokenFilter extends HttpFilter {
	private static final List<String> Whitelist = List.of("/api/v1/employee/login","/api/v1/employee/qr");
	@Autowired
	private EmployeeService service;
	
	/**
	 * The <code>doFilter</code> method of the Filter is called by the container
	 * each time a request/response pair is passed through the chain due to a
	 * client request for a resource at the end of the chain. The FilterChain
	 * passed in to this method allows the Filter to pass on the request and
	 * response to the next entity in the chain.
	 * <p>
	 * A typical implementation of this method would follow the following
	 * pattern:- <br>
	 * 1. Examine the request<br>
	 * 2. Optionally wrap the request object with a custom implementation to
	 * filter content or headers for input filtering <br>
	 * 3. Optionally wrap the response object with a custom implementation to
	 * filter content or headers for output filtering <br>
	 * 4. a) <strong>Either</strong> invoke the next entity in the chain using
	 * the FilterChain object (<code>chain.doFilter()</code>), <br>
	 * 4. b) <strong>or</strong> not pass on the request/response pair to the
	 * next entity in the filter chain to block the request processing<br>
	 * 5. Directly set headers on the response after invocation of the next
	 * entity in the filter chain.
	 * <p>
	 * This default implementation simply calls the next filter in the filter
	 * chain.
	 *
	 * @param request  The request to process
	 * @param response The response associated with the request
	 * @param chain    Provides access to the next filter in the chain for this
	 *                 filter to pass the request and response to for further
	 *                 processing
	 * @throws IOException      if an I/O error occurs during this filter's
	 *                          processing of the request
	 * @throws ServletException if the processing fails for any other reason
	 */
	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		final var uri = request.getRequestURI();
		if (Whitelist.contains(uri)) {
			chain.doFilter(request, response);
			return;
		}
		final var token = request.getHeader(TokenManager.TOKEN_NAME);
		if (StringUtils.hasLength(token)) {
			if (TokenManager.containsKey(token)) {
				final var user = TokenManager.getUser(token);
				final var expression = service.getExpressionByEmpId(user.getId());
				//????????????
				final var authorityList = expression.parallelStream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
				final var authenticationToken = SecurityUtils.ofUsernamePasswordAuthenticationToken(user, user.getPassword(), authorityList);
				SecurityUtils.setAuthentication(authenticationToken);
				chain.doFilter(request, response);
				return;
			}
		}
		final var failed = JsonResult.failed(401, "????????????,??????????????????");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		JsonUtil.writeJson(response.getWriter(), failed);
	}
}
