package com.example.demo.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class DemoWebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DemoWebLogoutHandler demoWebLogutHandler;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
        .authorizeRequests()
        /* 加入無須授權的網址 */
        .antMatchers("/logoutResult.html").permitAll()
        .anyRequest()
        .authenticated()
        .and().formLogin()
        /* 使用基礎的http basic-帳號/密碼驗證 */
        .and().httpBasic()
        /* 停用預設登入畫面 */
        .and().formLogin().disable()
        /* 關閉csrf防護，免帶token */
        .csrf().disable()
		/* 開啟csrf防護，需帶token
		 * 
		 * 全域防護
		 * http.csrf().CsrfProtectionMatcher(AnyRequestMatcher.INSTANCE);
		 * 
		 * 特定路徑
		 * http.antMatcher("/xxx/**").xxx
		 * 
		 * 需驗證授權後的請求
		 * http.authorizeRequest(authorize->authorize.anyRequest().xxx).xxx;
		 * 
		 * 需特定角色才授權的請求
		 * http.authorizeRequest(authorize->authorize.anyRequest().hasRole("account level/type")).xxx;
		 * 
		 * 授權後的請求
		 * http.authorizeRequest(authorize->authorize.anyRequest().authenticated()).xxx; 
		 *  
		 * 新增自訂登出器 */
		.logout().addLogoutHandler(demoWebLogutHandler)
		/* 自訂登出後的重導向網址 */
		.logoutSuccessUrl("/logoutResult.html");
	}
	
}
