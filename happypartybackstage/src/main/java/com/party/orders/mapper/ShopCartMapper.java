package com.party.orders.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.party.domain.DecorationProduct;
import com.party.domain.Serve;
import com.party.domain.ShopCart;
import com.party.domain.Site;
import com.party.dto.OrdersDTO;
/**
 * 
 * @author Caizhf
 * @date 2017年6月14日下午4:23:28
 * @version v.0.1
 * @email 1115054416@qq.com 
 *
 * <p>Description: 购物清单持久层</p>
 *
 */
public interface ShopCartMapper {
	
	/**
	 * 只需要根据id删除即可
	 * @param spid
	 * @return
	 */
	Integer deleteById(int spid);

	/**
	 * 需要级联图片，那些商品需要在Service层自己动手做映射
	 * @param spid
	 * @return
	 */
	OrdersDTO findById(int spid);

	/**
	 * 需要与场地表、用户表级联，查出为本用户下场地的清单
	 * @param userId 用户id
	 * @param pageSize 
	 * @param i 
	 * @param state	状态为0（状态是清单）
	 * @return
	 */
	List<OrdersDTO> listSiteShopCartByUserIdAndState(@Param("userId")Integer userId, 
			@Param("ordersState")int ordersState, @Param("pageIndex")Integer pageIndex, @Param("pageSize")Integer pageSize);

	/**
	 * 需要与服务表、用户表级联，查出为本用户下服务的清单
	 * @param userId 用户id
	 * @param state 状态为0是清单
	 * @param serveType 服务类型
	 * @return
	 */
	List<OrdersDTO> listServeShopCartByUserIdAndStateAndServeType(@Param("userId") Integer userId,
			@Param("ordersState") int state, @Param("serveType") String serveType,
			@Param("pageIndex")Integer pageIndex, @Param("pageSize")Integer pageSize);

	/**
	 * 需要与场地布置商品表、用户表级联，查出为本用户下场地布置商品的清单
	 * @param userId 用户id
	 * @param state	状态为0是清单
	 * @return
	 */
	List<OrdersDTO> listDecProductShopCartByUserIdAndState(@Param("userId") Integer userId,@Param("ordersState") int ordersState,
			@Param("pageIndex")Integer pageIndex, @Param("pageSize")Integer pageSize);

	/**
	 * 统计一下当前用户是某个状态下面的订单有多少条
	 * @param userId	当前用户
	 * @param ordersState	订单状态
	 * @return
	 */
	Integer countByStateAndUserId(@Param("userId")int userId, @Param("ordersState")int ordersState);

	/**
	 * 分页查出当前用户是某个状态下面的订单集合
	 * @param userId	当前用户
	 * @param ordersState	订单状态
	 * @param startIndex	
	 * @param pageSize	
	 * @return
	 */
	/*List<OrdersDTO> listByStateAndUserId(@Param("userId")int userId, @Param("ordersState")int ordersState,
			@Param("startIndex")int startIndex, @Param("pageSize")int pageSize);*/

	/**
	 * 根据id更新清单下面的状态
	 * @param ordersState
	 * @param spid
	 * @return
	 */
	Integer updateStateById(@Param("ordersState") int ordersState, @Param("spid")int spid);

	
	/**
	 * 根据商品类型与id查询这个商品的价格
	 * @param productId
	 * @param productType
	 * @return
	 */
	Double findProductPriceByProductIdAndProductType(@Param("productId")int productId, @Param("productType")String productType);


	/**
	 * 根据订单号查询指定的订单
	 * @param ordersNum
	 * @return
	 */
	OrdersDTO findByOrdersNum(String ordersNum);

	Integer countByState(int ordersState);

}
