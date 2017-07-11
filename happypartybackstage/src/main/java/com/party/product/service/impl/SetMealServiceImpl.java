package com.party.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.party.domain.MealCategory;
import com.party.domain.SetMeal;
import com.party.dto.Page;
import com.party.exception.ExceptionMsgEnum;
import com.party.exception.ServiceException;
import com.party.product.mapper.MealCategoryMapper;
import com.party.product.mapper.SetMealMapper;
import com.party.product.service.SetMealService;
/**
 * 
 * @author Caizhf
 * @date 2017年6月26日下午4:13:20
 * @version v.0.1
 * @email 1115054416@qq.com 
 *
 * <p>Description: 聚会套餐</p>
 *
 */
@Service
public class SetMealServiceImpl implements SetMealService{
	
	@Autowired
	private SetMealMapper mapper;
	@Autowired
	private MealCategoryMapper categoryMapper;

	
	@Override
	@Transactional(readOnly=true)
	public Page list(int pageNum, Integer categoryId, int pageSize) {
		int totleRecord = mapper.countList(categoryId);
		Page page = new Page(totleRecord,pageNum,pageSize);
		List<SetMeal> list = mapper.list(categoryId,page.getStartindex(),pageSize);
		page.setList(list);
		return page;
	}

	@Override
	public SetMeal findById(int smid) {
		SetMeal setMeal = mapper.findById(smid);
		return setMeal;
	}

	@Override
	public int save(SetMeal meal) {
		if(mapper.save(meal)==null)
			throw new ServiceException(ExceptionMsgEnum.SERVER_ERROR_MSG);
		return meal.getSmid();
	}

	@Override
	public void deleteById(int smid) {
		if(mapper.deleteById(smid)==null)
			throw new ServiceException(ExceptionMsgEnum.SERVER_ERROR_MSG);
	}

	@Override
	public void updateById(SetMeal meal) {
		if(mapper.updateById(meal)==null)
			throw new ServiceException(ExceptionMsgEnum.SERVER_ERROR_MSG);
	}

	@Override
	public void toTop(int smid, int sequence) {
		if(mapper.updateSequenceById(smid,sequence)==null)
			throw new ServiceException(ExceptionMsgEnum.SERVER_ERROR_MSG);
	}

	//------------------------------------------------------Category Start---------------------------------------//
	
	@Override
	public int saveCategory(MealCategory mc) {
		if(mc==null)
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		if(categoryMapper.save(mc)==null)
			throw new ServiceException(ExceptionMsgEnum.SERVER_ERROR_MSG);
		return mc.getMcid();
	}

	@Override
	public void deleteCategoryById(int mcid) {
		categoryMapper.deleteById(mcid);
	}

	@Override
	public MealCategory findCategoryById(int mcid) {
		if(mcid==0)
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		MealCategory mc = categoryMapper.findById(mcid);
		return mc;
	}

	@Override
	public Page listCategory(int pageNum, int pageSize) {
		int totleRecord = categoryMapper.countList();
		Page page = new Page(totleRecord,pageNum,pageSize);
		List<MealCategory> list = categoryMapper.list(page.getStartindex(),pageSize);
		page.setList(list);
		return page;
	}

	@Override
	public void updateCategory(MealCategory mc) {
		if(mc==null)
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		if(categoryMapper.update(mc)==null)
			throw new ServiceException(ExceptionMsgEnum.SERVER_ERROR_MSG);
	}

}
