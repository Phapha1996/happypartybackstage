package com.party.product.service;

import com.party.domain.Img;
/**
 * 
 * @author Caizhf
 * @date 2017年6月20日下午3:49:52
 * @version v.0.1
 * @email 1115054416@qq.com 
 *
 * <p>Description: 图片Service</p>
 *
 */
public interface ImgService {

	void save(String productType, int productId, String realPath, String diskPath);
	
	void delete(int iid);

	Img get(int iid);

	void update(int iid, String realPath, String diskPath);
	
}
