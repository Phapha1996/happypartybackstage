package com.party.decoration.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.party.domain.Decoration;
import com.party.domain.DecorationProduct;
/**
 * 
 * @author Caizhf
 * @date 2017年6月21日下午4:18:57
 * @version v.0.1
 * @email 1115054416@qq.com 
 *
 * <p>Description: 场地布置</p>
 *
 */
public interface DecorationMapper {

	Integer save(Decoration dec);

	Decoration findById(int did);

	int countListDecorationByThemeId(int thid);

	/**
	 * 根据布置主题的id查询所有布置
	 * 
	 * @param thid
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	List<Decoration> listDecorationByThemeId(@Param("thid") int thid, @Param("startIndex") Integer startIndex,
			@Param("pageSize") Integer pageSize);

	int countList(@Param("adminId")Integer adminId);

	List<Decoration> list(@Param("startIndex") Integer startIndex, 
			@Param("pageSize") Integer pageSize, @Param("adminId")Integer adminId);

	int countListDecorationLikeByTitle(@Param("key")String key, @Param("adminId")Integer adminId);

	/**
	 * 根据title模糊查询所有的布置
	 * 
	 * @param key
	 * @param startIndex
	 * @param pageSize
	 * @param adminId 
	 * @return
	 */
	List<Decoration> listDecorationLikeByTitle(@Param("key") String key, @Param("startIndex") Integer startIndex,
			@Param("pageSize") Integer pageSize, @Param("adminId")Integer adminId);

	/**
	 * 把布置中的主题外键去除
	 * 
	 * @param did
	 * @param thid
	 * @return
	 */
	Integer removeThemeForeignKeyById(@Param("did") int did, @Param("thid") Integer thid);

	Integer deleteById(int did);

	Integer updateSequenceById(@Param("did") int did, @Param("sequence")int sequence);

	Integer updateById(Decoration d);
}
