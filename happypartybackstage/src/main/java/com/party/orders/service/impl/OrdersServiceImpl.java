package com.party.orders.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.party.decoration.mapper.DecorationProductMapper;
import com.party.dto.OrdersDTO;
import com.party.dto.Page;
import com.party.exception.ExceptionMsgEnum;
import com.party.exception.ServiceException;
import com.party.orders.mapper.ShopCartMapper;
import com.party.orders.service.OrdersService;
import com.party.product.mapper.ServeMapper;
import com.party.product.mapper.SiteMapper;
/**
 * 
 * @author Caizhf
 * @date 2017年6月13日下午7:28:23
 * @version v.0.1
 * @email 1115054416@qq.com 
 *
 * <p>Description: 订单service层：订单</p>
 *
 */
@Service
public class OrdersServiceImpl implements OrdersService{
	
	@Autowired
	private ShopCartMapper shopCartMapper;
	@Autowired
	private SiteMapper siteMapper;
	@Autowired
	private ServeMapper serveMapper;
	@Autowired
	private DecorationProductMapper decMapper;
	
	private final Logger log = LoggerFactory.getLogger(OrdersServiceImpl.class);
	
	private static final String TALENT_SERVICE = "达人服务";
	private static final String BUS_SERVICE = "包车服务";
	private static final String CATER_SERVICE = "餐饮服务";
	private static final String SITE = "场地";
	private static final String DEC_PRODUCT = "场地布置";
	private static final int PAGE_SIZE = 10;
			
		
	//删除订单的某项
	@Override
	@Transactional
	public void delete(int spid) {
		if(spid==0)
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		if(shopCartMapper.deleteById(spid)==null)
			throw new ServiceException(ExceptionMsgEnum.SERVER_DB_NOTEXIT);
	}

	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public Map<String,List> listOrdersByUser(int userId, int ordersState, int pageNum,int pageSize) {
		if(ordersState < 1)
			throw new ServiceException("不是订单状态!");
		int totleRecord = shopCartMapper.countByStateAndUserId(userId,ordersState);
		Page page = null;
		if(pageNum==0)
			page = new Page(totleRecord,1);
		else
			//如果不为空，那么就按照想看的页码进行
			page = new Page(totleRecord, pageNum,pageSize);
		
		Map<String,List> showPage= new HashMap<String,List>();
		//查出用户所有加入过清单的场地
		List<OrdersDTO> sites =  shopCartMapper.listSiteShopCartByUserIdAndState(userId,
				ordersState,page.getStartindex(),pageSize);
		//查出用户所有加入过清单的达人服务
		List<OrdersDTO> talents = shopCartMapper.listServeShopCartByUserIdAndStateAndServeType(userId,
				ordersState,TALENT_SERVICE,page.getStartindex(),pageSize);
		//查出用户所有加入过清单的包车服务
		List<OrdersDTO> buses = shopCartMapper.listServeShopCartByUserIdAndStateAndServeType(userId,
				ordersState,BUS_SERVICE,page.getStartindex(),pageSize);
		//查出用户所有加入过清单的餐饮服务
		List<OrdersDTO> caters = shopCartMapper.listServeShopCartByUserIdAndStateAndServeType(userId,
				ordersState,CATER_SERVICE,page.getStartindex(),pageSize);
		////查出用户所有加入过清单的场地布置商品
		List<OrdersDTO> decorationProducts = shopCartMapper.listDecProductShopCartByUserIdAndState(userId,
				ordersState,page.getStartindex(),pageSize);
		
		showPage.put(SITE, sites);
		showPage.put(DEC_PRODUCT, decorationProducts);
		showPage.put(CATER_SERVICE, caters);
		showPage.put(TALENT_SERVICE, talents);
		showPage.put(BUS_SERVICE, buses);
		
		return showPage;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public OrdersDTO findById(int spid) {
		if(spid==0)
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		OrdersDTO orders = shopCartMapper.findById(spid);
		if(orders==null)
			throw new ServiceException(ExceptionMsgEnum.SERVER_DB_NOTEXIT);
		
		if(orders.getProductType().equals(SITE)){
			//如果是场地
			orders.setProduct(siteMapper.findById(orders.getProductId()));
		}else if(orders.getProductType().equals(DEC_PRODUCT)){
			//如果是场地布置
			orders.setProduct(decMapper.findById(orders.getProductId()));
		}else{
			//不然就是二级服务了
			orders.setProduct(serveMapper.findById(orders.getProductId()));
		}
			
		return orders;
	}

/*	private void updateOnState(int state, int spid) {
		if(spid==0)
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		shopCartMapper.updateStateById(state,spid);
	}
*/
	


	//取消订单
	@Override
	public void updateOnStateToCanceled(int spid) {
		shopCartMapper.updateStateById(CANCELED, spid);
	}


	@Override
	public OrdersDTO findByOrdersNum(String ordersNum) {
		if(ordersNum==null)
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		OrdersDTO orders = shopCartMapper.findByOrdersNum(ordersNum);
		if(orders==null)
			throw new ServiceException(ExceptionMsgEnum.SERVER_DB_NOTEXIT);
		
		if(orders.getProductType().equals(SITE)){
			//如果是场地
			orders.setProduct(siteMapper.findById(orders.getProductId()));
		}else if(orders.getProductType().equals(DEC_PRODUCT)){
			//如果是场地布置
			orders.setProduct(decMapper.findById(orders.getProductId()));
		}else{
			//不然就是二级服务了
			orders.setProduct(serveMapper.findById(orders.getProductId()));
		}
		return orders;
	}


	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public Map<String, List> listOrders(int ordersState, int pageNum, int pageSize) {
		if(ordersState < 1)
			throw new ServiceException("不是订单状态!");
		int totleRecord = shopCartMapper.countByState(ordersState);
		Page page = null;
		if(pageNum==0)
			page = new Page(totleRecord,1);
		else
			//如果不为空，那么就按照想看的页码进行
			page = new Page(totleRecord, pageNum,pageSize);
		
		Map<String,List> showPage= new HashMap<String,List>();
		//查出用户所有加入过清单的场地
		List<OrdersDTO> sites =  shopCartMapper.listSiteShopCartByUserIdAndState(null,
				ordersState,page.getStartindex(),pageSize);
		//查出用户所有加入过清单的达人服务
		List<OrdersDTO> talents = shopCartMapper.listServeShopCartByUserIdAndStateAndServeType(null,
				ordersState,TALENT_SERVICE,page.getStartindex(),pageSize);
		//查出用户所有加入过清单的包车服务
		List<OrdersDTO> buses = shopCartMapper.listServeShopCartByUserIdAndStateAndServeType(null,
				ordersState,BUS_SERVICE,page.getStartindex(),pageSize);
		//查出用户所有加入过清单的餐饮服务
		List<OrdersDTO> caters = shopCartMapper.listServeShopCartByUserIdAndStateAndServeType(null,
				ordersState,CATER_SERVICE,page.getStartindex(),pageSize);
		////查出用户所有加入过清单的场地布置商品
		List<OrdersDTO> decorationProducts = shopCartMapper.listDecProductShopCartByUserIdAndState(null,
				ordersState,page.getStartindex(),pageSize);
		
		showPage.put(SITE, sites);
		showPage.put(DEC_PRODUCT, decorationProducts);
		showPage.put(CATER_SERVICE, caters);
		showPage.put(TALENT_SERVICE, talents);
		showPage.put(BUS_SERVICE, buses);
		
		return showPage;
	}


	@Override
	public void updateOnState(int state, int spid) {
		if(spid==0)
			throw new ServiceException(ExceptionMsgEnum.CLIENT_PARAM_ERROR_MSG);
		shopCartMapper.updateStateById(state,spid);
	}

	
}
