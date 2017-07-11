package com.party.product.service;


import java.util.List;

import com.party.domain.MealCategory;
import com.party.domain.SetMeal;
import com.party.dto.Page;
/**
 * 
 * @author Caizhf
 * @date 2017年6月26日下午4:05:11
 * @version v.0.1
 * @email 1115054416@qq.com 
 *
 * <p>Description: 套餐</p>
 *
 */
public interface SetMealService {

	Page list(int pageNum, Integer categoryId, int pageSize);

	SetMeal findById(int smid);

	int save(SetMeal meal);

	void deleteById(int smid);

	void updateById(SetMeal setMeal);

	void toTop(int smid, int sequence);

	int saveCategory(MealCategory mc);

	void deleteCategoryById(int mcid);

	MealCategory findCategoryById(int mcid);

	Page listCategory(int pageNum, int pageSize);

	void updateCategory(MealCategory mc);

}
