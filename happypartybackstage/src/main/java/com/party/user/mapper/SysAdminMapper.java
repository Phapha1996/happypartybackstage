package com.party.user.mapper;


import com.party.domain.Admin;
/**
 * 
 * @author Caizhf
 * @date 2017年6月16日下午12:19:11
 * @version v.0.1
 * @email 1115054416@qq.com 
 *
 * <p>Description: </p>
 *
 */
public interface SysAdminMapper {

	Admin findByAccount(String account);

}
