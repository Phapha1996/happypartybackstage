package com.party.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.party.security.MyAuthenticationProvider;
import com.party.security.MyLogoutHandler;
import com.party.security.MyAdminDetailsService;
import com.party.security.SuccessHandler;

/**
 * 
 * @author Caizhf
 * @date 2017年5月20日下午3:40:36
 * @version v.0.1
 * @title springSecurity配置
 * <p>
 *   1.首先当我们要自定义Spring Security的时候我们需要继承自WebSecurityConfigurerAdapter来完成，相关配置重写对应 方法即可。
 *   2.我们在这里注册CustomUserService的mBean，然后通过重写configure方法添加我们自定义的认证方式。 
 * </p>
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//开启security注解
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private MyAuthenticationProvider provider;//自定义验证
	@Autowired
	private MyAdminDetailsService adminDetailsService;//自定义用户服务
	@Autowired
	private SuccessHandler successHandler;
	@Autowired
	private MyLogoutHandler logoutHandler;
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.userDetailsService(adminDetailsService);
		auth.authenticationProvider(provider);
	}
	
	
	
	
	/**
	 * 1.permitAll():表示该请求任何人都可以访问。
	 * 
	 * 2.anyRequest().authenticated(): 表示其他的请求都必须要有权限认证。
	 * 
	 * 3.and():连接。
	 * 
	 * 4.这里我们可以通过匹配器来匹配路径，比如antMatchers方法，假设我要管理员才可以访问admin文件夹下的内容，
	 * 我可以这样来写：.antMatchers("/admin/**").hasRole("ROLE_ADMIN")，也可以设置admin文件夹下的
	 * 文件可以有多个角色来访问，写法如下：.antMatchers("/admin/**").hasAnyRole("ROLE_ADMIN","ROLE_USER")。
	 * 
	 * 5.可以通过hasIpAddress来指定某一个ip可以访问该资源,假设只允许访问ip为210.210.210.210的请求获取admin下的
	 * 资源 ，写法如下.antMatchers("/admin/**").hasIpAddress("210.210.210.210").
	 * 
	 * 6.更多资源请查询SpringSecurityAPI文档使用
	 * 
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.headers().frameOptions().disable();
		 http.csrf().disable()
		 .authorizeRequests()
		 	.antMatchers("/css/**","/js/**","/fonts/**","/images/**","/plugins/**","/simple/**").permitAll()	//静态资源路径
             .antMatchers("/user/**","/custom/**","/topic/**","/play/**","/discuss/delete/**","/recommend/**","/business/**")
		 	 .hasAuthority("ROLE_ADMIN")
             .anyRequest().authenticated()												//接下来的就需要登录了,普通用户等等
         .and()
             .formLogin()
             .usernameParameter("account") 												//前端username配置成email
             .passwordParameter("password")
             .loginPage("/login").successHandler(successHandler)
             .failureUrl("/security/failure").permitAll()
          .and()
             .logout()
             .logoutSuccessUrl("/login")
             .invalidateHttpSession(true)
             .addLogoutHandler(logoutHandler)
//             .deleteCookies("9D119EE5A2B7DAF6B4DC1EF871D0AC3C")
             .permitAll()
         .and()
         	 .rememberMe()									//记住我，下次自动登录
         	 .tokenValiditySeconds(60 * 60)					//cookie时间(自行根据需求设置)
         	 .key("9D119EE5A2B7DAF6B4DC1EF871D0AC3C");		//cookie私钥
		     
		 
//		 http.sessionManagement()
//		 	 .invalidSessionUrl("/login")
//	     	 .maximumSessions(1)
//	     	 .expiredUrl("/security/only");
//	     	 .maxSessionsPreventsLogin(true);
	}
	
}
