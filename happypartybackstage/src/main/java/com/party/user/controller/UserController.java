package com.party.user.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.party.domain.Admin;
import com.party.domain.User;
import com.party.dto.Response;
import com.party.user.service.impl.UserServiceImpl;


@RestController
@RequestMapping("/user")
public class UserController {
	private final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserServiceImpl  userService;
	
	@Autowired
	HttpServletResponse response;
	@Autowired
	HttpServletRequest request;
	
	@Value("${USER_ICON}")
	String USER_ICON;
	

	//更新管理员数据(用户名,密码,)
	@RequestMapping(value="upAdmin")
	public Response upAdmin(String account,String password,Integer aid)
	{
		userService.updateAdmin(account, password, aid);
		return Response.success();
	}
	
	
	//获取所有用户信息
	@RequestMapping(value="getAllUser")
	public Response getAllUser(Integer page,Integer size)
	{
		List<User> user=userService.findAllUser(page, size);
		return Response.success(user);
	}
	
	//获取单个用户信息（邮箱,手机号）
	@RequestMapping(value="getUsernameUser")
	public Response getUsernameUser(String username)
	{
		User user=userService.findByUsernameUser(username);
		return Response.success(user);
	}

	//获取单个用户信息（id）
	@RequestMapping(value="getIdUser")
	public Response getIdUser(Integer uid)
	{
		User user=userService.findByIdUser(uid);
		return Response.success(user);
	}
	
	
	//删除用户(id)
	@RequestMapping(value="deleteUser")
	public Response deleteUser(Integer uid)
	{
		userService.deleteIdUser(uid);
		return Response.success();
	}


	//更新用户数据(姓名,用户名,密码,积分,是否激活)
	@RequestMapping(value="upUserDate")
	public Response upUserDate(String nickName,String userName,String password,Integer integral,Integer activate,Integer uid)
	{
		userService.updateUserDate(nickName, userName, password, integral, activate, uid);
		return Response.success();
	}

	
	//更新帐号角色(1:普通用户   2:商家用户   3:管理员)
	@RequestMapping(value="upUserRole")
	public Response upUserRole(Integer rid,Integer uid)
	{
		userService.updateUserRole(rid, uid);
		return Response.success();
	}
	
	
	//增加商家帐号
	@RequestMapping(value="addAdminAccount")
	public Response addAdminAccount(Admin admin)
	{
		userService.addAdmin(admin);
		return Response.success();
	}
	
	//获取所有商家信息
	@RequestMapping(value="getAllAdmin")
	public Response getAllAdmin(Integer page,Integer size)
	{
		List<Admin> admin=userService.findAllAdmin(page, size);
		return Response.success(admin);
	}
		
	//获取单个商家信息（帐号）
	@RequestMapping(value="getAccountUser")
	public Response getAccountUser(String account)
	{
		Admin admin=userService.findByAccountAdmin(account);
		return Response.success(admin);
	}
	
	//获取单个商家信息（id）
	@RequestMapping(value="getIdAdmin")
	public Response getIdAdmin(Integer aid)
	{
		Admin admin=userService.findByIdAdmin(aid);
		return Response.success(admin);
	}
	
	//删除商家(id)
	@RequestMapping(value="deleteAdmin")
	public Response deleteAdmin(Integer aid)
	{
		userService.deleteIdAdmin(aid);
		return Response.success();
	}
	
	//更新用户数据(姓名,用户名,密码,积分,是否激活)
	@RequestMapping(value="upAdminDate")
	public Response upAdminDate(String account,String password,String phone,String email,Integer aid)
	{
		userService.updateAdminDate(account, password, phone, email, aid);
		return Response.success();
	}
	
	//更新商家帐号角色(1:普通用户   2:商家用户   3:管理员)
	@RequestMapping(value="upAdminRole")
	public Response upAdminRole(Integer rid,Integer aid)
	{
		userService.updateAdminRole(rid, aid);
		return Response.success();
	}
	
	
}
