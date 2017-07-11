package com.party.decoration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.party.decoration.service.DecorationService;
import com.party.domain.Admin;
import com.party.domain.Decoration;
import com.party.domain.Role;
import com.party.dto.Response;

@RestController
@RequestMapping("/dec")
public class DecorationController {
	@Autowired
	private DecorationService service;

	/**
	 * 功能：增加
	 * 限制：商家自行增加的接口
	 * 注意：可以自选是否传入主题的id号码
	 * @param dec
	 * @return
	 */
	//127.0.0.1:8090/dec/add?title=*&city=*&tags=*&details=*&theme.thid=*
	/*@RequestMapping("/add")
	public Response save(Decoration dec){
		Admin admin = (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		dec.setAdmin(admin);
		return Response.success(service.saveDecoration(dec));
	}*/
	
	/**
	 * 功能：增加
	 * 限制：管理员帮助商家添加的接口
	 * 注意：可以自选是否传入主题的id号码，但是必须传入商家id
	 * @param dec
	 * @return
	 */
	//127.0.0.1:8090/dec/save?title=*&city=*&tags=*details=*theme.thid=&aid=*&price=*&bottomPrice=*
	@RequestMapping("/admin/add")
	public Response save(Decoration dec,@RequestParam("aid")int aid){
		dec.setAdmin(new Admin(aid));
		return Response.success(service.saveDecoration(dec));
	}
	
	//127.0.0.1:8090/dec/delete/*
	@RequestMapping("/delete/{did}")
	public Response delete(@PathVariable int did){
		service.deleteDecoration(did);
		return Response.success();
	}
	
	//127.0.0.1:8090/dec/get/*
	@RequestMapping("/get/{did}")
	public Response get(@PathVariable int did){
		return Response.success(service.getDecoration(did));
	}
	
	/**
	 * 功能：根据主题查询所有的场地布置
	 * @param thid 主题id号
	 * @param pageNum 
	 * @param pageSize
	 * @return
	 */
	//127.0.0.1:8090/dec/list/theme/*?thid=*&pageSize=*
	@RequestMapping("/list/theme/{pageNum}")
	public Response listByThemeId(@RequestParam("thid")int thid, 
			@PathVariable int pageNum, @RequestParam("pageSize") int pageSize){
		return Response.success(service.listDecorationByThemeId(thid, pageNum, pageSize));
	}
	
	/**
	 * 功能：查询所有的场地布置
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	//127.0.0.1:8090/dec/list/*?pageSize=*
	@RequestMapping("/list/{pageNum}")
	public Response list(@PathVariable int pageNum, @RequestParam("pageSize") int pageSize,int aid){
		Admin admin = (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//如果是店家，那么就只能查询自己拥有的东西
		if(admin!=null)
		for(Role r:admin.getRoles()){
			if(r.getRoleName().equals("ROLE_SHOP"))
				return Response.success(service.listDecoration(pageNum, pageSize,admin.getAid()));
		}
		if(aid!=0)
			return Response.success(service.listDecoration(pageNum, pageSize,aid));
		//如果是管理员角色，那么就可以查询所有的
		else
		return Response.success(service.listDecoration(pageNum, pageSize,null));
	}
	
	/**
	 * 功能：按标题查询场地布置
	 * @param pageNum
	 * @param pageSize
	 * @param key
	 * @return
	 */
	//127.0.0.1:8090/dec/list/like/*?pageSize=*&key=*
	@RequestMapping("/list/like/{pageNum}")
	public Response listLikeByTitle(@PathVariable int pageNum, 
			@RequestParam("pageSize") int pageSize,@RequestParam("key") String key){
		Admin admin = (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//如果是店家，那么就只能查询自己拥有的东西
		if(admin!=null)
		for(Role r:admin.getRoles()){
			if(r.getRoleName().equals("ROLE_SHOP"))
				return Response.success(service.listDecorationLikeByTitle(pageNum,pageSize,key,admin.getAid()));
		}
		//如果是管理员角色，那么就可以查询所有的
		return Response.success(service.listDecorationLikeByTitle(pageNum,pageSize,key,null));
	}
	
	/**
	 * 置顶
	 * @param did
	 * @param sequence
	 * @return
	 */
	//127.0.0.1:8090/dec/top/*?sequence=*
	@RequestMapping("/top/{did}")
	public Response toTop(@PathVariable int did,@RequestParam("sequence")int sequence){
		service.decorationToTop(did,sequence);
		return Response.success();
	}
	
	//127.0.0.1:8090/dec/update?
	@RequestMapping("/update")
	public Response update(Decoration d){
		service.updateDecoration(d);
		return Response.success();
	}
}
