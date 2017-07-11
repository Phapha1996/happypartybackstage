package com.party.decoration.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.party.domain.Decoration;
import com.party.domain.DecorationProduct;
import com.party.domain.DecorationProductCategory;
import com.party.domain.Theme;
import com.party.dto.Page;
/**
 * 
 * @author Caizhf
 * @date 2017年6月22日下午1:43:50
 * @version v.0.1
 * @email 1115054416@qq.com 
 *
 * <p>Description: 有关场地布置所有的业务都在这里</p>
 *
 */
public interface DecorationService {

	int saveTheme(Theme theme);

	/**
	 * 功能：删除布置主题 限制：删除布置主题，但是不能级联删除布置
	 * 
	 * @return
	 */
	void deleteTheme(int thid);
	
	void themeToTop(int thid, int sequence);
	
	Theme getTheme(int thid);

	void updateTheme(Theme theme);

	Page listTheme(int pageNum, int pageSize);

	Page listThemeLikeByName(String key, int pageNum, int pageSize);

	int saveDecoration(Decoration dec);

	void deleteDecoration(int did);

	Decoration getDecoration(int did);
	
	void updateDecoration(Decoration d);

	/**
	 * 功能：根据主题查询所有的场地布置
	 * 
	 * @param thid
	 *            主题id号
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	Page listDecorationByThemeId(int thid, int pageNum, int pageSize);

	Page listDecoration(int pageNum, int pageSize, Integer adminId);

	/**
	 * 按标题模糊查询场地布置
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param key
	 * @param adminId 
	 * @return
	 */
	Page listDecorationLikeByTitle(int pageNum, int pageSize, String key, Integer adminId);

	void saveDecorationProductCategory(DecorationProductCategory decProCat);

	void deleteDecorationProductCategory(int dcid);

	void update(DecorationProductCategory decProCat);

	Page listDecorationProductCategory(int pageNum, int pageSize);

	DecorationProductCategory findDecorationProductCategoryById(int dcid);
	
	/**
	 * 功能：增加商品
	 * 注意：需要把分类、所属布置主键传入
	 * @param dp
	 * @return
	 */
	int saveDecorationProduct(DecorationProduct dp);

	void deleteDecorationProduct(int dpid);

	DecorationProduct findDecorationProductById(int dpid);

	void updateDecorationProduct(DecorationProduct dp);

	/**
	 * 功能：根据布置的id查询附属下面的所有商品
	 * @param did 布置的id
	 * @param pageNum 
	 * @param pageSize
	 * @return
	 */
	Page listDecorationProductByDecorationId(int did, int pageNum, int pageSize);

	/**
	 * 功能：根据分类的id来查询该分类下所有的商品
	 * @param dcid
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	Page listDecorationProductByCategoryId(int dcid, int pageNum, int pageSize);
	
	/**
	 * 功能：模糊查询或者查询所有（泛化方法）
	 * 注意：如果key存在，那么就是模糊查询，如果key不存在，就是正常查询所有的商品
	 * @param key 
	 * @return
	 */
	Page listDecorationProductOrByLikeTitle(String key, int pageNum, int pageSize);

	void decorationToTop(int did, int sequence);


	
}
