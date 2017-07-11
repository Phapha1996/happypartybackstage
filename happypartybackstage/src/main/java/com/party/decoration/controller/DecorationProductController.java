package com.party.decoration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.party.decoration.service.DecorationService;
import com.party.domain.DecorationProduct;
import com.party.dto.Response;

/**
 * 
 * @author Caizhf
 * @date 2017年6月21日下午9:27:05
 * @version v.0.1
 * @email 1115054416@qq.com 
 *
 * <p>Description: 场地布置下面的商品</p>
 *
 */
@RestController
@RequestMapping("/dec/pro")
public class DecorationProductController {
	
	@Autowired
	private DecorationService service;
	
	/**
	 * 功能：增加商品
	 * 注意：需要把分类、所属布置主键传入
	 * @param dp
	 * @return
	 */
	//127.0.0.18090/dec/pro/add?title=*&description=*&number=*&price=*&decoration.did=*&productCategory.dcid=*
	@RequestMapping("/add")
	public Response save(DecorationProduct dp){
		return Response.success(service.saveDecorationProduct(dp));
	}
	
	//127.0.0.1:8090/dec/pro/delete/*
	@RequestMapping("/delete/{dpid}")
	public Response delete(@PathVariable int dpid){
		service.deleteDecorationProduct(dpid);
		return Response.success();
	}
	
	/**
	 * 功能：根据id查询本商品的详情
	 * 注意：由于内部业务需要，会把商家一起映射出来
	 * @param dpid
	 * @return
	 */
	//127.0.0.1:8090/dec/pro/get/*
	@RequestMapping("/get/{dpid}")
	public Response get(@PathVariable int dpid){
		return Response.success(service.findDecorationProductById(dpid));
	}
	
	/**
	 * 功能：修改
	 * 注意：布置外键与分类外键暂不开放修改,需要的时候在跟我联系
	 * @return
	 */
	//127.0.0.1:8090/dec/pro/update?title=*&description=*&number=*&price=*&decoration.did=*&productCatrgory.dcid=*
	@RequestMapping("/update")
	public Response update(DecorationProduct dp){
		service.updateDecorationProduct(dp);
		return Response.success();
	}
	
	/**
	 * 功能：根据布置的id查询附属下面的所有商品
	 * @param did 布置的id
	 * @param pageNum 
	 * @param pageSize
	 * @return
	 */
	//127.0.0.1:8090/dec/pro/listdec/*?did=*&pageSize=*
	@RequestMapping("/listdec/{pageNum}")
	public Response listByDecorationId(@RequestParam("did")int did, 
			@PathVariable int pageNum, @RequestParam("pageSize")int pageSize){
		return Response.success(service.listDecorationProductByDecorationId(did,pageNum,pageSize));
	}
	
	/**
	 * 功能：根据分类的id来查询该分类下所有的商品
	 * @param dcid
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	//127.0.0.1:8090/dec/pro/listcat/*?dcid=*&pageSize=*
	@RequestMapping("/listcat/{pageNum}")
	public Response listByCategoryId(@RequestParam("dcid")int dcid, 
			@PathVariable int pageNum, @RequestParam("pageSize")int pageSize){
		return Response.success(service.listDecorationProductByCategoryId(dcid,pageNum,pageSize));
	}
	
	/**
	 * 功能：模糊查询或者查询所有（泛化方法）
	 * 注意：如果key存在，那么就是模糊查询，如果key不存在，就是正常查询所有的商品
	 * @param key 
	 * @return
	 */
	//正常list：127.0.0.1:8090/dec/pro/list/*?pageSize=*
	//title模糊查询：127.0.0.1:8090/dec/pro/list/*?pageSize=*&key=*
	@RequestMapping("/list/{pageNum}")
	public Response list( String key,
			@PathVariable int pageNum,@RequestParam("pageSize")int pageSize){
		return Response.success(service.listDecorationProductOrByLikeTitle(key,pageNum,pageSize));
	}
	
}
