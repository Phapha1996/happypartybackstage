package com.party.product.mapper;

import org.apache.ibatis.annotations.Param;

import com.party.domain.Img;
/**
 * 
 * @author Caizhf
 * @date 2017年6月20日下午4:26:38
 * @version v.0.1
 * @email 1115054416@qq.com 
 *
 * <p>Description: 图片映射</p>
 *
 */
public interface ImgMapper {

	Integer save(Img img);

	Img findById(int iid);

	Integer deleteById(int iid);

	Integer updateUrlById(@Param("iid")int iid, @Param("imgUrl")String imgUrl, @Param("diskUrl")String diskUrl);

}
