package com.party.decoration.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.party.domain.DecorationProduct;
/**
 * 
 * @author Caizhf
 * @date 2017年6月22日下午6:18:07
 * @version v.0.1
 * @email 1115054416@qq.com 
 *
 * <p>Description:场地布置下面的商品 </p>
 *
 */
public interface DecorationProductMapper {

	Integer save(DecorationProduct dp);

	Integer deleteById(int dpid);

	DecorationProduct findById(int dpid);

	Integer updateById(DecorationProduct dp);

	int countListDecorationProductByDecorationId(int did);

	List<DecorationProduct> listDecorationProductByDecorationId(@Param("did")int did,
			@Param("startIndex")Integer startIndex, @Param("pageSize")Integer pageSize);

	int countListDecorationProductByCategoryId(int dcid);

	List<DecorationProduct> listDecorationProductByCategoryId(@Param("dcid")int dcid,
			@Param("startIndex")Integer startIndex, @Param("pageSize")Integer pageSize);

	int countList(@Param("key")String key);
	
	/**
	 * 查询所有的或者按照标题模糊查询
	 * @param key 可为空，在mapper层做判断
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	List<DecorationProduct> listDecorationProductOrByLikeTitle(@Param("key")String key, 
			@Param("startIndex")Integer startIndex, @Param("pageSize")Integer pageSize);

	Integer removeForeignKeyOnDecorationById(@Param("dpid")int dpid, @Param("state")Integer state);

}
