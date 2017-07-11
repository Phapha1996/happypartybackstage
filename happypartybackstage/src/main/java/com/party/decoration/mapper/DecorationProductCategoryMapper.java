package com.party.decoration.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.party.domain.DecorationProductCategory;

/**
 * 
 * @author Caizhf
 * @date 2017年6月22日下午2:20:56
 * @version v.0.1
 * @email 1115054416@qq.com 
 *
 * <p>Description: 场地布置下的商品的分类</p>
 *
 */
public interface DecorationProductCategoryMapper {

	Integer save(DecorationProductCategory decProCat);

	Integer deleteById(int dcid);

	Integer updateById(DecorationProductCategory decProCat);

	int countList();

	List<DecorationProductCategory> list(@Param("startIndex")int startIndex, @Param("pageSize")int pageSize);

	DecorationProductCategory findById(int dcid);
	
}
