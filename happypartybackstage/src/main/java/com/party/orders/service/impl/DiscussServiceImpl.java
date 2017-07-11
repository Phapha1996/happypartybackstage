package com.party.orders.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.party.domain.Discuss;
import com.party.dto.Page;
import com.party.exception.ExceptionMsgEnum;
import com.party.exception.ServiceException;
import com.party.orders.mapper.DiscussMapper;
import com.party.orders.service.DiscussService;

/**
 * 
 * @author Caizhf
 * @date 2017年6月15日下午2:34:21
 * @version v.0.1
 * @email 1115054416@qq.com 
 *
 * <p>Description: 评价业务实现层</p>
 *
 */
@Service
public class DiscussServiceImpl implements DiscussService{
	
	@Autowired
	private DiscussMapper discussMapper;
	
	/*
	 * 查询某个产品下面所有的评价，分页显示
	 */
	@Override
	public Page listByProduct(String productType, int productId, int pageNum, int pageSize) {
		int totleRecord = discussMapper.countListByProductIdAndProductType(productType,productId);
		Page page = null;
		if(pageNum==0)
			page = new Page(totleRecord,1);
		else
			//如果不为空，那么就按照想看的页码进行
			page = new Page(totleRecord, pageNum,pageSize);
		List<Discuss> discusses = discussMapper.listByProductIdAndProductType(productType, productId,page.getStartindex(),pageSize);
		page.setList(discusses);
		return page;
	}
	
	/*
	 * 根据id删除
	 */
	@Override
	public void deleteById(int did) {
		if(discussMapper.deleteById(did)==null)
			throw new ServiceException(ExceptionMsgEnum.SERVER_ERROR_MSG);
	}

	@Override
	public Page findByLikeContent(String key,int pageNum,int pageSize) {
		int totleRecord = discussMapper.countListByLikeContent(key);
		Page page = null;
		if(pageNum==0)
			page = new Page(totleRecord,1);
		else
			//如果不为空，那么就按照想看的页码进行
			page = new Page(totleRecord, pageNum,pageSize);
		List<Discuss> discusses = discussMapper.listByLikeContent(key,page.getStartindex(),pageSize);
		page.setList(discusses);
		return page;
	}

	@Override
	public void update(Discuss discuss) {
		discuss.setCtime(new Date());
		if(discussMapper.updateById(discuss)==null)
			throw new ServiceException(ExceptionMsgEnum.SERVER_ERROR_MSG);
	}

	/*
	 * 需要把商品、映射进来
	 */
	@Override
	public Discuss findById(int did) {
		Discuss discuss = discussMapper.findById(did);
		if(discuss==null)
			throw new ServiceException(ExceptionMsgEnum.SERVER_DB_NOTEXIT);
		return discuss;
	}

}
