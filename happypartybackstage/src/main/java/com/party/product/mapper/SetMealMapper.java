package com.party.product.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.party.domain.SetMeal;
/**
 * 
 * @author Caizhf
 * @date 2017年6月26日下午4:20:58
 * @version v.0.1
 * @email 1115054416@qq.com 
 *
 * <p>Description: 聚会套餐</p>
 *
 */
public interface SetMealMapper {

	int countList(@Param("categoryId")Integer categoryId);

	List<SetMeal> list(@Param("categoryId")Integer categoryId, 
			@Param("startIndex")int startIndex, @Param("pageSize")int pageSize);

	SetMeal findById(int smid);

	Integer save(SetMeal meal);

	Integer deleteById(int smid);

	Integer updateById(SetMeal meal);

	Integer updateSequenceById(@Param("smid")int smid, @Param("sequence")int sequence);
	
//	Integer removeCategoryForeignKey(@Param("ids")int[] ids,@Param("state")Integer state);
}
