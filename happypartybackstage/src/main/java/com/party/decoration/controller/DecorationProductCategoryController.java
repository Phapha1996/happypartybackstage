package com.party.decoration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.party.decoration.service.DecorationService;
import com.party.domain.DecorationProductCategory;
import com.party.dto.Response;

/**
 * 
 * @author Caizhf
 * @date 2017年6月22日下午1:29:22
 * @version v.0.1
 * @email 1115054416@qq.com 
 *
 * <p>Description: 场地布置下商品的分类</p>
 *
 */
@RequestMapping("/dec/cat")
@RestController
public class DecorationProductCategoryController {
	@Autowired
	private DecorationService service;
	
	/**
	 * 功能：增加分类
	 * @return
	 */
	//127.0.0.1:8090/dec/cat/add?catName=*
	@RequestMapping("/add")
	public Response save(DecorationProductCategory decProCat){
		service.saveDecorationProductCategory(decProCat);
		return Response.success();
	}
	
	//127.0.0.1:8090/dec/cat/delete/*
	@RequestMapping("/delete/{dcid}")
	public Response delete(@PathVariable int dcid){
		service.deleteDecorationProductCategory(dcid);
		return Response.success();
	}
	
	//127.0.0.1:8090/dec/cat/update?catName=*&dcid=*
	@RequestMapping("/update")
	public Response update(DecorationProductCategory decProCat){
		service.update(decProCat);
		return Response.success();
	}
	
	/**
	 * 功能：查询单条
	 * @param dcid
	 * @return
	 */
	//127.0.0.1:8090/dec/cat/get/*
	@RequestMapping("/get/{dcid}")
	public Response get(@PathVariable int dcid){
		return Response.success(service.findDecorationProductCategoryById(dcid));
	}
	
	
	/**
	 * 功能：查询所有
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	//127.0.0.1:8090/dec/cat/list/*?pageSize=*
	@RequestMapping("/list/{pageNum}")
	public Response list(@PathVariable int pageNum, @RequestParam("pageSize")int pageSize){
		return Response.success(service.listDecorationProductCategory(pageNum,pageSize));
	}
}
