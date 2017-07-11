package com.party.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.party.domain.Admin;
import com.party.domain.User;
import com.party.user.mapper.SysAdminMapper;

/**
 * 
 * @author Caizhf
 * @date 2017年5月22日下午8:31:16
 * @version v.0.1
 * @email 1115054416@qq.com 
 *
 * <p>Description: 自定义SpringSecurity UserDetailsService类</p>
 *
 */
@Service
public class MyAdminDetailsService implements UserDetailsService{
	@Autowired
	private SysAdminMapper mapper;
	private final Logger log = LoggerFactory.getLogger(MyAdminDetailsService.class);
	
	/**
	 * 把username返回回去
	 */
	@Override
	public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
		log.info("account:" + account + "------------------");
		Admin admin = mapper.findByAccount(account);
		if (admin == null)
			throw new BadCredentialsException("不存在这个管理员");
		log.info("account" + admin.getAccount() + "|" + "password"
				+ admin.getPassword());
		return new MyAdminDetails(admin);
	}

}
