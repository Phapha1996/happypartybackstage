package com.party.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 
 * @author Caizhf
 * @date 2017年5月20日下午3:37:32
 * @version v.0.1
 * @title SpringMVC配置
 *        <p>
 *        Description: 当用户访问login时跳转到login.html页面。
 *        </p>
 * 
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index");
		registry.addViewController("/index").setViewName("index");
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/upload").setViewName("upload");
		registry.addViewController("/main").setViewName("main");
		
		registry.addViewController("/site").setViewName("site/index");
		registry.addViewController("/site/addview").setViewName("site/add");
		registry.addViewController("/site/edit").setViewName("site/edit");
		
		registry.addViewController("/service").setViewName("service/index");
		registry.addViewController("/service/addview").setViewName("service/add");
		registry.addViewController("/service/edit").setViewName("service/edit");
		registry.addViewController("/service/category").setViewName("service/category");
		
		registry.addViewController("/img").setViewName("img");
		
		registry.addViewController("/custom").setViewName("custom/index");
		registry.addViewController("/custom/info").setViewName("custom/info");
		
		registry.addViewController("/user").setViewName("user/index");
		registry.addViewController("/user/edit").setViewName("user/edit");
		registry.addViewController("/user/update").setViewName("user/updateAdmin");
		
		registry.addViewController("/business").setViewName("business/index");
		registry.addViewController("/business/addview").setViewName("business/add");
		registry.addViewController("/business/edit").setViewName("business/edit");
		
		registry.addViewController("/topic").setViewName("topic/index");
		registry.addViewController("/topic/addview").setViewName("topic/add");
		registry.addViewController("/topic/reply").setViewName("topic/reply");
		
		registry.addViewController("/discuss").setViewName("discuss/index");
		registry.addViewController("/discuss/list").setViewName("discuss/list");
		
		registry.addViewController("/order").setViewName("order/index");
		
		registry.addViewController("/decoration").setViewName("decoration/index");
		registry.addViewController("/decoration/list").setViewName("decoration/list");
		registry.addViewController("/decoration/list/add").setViewName("decoration/addDecoration");
		registry.addViewController("/decoration/list/edit").setViewName("decoration/editDecoration");
		registry.addViewController("/decoration/goods").setViewName("decoration/goodsList");
		registry.addViewController("/decoration/goods/add").setViewName("decoration/addGoods");
		registry.addViewController("/decoration/goods/edit").setViewName("decoration/editGoods");
		registry.addViewController("/decoration/category").setViewName("decoration/categoryList");
		
		registry.addViewController("/meal").setViewName("meal/index");
		registry.addViewController("/meal/addview").setViewName("meal/add");
		registry.addViewController("/meal/edit").setViewName("meal/edit1");
		registry.addViewController("/meal/category").setViewName("meal/category");
		
		registry.addViewController("/play").setViewName("play/index");
		registry.addViewController("/play/addview").setViewName("play/add");
		
		registry.addViewController("/recommend").setViewName("recommend/index");
		registry.addViewController("/recommend/addview").setViewName("recommend/add");
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("/userstatic/**")
	            .addResourceLocations("classpath:/userstatic/");
	}
}