package com.party.product.service;

import java.util.List;

import com.party.domain.Site;
import com.party.dto.Page;
/**
 * 
 * @author Caizhf
 * @date 2017年6月20日下午5:20:08
 * @version v.0.1
 * @email 1115054416@qq.com 
 *
 * <p>Description: 场地</p>
 *
 */
public interface SiteService {
	/**
	 * 功能：增加场地
	 * 需要传入：title标题，price价格，city城市，address所在地址，roomNum房间数量，
	 * 		  bedNum床位数，apply适用人数，tags标签，description介绍，facilities配套设施，
	 * 		  reference补充介绍，remind温馨提示，num提供数量，
	 * @param site
	 * @return
	 */
	Integer save(Site site);

	void deleteById(int siid);
	
	/**
	 * 功能：更新场地
	 * 限制：不可更新商家admin，不可更新sequence置顶，想要置顶的自行调用置顶接口
	 * @param site
	 * @return
	 */
	void updateById(Site site);

	/**
	 * 功能：获取详情
	 * @return
	 */
	Site findById(int siid);

	/**
	 * 功能：按标题模糊查询
	 * @param key
	 * @param pageSize 
	 * @param pageNum 
	 * @return
	 */
	Page listLikeByTitle(String key, int pageNum, int pageSize);

	/**
	 * 功能：获取所有的场地
	 * @param pageNum
	 * @param pageSize
	 * @param adminId 
	 * @return
	 */
	Page list(int pageNum, int pageSize, Integer adminId);

	/**
	 * 功能：置顶某个场地
	 * @param siid
	 * @param sequence 置顶级别
	 * @return
	 */
	void toTop(int siid, int sequence);
	
	
}
