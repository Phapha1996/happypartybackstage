package com.party.product.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.party.domain.MealCategory;

/**
 * 
 * @author Caizhf
 * @date 2017年7月5日下午3:35:47
 * @version v.0.1
 * @email 1115054416@qq.com 
 *
 * <p>Description: </p>
 *
 */
public interface MealCategoryMapper {

	Integer save(MealCategory mc);

	MealCategory findById(int mcid);

	List<MealCategory> list(@Param("startIndex")int startIndex, @Param("pageSize")int pageSize);

	int countList();

	Integer update(MealCategory mc);
	
	Integer updateSequenceById(@Param("mcid")int mcid,@Param("sequence")int sequence);

	Integer deleteById(int mcid);
	
}
