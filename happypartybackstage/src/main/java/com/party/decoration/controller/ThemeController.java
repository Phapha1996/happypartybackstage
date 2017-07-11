package com.party.decoration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.party.decoration.service.DecorationService;
import com.party.domain.Theme;
import com.party.dto.Response;

/**
 * 
 * @author Caizhf
 * @date 2017年6月21日下午1:43:42
 * @version v.0.1
 * @email 1115054416@qq.com
 *
 *        <p>
 * 		Description: 布置主题
 *        </p>
 *
 */
@RequestMapping("/dectheme")
@RestController
public class ThemeController {

	@Autowired
	private DecorationService service;

	// 127.0.0.1:8090/dectheme/add?themeName=*
	@RequestMapping("/add")
	public Response save(Theme theme) {
		return Response.success(service.saveTheme(theme));
	}

	/**
	 * 功能：删除布置主题 限制：删除布置主题，但是不能级联删除布置
	 * 
	 * @return
	 */
	//127.0.0.1:8090/dectheme/delete/*
	@RequestMapping("/delete/{thid}")
	public Response delete(@PathVariable int thid) {
		service.deleteTheme(thid);
		return Response.success();
	}

	/**
	 * 功能：查询所有的布置主题
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	// 127.0.0.1:8090/dectheme/list/*?pageSize=*
	@RequestMapping("/list/{pageNum}")
	public Response list(@PathVariable int pageNum, @RequestParam("pageSize") int pageSize) {
		return Response.success(service.listTheme(pageNum, pageSize));
	}

	/**
	 * 功能：按标题模糊查询
	 * 
	 * @param key
	 * @return
	 */
	// 127.0.0.1:8090/dectheme/list/like/*?key=*&pageSize=*
	@RequestMapping("/list/like/{pageNum}")
	public Response listLikeByTitle(@RequestParam("key") String key, @PathVariable int pageNum,
			@RequestParam("pageSize") int pageSize) {
		return Response.success(service.listThemeLikeByName(key, pageNum, pageSize));
	}
	
	//127.0.0.1:8090/dectheme/get/*
	@RequestMapping("/get/{thid}")
	public Response get(@PathVariable int thid){
		return Response.success(service.getTheme(thid));
	}
	
	@RequestMapping("/update")
	public Response update(Theme theme){
		service.updateTheme(theme);
		return Response.success();
	}
	
	/**
	 * 置顶
	 * @param sequence
	 */
	//127.0.0.1:8090/dectheme/top/*?sequence=*&thid=*
	@RequestMapping("/top/{thid}")
	public Response toTop(@RequestParam("sequence")int sequence,@PathVariable int thid){
		service.themeToTop(thid,sequence);
		return Response.success();
	}
}
