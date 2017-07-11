package com.party.security;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.party.tool.MD5;


/**
 * 
 * @author Caizhf
 * @date 2017年5月22日下午8:45:12
 * @version v.0.1
 * @email 1115054416@qq.com 
 *
 * <p>Description: </p>
 *
 */
@Component
public class MyAuthenticationProvider implements AuthenticationProvider{
	@Autowired
	private MyAdminDetailsService service;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String account = authentication.getName();
		String pwd = (String)authentication.getCredentials();
		UserDetails admin = service.loadUserByUsername(account);
		//加密过程在这里体现
		String password = MD5.toMD5(pwd);
		if (!password.equals(admin.getPassword()))
            throw new BadCredentialsException("密码错误.");
		
		 Collection<? extends GrantedAuthority> authorities = admin.getAuthorities();
	        return new UsernamePasswordAuthenticationToken(admin, admin.getPassword(), authorities);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return true;
	}
}
