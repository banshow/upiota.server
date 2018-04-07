package io.github.upiota.server.config;



import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import io.github.upiota.server.security.MyAuthenticationEntryPoint;
import io.github.upiota.server.security.OAuth2UrlLogoutSuccessHandler;
import io.github.upiota.server.security.MyAccessDeniedHandler;

@Configuration
@EnableWebSecurity
public class OAuth2SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private Filter ssoFilter;
	
	@Autowired
 	private MyAuthenticationEntryPoint unauthorizedHandler;
	
	@Autowired
	private OAuth2UrlLogoutSuccessHandler oAuth2UrlLogoutSuccessHandler;

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

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/swagger-resources/**", "/webjars/**", "/v2/api-docs", "/swagger-ui.html", "/",
				"/*.html", "/favicon.ico", "/**/*.html", "/**/*.css", "/**/*.js");
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
		 .authorizeRequests()
         .antMatchers("/oauth/*","/error").permitAll()
         .antMatchers("/login","/index").permitAll()
         .anyRequest().authenticated()
         .and().logout().logoutSuccessHandler(oAuth2UrlLogoutSuccessHandler)//.logoutSuccessUrl("http://localhost:8888/oauth/exit")
         .and().csrf().disable()//.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
         .addFilterBefore(ssoFilter, BasicAuthenticationFilter.class);
         //.exceptionHandling().accessDeniedHandler(new MyAccessDeniedHandler()).authenticationEntryPoint(unauthorizedHandler);
	}
}