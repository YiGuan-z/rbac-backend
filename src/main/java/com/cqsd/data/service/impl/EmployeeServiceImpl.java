package com.cqsd.data.service.impl;

import com.cqsd.data.entry.Employee;
import com.cqsd.auth.entry.UserLogin;
import com.cqsd.data.mapper.EmployeeMapper;
import com.cqsd.data.qo.QueryObject;
import com.cqsd.data.service.EmployeeService;
import com.cqsd.data.service.base.BaseServiceImpl;
import com.cqsd.data.utils.AutoCopy;
import com.cqsd.data.utils.SecurityUtils;
import com.cqsd.data.utils.TokenManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class EmployeeServiceImpl extends BaseServiceImpl<Employee, QueryObject, EmployeeMapper> implements EmployeeService {
	public EmployeeServiceImpl(EmployeeMapper mapper) {
		super(mapper);
	}
	
	@Override
	public void save(Employee record) {
		record.setPassword(SecurityUtils.encodePassword(record.getPassword()));
		super.save(record);
	}
	
	@Override
	@Deprecated
	public String login(String username, String password) throws LoginExeption {
		Employee employee = mapper.selectByUserName(username);
		Objects.requireNonNull(employee, "用户不存在");
		if (employee.getPassword().equals(password)) {
			final var token = TokenManager.createToken();
			//往新的token管理器添加用户对象
			final var login = AutoCopy.of(employee, UserLogin.class);
			assert login != null;
			final var expression = getExpressionByEmpId(employee.getId());
			login.setAuthorities(expression);
			login.setExpressions(expression);
			TokenManager.addUser(token, login);
			return token;
		}
		throw new LoginExeption("用户密码错误");
		
	}
	
	@Override
	public void deleteByIds(List<Long> ids) {
		mapper.deleteByBeathId(ids);
	}
	
	@Override
	public List<String> getExpressionByEmpId(Long id) {
//		select menu.expression
//		from sys_employee emp
//		right join sys_employee_role emp_role on emp.id = emp_role.employee_id
//		left join sys_role_menu srm on emp_role.role_id = srm.role_id
//		join sys_menus menu on menu.id = srm.menu_id
//		where emp.id = 1
//		and menu.status = 0
//		and menu.type = 2
		//role
//		select roles.*
//		from sys_roles roles
//		right join sys_employee_role ser on roles.id = ser.role_id
//		where employee_id=2;
		List<String> ret;
		ret = mapper.selectExpression(id);
		List<String> roles = mapper.selectRole(id);
		final var iterator = roles.listIterator();
		while (iterator.hasNext()) {
			final var next = iterator.next();
			final var role = toRole(next);
			iterator.set(role);
		}
		ret.addAll(roles);
		return ret;
	}
	
	/**
	 * Locates the user based on the username. In the actual implementation, the search
	 * may possibly be case sensitive, or case insensitive depending on how the
	 * implementation instance is configured. In this case, the <code>UserDetails</code>
	 * object that comes back may have a username that is of a different case than what
	 * was actually requested..
	 *
	 * @param username the username identifying the user whose data is required.
	 * @return a fully populated user record (never <code>null</code>)
	 * @throws UsernameNotFoundException if the user could not be found or the user has no
	 *                                   GrantedAuthority
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final var employee = mapper.selectByUserName(username);
		final var login = AutoCopy.of(employee, UserLogin.class);
		
		if (employee.getAdmin()) {
			Objects.requireNonNull(login).setAuthorities(getExpressionByEmpId(employee.getId()));
			return login;
		} else if (Objects.nonNull(employee)) {
			
			Objects.requireNonNull(login).setAuthorities(getExpressionByEmpId(employee.getId()));
			return login;
		} else {
			throw new UsernameNotFoundException("世界咀嚼了你，你不存在于这个世界上");
		}
	}
	
	public static String toRole(String role) {
		return "ROLE_" + role;
	}
}
