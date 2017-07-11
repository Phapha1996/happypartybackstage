package com.party.user.service.impl;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.party.domain.Admin;
import com.party.domain.User;

import com.party.exception.ExceptionMsgEnum;
import com.party.exception.ServiceException;
import com.party.tool.MD5;
import com.party.user.mapper.UserMapper;
import com.party.user.service.UserService;


@Service
public class UserServiceImpl implements UserService
{
	private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserMapper userMapper;
	
	@Value("${USER_ICON}")
	String USER_ICON;
	
	
	//更新商家角色
	public void updateAdmin(String account,String password,Integer aid)
	{
		if(StringUtils.isEmpty(account)||StringUtils.isEmpty(password)||aid==0)
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		password=MD5.toMD5(password);
		int status=userMapper.updateAdminDate(account, password, aid);
		if(status==0)
			throw new ServiceException(ExceptionMsgEnum.SERVER_ERROR_MSG);
	}
	
	//增加商家帐号
	@Transactional(propagation=Propagation.REQUIRED)
	public void addAdmin(Admin admin)
	{

		admin.setPassword(MD5.toMD5(admin.getPassword()));

		admin.setCdate(new java.sql.Date(new java.util.Date().getTime()));

		logger.info("增加商家中");
		int status=userMapper.insterAdmin(admin);
		if(status==0)
			throw new ServiceException("增加用户失败！");
		logger.info("增加商家成功");
		
		int statu=userMapper.insterAdminRole(admin.getAid(),2);
		if(statu==0)
			throw new ServiceException("增加商家权限失败！");
	}
	
	//查询所有商家信息
	public List<Admin> findAllAdmin(Integer page,Integer size)
	{
		if(page==0||size==0)
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		int start=size*(page-1);
		List<Admin> admin=userMapper.selectAllAdmin(start, size);
		if(admin.equals(null)||admin.size()==0)
			throw new ServiceException("获取所有商家信息失败！");
		return admin;
	}
	
	//获取单个商家信息（帐号）
	public Admin findByAccountAdmin(String account)
	{
		if(StringUtils.isEmpty(account))
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		Admin admin=userMapper.SelectByAccountUser(account);
		if(admin==null)
			throw new ServiceException(ExceptionMsgEnum.SERVER_DB_NOTEXIT);
		return admin;
	}
	
	//获取单个商家信息（id）
	public Admin findByIdAdmin(Integer aid)
	{
		if(aid==0)
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		Admin admin=userMapper.SelectByIdAdmin(aid);
		if(admin==null)
			throw new ServiceException(ExceptionMsgEnum.SERVER_DB_NOTEXIT);
		return admin;
	}
	
	//删除商家(id)
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteIdAdmin(Integer aid)
	{
		if(aid==0)
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		int state=userMapper.DeleteByIdAdminRole(aid);
		if(state==0)
			throw new ServiceException(ExceptionMsgEnum.SERVER_ERROR_MSG);
		int status=userMapper.DeleteByIdAdmin(aid);
		if(status==0)
			throw new ServiceException(ExceptionMsgEnum.SERVER_ERROR_MSG);
	}
	
	//更新商家数据(账号,密码,手机,邮箱)
	public void updateAdminDate(String account,String password,String phone,String email,Integer aid)
	{
		if(StringUtils.isEmpty(account)||StringUtils.isEmpty(password)||StringUtils.isEmpty(phone)||StringUtils.isEmpty(email)||aid==0)
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		password=MD5.toMD5(password);
		int status=userMapper.updateByIdAdminDate(account, password, phone, email, aid);
		if(status==0)
			throw new ServiceException(ExceptionMsgEnum.SERVER_ERROR_MSG);
	}
	
	//更新商家角色
	public void updateAdminRole(Integer rid,Integer aid)
	{
		if(rid==0||aid==0)
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		int status=userMapper.updateByIdAdminRole(rid, aid);
		if(status==0)
			throw new ServiceException(ExceptionMsgEnum.SERVER_ERROR_MSG);
	}
	
	//获取所有用户信息
	public List<User> findAllUser(Integer page,Integer size)
	{
		if(page==0||size==0)
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		int start=size*(page-1);
		List<User> user=userMapper.selectAllUser(start, size);
		if(user.equals(null)||user.size()==0)
			throw new ServiceException("获取所有用户信息失败！");
		return user;
	}
	
	
	//获取单个用户信息（邮箱,手机号）
	public User findByUsernameUser(String username)
	{
		if(StringUtils.isEmpty(username))
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		User user=userMapper.SelectByUsernameUser(username);
		if(user==null)
			throw new ServiceException(ExceptionMsgEnum.SERVER_DB_NOTEXIT);
		return user;
	}
	
	
	//获取单个用户信息（id）
	public User findByIdUser(Integer uid)
	{
		if(uid==0)
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		User user=userMapper.SelectByIdUser(uid);
		if(user==null)
			throw new ServiceException(ExceptionMsgEnum.SERVER_DB_NOTEXIT);
		return user;
	}
	
	//删除用户(id)
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteIdUser(Integer uid)
	{
		if(uid==0)
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		int state=userMapper.DeleteByIdUserRole(uid);
		if(state==0)
			throw new ServiceException(ExceptionMsgEnum.SERVER_ERROR_MSG);
		int status=userMapper.DeleteByIdUser(uid);
		if(status==0)
			throw new ServiceException(ExceptionMsgEnum.SERVER_ERROR_MSG);
	}
	
	//更新用户数据(姓名,用户名,密码,积分,是否激活)
	public void updateUserDate(String nickName,String userName,String password,Integer integral,Integer activate,Integer uid)
	{
		if(StringUtils.isEmpty(nickName)||StringUtils.isEmpty(userName)||StringUtils.isEmpty(password)||integral<0||activate<0||uid==0)
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		password=MD5.toMD5(password);
		int status=userMapper.updateByIdUserDate(nickName, userName, password, integral, activate, uid);
		if(status==0)
			throw new ServiceException(ExceptionMsgEnum.SERVER_ERROR_MSG);
	}
	
	//更新用户角色
	public void updateUserRole(Integer rid,Integer uid)
	{
		if(rid==0||uid==0)
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		int status=userMapper.updateByIdUserRole(rid, uid);
		if(status==0)
			throw new ServiceException(ExceptionMsgEnum.SERVER_ERROR_MSG);
	}


}
