package io.github.upiota.server.config;


import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class OAuth2SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private Filter ssoFilter;

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	
	@Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        auth
			.userDetailsService(userDetailsService)
			.passwordEncoder(passwordEncoder());
    }
	
//	@Autowired
//	public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//		authenticationManagerBuilder.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder());
//	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	//@Configuration
	//@EnableResourceServer
	protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
		@Override
		public void configure(HttpSecurity http) throws Exception {
			// @formatter:off
			http.antMatcher("/me").authorizeRequests().anyRequest().authenticated();
			// @formatter:on
		}
	}
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/swagger-resources/**", "/webjars/**", "/v2/api-docs", "/swagger-ui.html", "/",
				"/*.html", "/favicon.ico", "/**/*.html", "/**/*.css", "/**/*.js");
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
		 .authorizeRequests()
         .antMatchers("/oauth/*").permitAll()
         .anyRequest().authenticated()
         //.and().csrf()
         //.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
         .and().addFilterBefore(ssoFilter, BasicAuthenticationFilter.class);
	}
}