package com.party.orders.service;

import java.util.List;

import com.party.domain.Discuss;
import com.party.dto.Page;
/**
 * 
 * @author Caizhf
 * @date 2017年6月15日下午2:33:27
 * @version v.0.1
 * @email 1115054416@qq.com 
 *
 * <p>Description: 评价业务层接口</p>
 *
 */
public interface DiscussService {

	/**
	 * 功能：查询指定商品下面的所有评价,分页显示
	 * @param productType
	 * @param productId
	 * @return
	 */
	Page listByProduct(String productType, int productId, int pageNum, int pageSize);

	/**
	 * 根据id删除
	 * @param did
	 */
	void deleteById(int did);

	/**
	 * 按评价内容模糊查询
	 * @param key
	 * @param pageSize 
	 * @param pageNum 
	 * @return
	 */
	Page findByLikeContent(String key, int pageNum, int pageSize);

	/**
	 * 修改评价
	 * @param discuss
	 * @return
	 */
	void update(Discuss discuss);

	/**
	 * 根据id获取评价
	 * @param did
	 * @return
	 */
	Discuss findById(int did);

}
