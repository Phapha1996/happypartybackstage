package com.party.user.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.party.domain.Admin;
import com.party.domain.User;

public interface UserService 
{
	//更新商家角色
	public void updateAdmin(String account,String password,Integer aid);
	//增加商家帐号
	public void addAdmin(Admin admin);
	
	//查询所有商家信息
	public List<Admin> findAllAdmin(Integer page,Integer size);
	
	//获取单个商家信息（帐号）
	public Admin findByAccountAdmin(String account);
	
	//获取单个用户信息（id）
	public Admin findByIdAdmin(Integer aid);
	
	//删除商家(id)
	public void deleteIdAdmin(Integer aid);
	
	//更新商家数据(账号,密码,手机,邮箱)
	public void updateAdminDate(String account,String password,String phone,String email,Integer aid);
	
	//更新商家角色
	public void updateAdminRole(Integer rid,Integer aid);
	
	//获取所有用户信息
	public List<User> findAllUser(Integer page,Integer size);
	
	//获取单个用户信息（邮箱,手机号）
	public User findByUsernameUser(String username);
	
	//获取单个用户信息（id）
	public User findByIdUser(Integer uid);
	
	//删除用户(id)
	public void deleteIdUser(Integer uid);
	
	//更新用户数据(姓名,用户名,密码,积分,是否激活)
	public void updateUserDate(String nickName,String userName,String password,Integer integral,Integer activate,Integer uid);
	
	//更新用户角色
	public void updateUserRole(Integer rid,Integer uid);
}
