package com.party.product.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.party.domain.Site;

/**
 * 
 * @author Caizhf
 * @date 2017年6月20日下午5:56:31
 * @version v.0.1
 * @email 1115054416@qq.com 
 *
 * <p>Description: </p>
 *
 */
public interface SiteMapper {

	Integer save(Site site);

	Integer deleteById(int siid);

	Integer updateById(Site site);

	Site findById(int siid);

	int countLikeByTitle(String key);
	
	/**
	 * 按标题模糊查询
	 * @param key
	 * @param startindex
	 * @param pageSize
	 * @return
	 */
	List<Site> listLikeByTitle(@Param("key")String key, 
			@Param("startIndex")int startIndex, @Param("pageSize")int pageSize);

	int countList(@Param("adminId")Integer adminId);
	
	/**
	 * 查询所有的
	 * @param startindex
	 * @param pageSize
	 * @param adminId 
	 * @return
	 */
	List<Site> list(@Param("startIndex")int startIndex, 
			@Param("pageSize")int pageSize, @Param("adminId")Integer adminId);
	
	/**
	 * 置顶功能
	 * @param siid
	 * @param sequence
	 * @return
	 */
	Integer updateSequenceById(@Param("siid")int siid, @Param("sequence")int sequence);

}
