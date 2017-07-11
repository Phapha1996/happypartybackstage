package com.party.product.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.party.domain.Admin;
import com.party.domain.Role;
import com.party.domain.Serve;
import com.party.dto.Response;
import com.party.product.service.ServeService;
/**
 * 
 * @author Caizhf
 * @date 2017年6月11日下午12:23:31
 * @version v.0.1
 * @email 1115054416@qq.com 
 *
 * <p>Description: 达人服务、包车服务、餐饮服务共同统称为二级服务</p>
 *
 */
@RestController
@RequestMapping("/serve")
public class ServeController {
	
	@Autowired
	private ServeService serveService;
	
	/**
	 * 显示全部服务，需要传入参数的可选类型：包车服务、达人服务、餐饮服务
	 * 注意：aid是可选传入的，如果传入了就是只查看当前这个商家下的服务
	 * @param type 包车服务、达人服务、餐饮服务三选一
	 * @param pageNum 页码
	 * @param aid 
	 * @return
	 */
	//访问路径：127.0.0.1:8080/serve/list/*?type=*&aid=*				其中第二个*只能是达人服务或者包车服务或者餐饮服务
	@RequestMapping("/list/{pageNum}")
	public Response list(@PathVariable int pageNum,@RequestParam("type")String type, Integer aid){
		Admin admin = (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//如果是店家，那么就只能查询自己拥有的东西
		if(admin!=null)
		for(Role r:admin.getRoles()){
			if(r.getRoleName().equals("ROLE_SHOP"))
				return Response.success(serveService.listServe(pageNum,type,admin.getAid()));
		}
		//如果admin的id被传入了，说明管理员想要看这个商家的产品
		if(aid!=null)
			return Response.success(serveService.listServe(pageNum,type,aid));
		else
		//如果是管理员角色，那么就可以查询所有的
		return Response.success(serveService.listServe(pageNum,type,null));
	}
	

	/**
	 * 显示某个细分类下的所有服务
	 * @param pageNum页码
	 * @param type 包车服务、达人服务、餐饮服务三选一
	 * @param categoryId 细分板块的id号码
	 * @return
	 */
	//访问路径：127.0.0.1:8080/serve/list/category/*?type=*&categoryId=*
	@RequestMapping("/list/category/{pageNum}")
	public Response listServeByCategory(@PathVariable int pageNum,@RequestParam("type")String type,
			@RequestParam int categoryId){
		return Response.success(serveService.listServeByCategory(pageNum,type,categoryId));
	}
	
	/**
	 * 
	 * @param sid 服务id号码
	 * @return
	 */
	//访问路径：127.0.0.1:8080/serve/get/*
	@RequestMapping("/get/{sid}")
	public Response get(@PathVariable int sid){
		return Response.success(serveService.getServe(sid));
	}
	
	/**
	 * 根据类型查看所有的分类
	 * @param type 包车服务、达人服务、餐饮服务三选一
	 * @return
	 */
	//访问路径：127.0.0.1:8090/serve/catlist?type=*
	@RequestMapping("/catlist")
	public Response listCategory(@RequestParam("type") String type){
		return Response.success(serveService.listCategory(type));
	}
	
	/**
	 * 功能：根据标题模糊查询
	 * @param pageNum 页码
	 * @param pageSize 每页需要显示多少条数据
	 * @param type 服务类型
	 * @param key 模糊条件
	 * @return
	 */
	//127.0.0.1:8090/serve/list/like/*?pageSize=*&type=*&key=*
	@RequestMapping("/list/like/{pageNum}")
	public Response listLikeByTitle(@PathVariable int pageNum, 
			@RequestParam("pageSize")int pageSize, @RequestParam("type")String type, @RequestParam("key")String key){
		return Response.success(serveService.listLikeByTitle(pageNum, type, pageSize, key));
	}
	
	/**
	 * 功能：更新服务
	 * 注意：不允许更新图片，需要删除增加自行用别的功能。
	 * 		不允许设置排序号，需要置顶只能由管理员设置。
	 * 		不允许修改商品所属的商家
	 * 		不允许更新该服务所属的服务细分类，只允许添加或者删除
	 * @param serve
	 * @return
	 */
	@RequestMapping("/update")
	//127.0.0.1:8090/serve/update?title=*&city=*&price=*&details=*&notes=*&wechat=*&number=*&sid=*
	public Response update(Serve serve){
		serveService.update(serve);
		return Response.success();
	}
	
	/**
	 * 功能：添加服务
	 * 注意：（接口限制）这是商家的添加接口，不是管理员调用的添加接口
	 * @param serve
	 * @return
	 */
	//127.0.0.1:8090/serve/add?type=*&title=*&city=*&price=*&details=*notes=*&wechat=*&number=*&serveCategory.scid=*
	/*@RequestMapping("/add")
	public Response save(Serve serve){
		Admin admin = (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		serve.setAdmin(admin);
		return Response.success(serveService.save(serve));
	}*/
	
	/**
	 * 功能：添加服务
	 * 注意：（接口限制）管理员后台帮商家添加服务的接口
	 * @param serve	服务
	 * @param aid	商家的id号码
	 * @return
	 */
	/*
	
	127.0.0.1:8090/serve/add?type=*?title=*
	&city=*&price=*&details=*notes=*&wechat=*&number=*&serveCategory.scid=*&admin.aid=*
	
	*/
	@RequestMapping("/admin/add")
	public Response save(Serve serve,@RequestParam("aid")int aid){
		serve.setAdmin(new Admin(aid));
		return Response.success(serveService.save(serve));
	}
	
	/**
	 * 功能：根据id删除某个服务
	 * @param sid
	 * @return
	 */
	//127.0.0.1:8090/serve/delete/*
	@RequestMapping("/delete/{sid}")
	public Response delete(@PathVariable int sid){
		serveService.deleteById(sid);
		return Response.success();
	}
	
	/**
	 * 置顶
	 * @param sid
	 * @param sequence
	 * @return
	 */
	//127.0.0.1:8090/serve/top/*?sequence=*
	@RequestMapping("/top/{sid}")
	public Response toTop(@PathVariable int sid,@RequestParam("sequence")int sequence){
		serveService.toTop(sid,sequence);
		return Response.success();
	}
	
}
