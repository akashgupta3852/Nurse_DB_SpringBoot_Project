//package com.example.demo.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@EnableWebSecurity // tells spring security that is a web security configuration
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//	@Override
//	public void configure(AuthenticationManagerBuilder builder) throws Exception {
//		builder.inMemoryAuthentication().withUser("Akash").password("123456").roles("ADMIN").and().withUser("Ramesh")
//				.password("123321").roles("USER");
//	}
//
//	@Bean
//	public PasswordEncoder getPasswordEncoder() {
//		return NoOpPasswordEncoder.getInstance();
//	}
//
//	@Override
//	public void configure(HttpSecurity security) throws Exception {
//		security.authorizeRequests().antMatchers("/", "/static/js", "/static/css").permitAll()
//				.antMatchers("/nurses/list/**").hasAnyRole("USER", "ADMIN").antMatchers("/**").hasRole("ADMIN").and()
//				.formLogin();
//	}
//}
