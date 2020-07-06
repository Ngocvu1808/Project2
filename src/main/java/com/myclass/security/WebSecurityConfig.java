package com.myclass.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@ComponentScan("com.myclass")
@Order(1)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// Khai báo lớp Service dùng để check email lấy thông tin người dùng
	// Khai báo đối tượng giải mã pass và check pass
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	//Phân quyền người dùng
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		
		  // Các trang không yêu cầu login
        http.authorizeRequests().antMatchers("/admin/login", "/admin/logout").permitAll();
        
        // Nếu chưa login, nó sẽ redirect tới trang /login.
		  http.authorizeRequests().antMatchers("/user/**").access("hasAnyRole('ROLE_TEACHER', 'ROLE_ADMIN')");
		  http.authorizeRequests().antMatchers("/role/**").access("hasAnyRole('ROLE_TEACHER', 'ROLE_ADMIN')");
		  http.authorizeRequests().antMatchers("/course/**").access("hasAnyRole('ROLE_TEACHER', 'ROLE_ADMIN')");
		  http.authorizeRequests().antMatchers("/video/**").access("hasAnyRole('ROLE_TEACHER', 'ROLE_ADMIN')");
		  http.authorizeRequests().antMatchers("/usercourse/**").access("hasAnyRole('ROLE_TEACHER', 'ROLE_ADMIN')");
		  http.authorizeRequests().antMatchers("/category/**").access("hasAnyRole('ROLE_TEACHER', 'ROLE_ADMIN')");
		  http.authorizeRequests().antMatchers("/target/**").access("hasAnyRole('ROLE_TEACHER', 'ROLE_ADMIN')");

		  
		   // Ngoại lệ AccessDeniedException sẽ ném ra.
	        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/error/403");

		
		
		http.formLogin()
		.loginPage("/admin/login")
		.loginProcessingUrl("/admin/login") // Link khai báo trong action của form đăng nhập
		.usernameParameter("email")
		.passwordParameter("password")
		.defaultSuccessUrl("/home")
		.failureUrl("/admin/login?error=true");
		
		// Cấu hình đăng xuất
		http.logout()
		.logoutUrl("/admin/logout")
		.logoutSuccessUrl("/admin/login")
		.deleteCookies("JSESSIONID");
		
	
	}
	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
	}

}
