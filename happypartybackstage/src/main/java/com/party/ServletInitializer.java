package com.party;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
/**
 * 
 * @author Caizhf
 * @date 2017年6月28日下午11:14:37
 * @version v.0.1
 * @email 1115054416@qq.com 
 *
 * <p>Description: 排除tomcat</p>
 *
 */
public class ServletInitializer extends SpringBootServletInitializer {
	/**
	 * 创建ServletInitializer.java，继承SpringBootServletInitializer ，
	 * 覆盖configure()，把启动类Application注册进去。
	 * 外部web应用服务器构建Web Application Context的时候，会把启动类添加进去
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(HappyPartyBackstageApplication.class);
	}
}