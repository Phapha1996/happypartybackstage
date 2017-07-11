package com.party.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.party.domain.Admin;
import com.party.domain.MealCategory;
import com.party.domain.SetMeal;
import com.party.dto.Response;
import com.party.product.service.SetMealService;
/**
 * 
 * @author Caizhf
 * @date 2017年6月26日下午4:02:35
 * @version v.0.1
 * @email 1115054416@qq.com 
 *
 * <p>Description: 聚会套餐</p>
 *
 */
@RequestMapping("/meal")
@RestController
public class SetMealController {
	@Autowired
	private SetMealService service;
	
	/**
	 * 功能：查看所有
	 * 注意：如果传入了类型，那么就是点击图标得到的列表；如果没有传入类型，那么就是显示所有的套餐（所有分类）
	 * @param pageNum
	 * @param mealType
	 * @return
	 */
	//显示所有套餐：127.0.0.1:8080/meal/list/*?pageSize=*
	//显示图标内的套餐：127.0.0.1:8080/meal/list/*?categoryId=*&pageSize=*
	@RequestMapping("/list/{pageNum}")
	public Response list(@PathVariable int pageNum,
			Integer categoryId,@RequestParam("pageSize") int pageSize){
		return Response.success(service.list(pageNum,categoryId,pageSize));
	}
	
	/**
	 * 功能：查看详情
	 * @param smid
	 * @return
	 */
	//127.0.0.1:8080/meal/get/*
	@RequestMapping("/get/{smid}")
	public Response get(@PathVariable int smid){
		return Response.success(service.findById(smid));
	}
	
	/**
	 * 增加
	 * @param meal
	 * @return
	 */
	/*
	 * 测试地址:127.0.0.1:8090/meal/add?mealType=*&title=*&price=*&city=*&address=*&roomNum=*&bedNum=*&apply=*
	 			&tags=*&description=*&facilities=*&reference=*&remind=*&num=*
	 */
	/*@RequestMapping("/add")
	public Response save(SetMeal meal){
		Admin admin = (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		meal.setAdmin(admin);
		return Response.success(service.save(meal));
	}*/
	
	/**
	 * 增加：管理员帮助增加的接口
	 * 需要传入所属类型的id
	 * @param meal
	 * @param aid
	 * @return
	 */
	/*
	 * 127.0.0.1:8090/admin/add?mealCategory.mcid=*&title=*&price=*&city=*&address=*&roomNum=*&bedNum=*&apply=*
	 			&tags=*&description=*&facilities=*&reference=*&remind=*&num=*
	 */
	@RequestMapping("/admin/add")
	public Response save(SetMeal meal,@RequestParam("aid")int aid){
		meal.setAdmin(new Admin(aid));
		return Response.success(service.save(meal));
	}
	
	/**
	 * 删除
	 * @param smid
	 * @return
	 */
	//127.0.0.1:8090/meal/delete/*
	@RequestMapping("/delete/{smid}")
	public Response delete(@PathVariable int smid){
		service.deleteById(smid);
		return Response.success();
	}
	
	/**
	 * 更新
	 * @param setMeal
	 * @return
	 */
	//127.0.0.1:8090/meal/update?
	@RequestMapping("/update")
	public Response update(SetMeal setMeal){
		service.updateById(setMeal);
		return Response.success();
	}
	
	/**
	 * 置顶 
	 * @param smid
	 * @param sequence
	 * @return
	 */
	//127.0.0.1:8090/meal/top/*?sequence=*
	@RequestMapping("/top/{smid}")
	public Response toTop(@PathVariable int smid,@RequestParam("sequence")int sequence){
		service.toTop(smid,sequence);
		return Response.success();
	}
//-------------------------------------------Category Start-------------------------------------------//	
	/**
	 * 增加分类
	 * @param mc
	 * @return
	 */
	//127.0.0.1:8090/meal/cat/add?categoryName=*
	@RequestMapping("/cat/add")
	public Response saveCategory(MealCategory mc){
		return Response.success(service.saveCategory(mc));
	}
	
	/**
	 * 删除
	 * @param mcid
	 * @return
	 */
	//127.0.0.1:8090/meal/cat/delete/*
	@RequestMapping("/cat/delete/{mcid}")
	public Response deleteCategory(@PathVariable int mcid){
		service.deleteCategoryById(mcid);
		return Response.success();
	}
	
	/**
	 * 查询单条
	 * @param mcid
	 * @return
	 */
	@RequestMapping("/cat/get/{mcid}")
	public Response getCategory(@PathVariable int mcid){
		return Response.success(service.findCategoryById(mcid));
	}
	
	/**
	 * 查询列表
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	//127.0.0.1:8090/meal/cat/list/*?pageSize=*
	@RequestMapping("/cat/list/{pageNum}")
	public Response listCategory(@PathVariable int pageNum,@RequestParam int pageSize){
		return Response.success(service.listCategory(pageNum,pageSize));
	}
	
	/**
	 * 修改
	 * @param mc
	 * @return
	 */
	//127.0.0.1:8090/meal/cat/update?categoryName=*&mcid=*
	@RequestMapping("/cat/update")
	public Response updateCategory(MealCategory mc){
		service.updateCategory(mc);
		return Response.success();
	}
	
	
}
