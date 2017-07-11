package com.party.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.party.domain.ServeCategory;
import com.party.dto.Response;
import com.party.product.service.ServeService;

@RestController
@RequestMapping("/ser_category")
public class ServeCategoryController {
	
	@Autowired
	private ServeService serveService;
	/**
	 * 功能：增加服务细分类
	 * @param sc
	 * @return
	 */
	//127.0.0.1:8090/ser_category/add?categoryName=*&serveType=*
	@RequestMapping("/add")
	public Response save(ServeCategory sc){
		serveService.saveServeCategory(sc);
		return Response.success();
	}
	
	/**
	 * 功能：根据id查单个细分类
	 * @param scid
	 * @return
	 */
	//127.0.0.1:8090/ser_category/get/*
	@RequestMapping("/get/{scid}")
	public Response get(@PathVariable int scid){
		return Response.success(serveService.findServeCategoryByScid(scid));
	}
	
	/**
	 * 功能：删除细分类(不提倡提供该接口给用户)
	 * @param scid
	 * @return
	 */
	//127.0.0.1:8090/ser_category/delete/*
	@RequestMapping("/delete/{scid}")
	public Response delete(@PathVariable int scid){
		serveService.deleteServeCategoryByScid(scid);
		return Response.success();
	}
	
	/**
	 * 功能：更新
	 * @param serveCategory
	 * @return
	 */
	//127.0.0.1:8090/ser_category/update?categoryName=*&serveType=*&scid=*
	@RequestMapping("/update")
	public Response update(ServeCategory serveCategory){
		serveService.updateServeCategoryByScid(serveCategory);
		return Response.success();
	}
}
