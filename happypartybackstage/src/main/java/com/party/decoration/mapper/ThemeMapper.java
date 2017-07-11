package com.party.decoration.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.party.domain.Theme;
/**
 * 
 * @author Caizhf
 * @date 2017年6月21日下午2:07:28
 * @version v.0.1
 * @email 1115054416@qq.com 
 *
 * <p>Description: 布置主题</p>
 *
 */
public interface ThemeMapper {

	Integer save(Theme theme);

	int countList();

	List<Theme> list(@Param("startIndex")int startIndex, @Param("pageSize")int pageSize);

	int countLikeByName(String key);

	List<Theme> listLikeByName(@Param("key")String key, 
			@Param("startIndex")int startIndex, @Param("pageSize")int pageSize);

	Integer deleteById(int thid);

	Theme findById(int thid);

	Integer updateById(Theme theme);

	Integer updateSequenceById(@Param("thid")int thid, @Param("sequence")int sequence);

}
