package com.party.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.party.domain.Admin;
import com.party.domain.Role;
import com.party.domain.Site;
import com.party.dto.Response;
import com.party.product.service.SiteService;

/**
 * 
 * @author Caizhf
 * @date 2017年6月20日下午5:20:32
 * @version v.0.1
 * @email 1115054416@qq.com 
 *
 * <p>Description: 场地控制器</p>
 *
 */
@RestController
@RequestMapping("/site")
public class SiteController {
	@Autowired
	private SiteService siteService;
	
	/**
	 * 功能：增加场地
	 * 注意事项：这个接口是商家自行添加的，不是管理员帮助添加的
	 * 需要传入：title标题，price价格，city城市，address所在地址，roomNum房间数量，
	 * 		  bedNum床位数，apply适用人数，tags标签，description介绍，facilities配套设施，
	 * 		  reference补充介绍，remind温馨提示，num提供数量，
	 * @param site
	 * @return
	 */
	/*
	  
	  测试地址:127.0.0.1:8090/site/add?title=*&price=*&city=*&address=*&roomNum=*&bedNum=*&apply=*
	 		&tags=*&description=*&facilities=*&reference=*&remind=*&num=*
	 
	 */
	/*@RequestMapping("/add")
	public Response save(Site site){
		Admin admin = (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		site.setAdmin(admin);
		return Response.success(siteService.save(site));
	}*/
	
	/**
	 * 功能：增加场地
	 * 注意事项：这是管理员帮助商家添加场地的接口
	 * @param site 场地基本信息
	 * @param aid  商家的id号码
	 * @return
	 */
	//测试地址：127.0.0.1:8090/admin/add?
	@RequestMapping("/admin/add")
	public Response save(Site site,@RequestParam("aid")int aid){
		site.setAdmin(new Admin(aid));
		return Response.success(siteService.save(site));
	}
	
	
	//测试地址：127.0.0.1:8090/site/delete/*
	@RequestMapping("/delete/{siid}")
	public Response delete(@PathVariable int siid){
		siteService.deleteById(siid);
		return Response.success();
	}
	
	/**
	 * 功能：更新场地
	 * 限制：不可更新商家admin，不可更新sequence置顶，想要置顶的自行调用置顶接口
	 * @param site
	 * @return
	 */
	//测试地址:127.0.0.1:8090/site/update
	@RequestMapping("/update")
	public Response update(Site site){
		siteService.updateById(site);
		return Response.success();
	}
	
	/**
	 * 功能：获取详情
	 * @return
	 */
	//测试地址127.0.0.1:8090/site/get/*
	@RequestMapping("/get/{siid}")
	public Response get(@PathVariable int siid){
		return Response.success(siteService.findById(siid));
	}
	
	/**
	 * 功能：按标题模糊查询
	 * @param key
	 * @return
	 */
	//127.0.0.1:8090/site/list/like/*?key=*&pageSize=*
	@RequestMapping("/list/like/{pageNum}")
	public Response listLikeByTitle(@RequestParam("key")String key,
			@PathVariable int pageNum,@RequestParam("pageSize")int pageSize){
		return Response.success(siteService.listLikeByTitle(key,pageNum,pageSize));
	}
	
	/**
	 * 功能：获取所有的场地
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	//127.0.0.1:8090/site/list/*?pageSize=*&aid=*
	@RequestMapping("/list/{pageNum}")
	public Response list(@PathVariable int pageNum,@RequestParam("pageSize")int pageSize, Integer aid){
		Admin admin = (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//如果是店家，那么就只能查询自己拥有的东西
		if(admin!=null)
		for(Role r:admin.getRoles()){
			if(r.getRoleName().equals("ROLE_SHOP"))
				return Response.success(siteService.list(pageNum,pageSize,admin.getAid()));
		}
		if(aid!=null)
			return Response.success(siteService.list(pageNum,pageSize,aid));
		else
		//如果是管理员角色，那么就可以查询所有的
		return Response.success(siteService.list(pageNum,pageSize,null));
	}
	
	/**
	 * 功能：置顶某个场地
	 * @param siid
	 * @param sequence 置顶级别
	 * @return
	 */
	//127.0.0.1:8090/site/top/*?sequence=*
	@RequestMapping("/top/{siid}")
	public Response toTop(@PathVariable int siid,@RequestParam("sequence")int sequence){
		siteService.toTop(siid,sequence);
		return Response.success();
	}
	
	
}
