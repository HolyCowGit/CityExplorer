package com.city.explorer.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.city.explorer.service.CustomUserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	GoogleOAuth2SuccessHandler googleOAuth2SuccessHandler;

	@Autowired
	CustomUserDetailService customUserDetailService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests ()
		.antMatchers( "/", "/shop/**", "/register" ,"/sellerRegister","/oauth/**") .permitAll()
		.antMatchers( "/admin/**").hasRole("ADMIN")
		.antMatchers( "/registerService/**").hasRole("SELLER")
		.anyRequest()
		.authenticated()
		
		.and() 
		.formLogin() 
		.loginPage("/login")
		.permitAll()
		.failureUrl("/login?error=true")
		//.failureUrl("/error")
		//.defaultSuccessUrl("/") to avoid timestamp error , we add true
		.defaultSuccessUrl("/",true)
		.usernameParameter("email")
		.passwordParameter("password")
		//.permitAll()
		
	
		
		.and()
		.oauth2Login()
		.loginPage("/login")
		.successHandler(googleOAuth2SuccessHandler)
		//.failureUrl("/")
		//.defaultSuccessUrl("/",true) //changes
		.and()

		.logout() 
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl ("/login")
		.invalidateHttpSession(true)
		.deleteCookies("JSESSIONID")
		.and()
		.exceptionHandling() 
		.and()
		.csrf()
		.disable();
		
		http.headers().frameOptions().disable();
		
	}
//	

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication()
//				.passwordEncoder(org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance())
//				.withUser("user1").password("secret1").roles("USER").and().withUser("admin1").password("secret1")
//				.roles("USER", "ADMIN");
//	}

	// Authorization : Role -> Access
//	protected void configure(HttpSecurity http) throws Exception {
//		http.httpBasic().and().authorizeRequests().antMatchers("/login/**").hasRole("USER").antMatchers("/**")
//				.hasRole("ADMIN").and().csrf().disable().headers().frameOptions().disable();
//	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {

		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailService);
		
	}

	@Override
	public void configure(WebSecurity web) throws Exception {

		web.ignoring().antMatchers("/resources/**", "/static/**", "/images/**", "/productImages/**", "/css/**",
				"/js/**");

	}

}
