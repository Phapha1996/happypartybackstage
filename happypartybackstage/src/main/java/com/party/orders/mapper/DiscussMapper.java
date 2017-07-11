package com.party.orders.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.party.domain.Discuss;

/**
 * 
 * @author Caizhf
 * @date 2017年6月15日下午2:38:05
 * @version v.0.1
 * @email 1115054416@qq.com 
 *
 * <p>Description: 评价数据访问接口 </p>
 *
 */
public interface DiscussMapper {

	
	Integer countListByProductIdAndProductType(@Param("productType")String productType, 
			@Param("productId")int productId);

	/**
	 * 查询某个产品下面所有的评价
	 * @param productType
	 * @param productId
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	List<Discuss> listByProductIdAndProductType(@Param("productType")String productType, 
			@Param("productId")int productId,@Param("pageIndex")int pageIndex, @Param("pageSize")int pageSize);


	Integer deleteById(int did);

	/**
	 * 按content字段模糊查询
	 * @param key
	 * @param pageSize 
	 * @param pageIndex 
	 * @return
	 */
	List<Discuss> listByLikeContent(@Param("key")String key, 
			@Param("pageIndex")int pageIndex, @Param("pageSize")int pageSize);


	Integer updateById(Discuss discuss);


	Discuss findById(int did);


	Integer countListByLikeContent(String key);

}
