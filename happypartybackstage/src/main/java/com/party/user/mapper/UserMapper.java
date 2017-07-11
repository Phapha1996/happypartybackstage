package com.party.user.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.party.domain.Admin;
import com.party.domain.User;


@Mapper
public interface UserMapper 
{
	//增加商家
	@Insert("INSERT INTO admin(aid,account,password,phone_number,email,cdate) VALUES(#{aid},#{account},#{password},#{phoneNumber},#{email},#{cdate})")
	@Options(useGeneratedKeys=true, keyProperty="aid")
	int insterAdmin(Admin admin);
	
	//增加商家角色关联
	@Insert("INSERT INTO admin_role(admin_id,role_id) VALUES(#{adminId},#{roleId})")
	int insterAdminRole(@Param("adminId")Integer adminId,@Param("roleId")Integer roleId);
	
	//查询所有商家信息
	@Select("select * from Admin where aid!=1 limit #{start},#{size}")
	 @Results(  
			    {  
	@Result(column = "phone_number", property = "phoneNumber")
			    }) 
	List<Admin> selectAllAdmin(@Param("start")Integer start,@Param("size")Integer size);
	
	//查询单个商家信息（帐号）
	@Select("select * from Admin where account=#{account}")
	 @Results(  
			    {  
	@Result(column = "phone_number", property = "phoneNumber")
			    }) 
	Admin SelectByAccountUser(@Param("account")String account);
	
	//查询单个商家信息（id）
	@Select("select * from Admin where aid=#{aid}")
	 @Results(  
			    {  
	@Result(column = "phone_number", property = "phoneNumber")
			    }) 
	Admin SelectByIdAdmin(@Param("aid")Integer aid);
	
	//删除商家(id)
	@Delete("delete from Admin where aid=#{aid}")
	int DeleteByIdAdmin(@Param("aid")Integer aid);
	
	//删除商家角色关联信息(id)
	@Delete("delete from admin_role where admin_id=#{aid}")
	int DeleteByIdAdminRole(@Param("aid")Integer aid);
	
	//更新商家数据(账号,密码,手机,邮箱)
	@Update("update Admin set account=#{account},password=#{password},phone_number=#{phone},email=#{email} where aid=#{aid}")
	int updateByIdAdminDate(@Param("account")String account,@Param("password")String password,@Param("phone")String phone,@Param("email")String email,@Param("aid")Integer aid);
	
	//更新管理员数据(账号,密码)
	@Update("update Admin set account=#{account},password=#{password} where aid=#{aid}")
	int updateAdminDate(@Param("account")String account,@Param("password")String password,@Param("aid")Integer aid);
	
	//更新商家角色
	@Update("update admin_role set role_id=#{rid} where admin_id=#{aid}")
	int updateByIdAdminRole(@Param("rid")Integer rid,@Param("aid")Integer aid);

	
	//查询所有用户信息
	@Select("select * from user limit #{start},#{size}")
	List<User> selectAllUser(@Param("start")Integer start,@Param("size")Integer size);

	//查询单个用户信息（邮箱,手机号）
	@Select("select * from user where username=#{username}")
	User SelectByUsernameUser(@Param("username")String username);
	
	//查询单个用户信息（id）
	@Select("select * from user where id=#{uid}")
	User SelectByIdUser(@Param("uid")Integer uid);
	
	//删除用户(id)
	@Delete("delete from user where id=#{uid}")
	int DeleteByIdUser(@Param("uid")Integer uid);
	
	//删除用户角色关联信息(id)
	@Delete("delete from user_role where user_id=#{uid}")
	int DeleteByIdUserRole(@Param("uid")Integer uid);
	
	//更新用户数据(姓名,用户名,密码,积分,是否激活)
	@Update("update user set nickname=#{nickName},username=#{userName},password=#{password},integral=#{integral},is_activate=#{activate} where id=#{uid}")
	int updateByIdUserDate(@Param("nickName")String nickName,@Param("userName")String userName,@Param("password")String password,@Param("integral")Integer integral,@Param("activate")Integer activate,@Param("uid")Integer uid);
	
	//更新用户角色
	@Update("update user_role set role_id=#{rid} where user_id=#{uid}")
	int updateByIdUserRole(@Param("rid")Integer rid,@Param("uid")Integer uid);

}
