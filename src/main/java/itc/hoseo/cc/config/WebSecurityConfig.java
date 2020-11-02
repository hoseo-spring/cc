package itc.hoseo.cc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Override
	@Bean
	protected UserDetailsManager userDetailsService() {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		return manager;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/mypage").access("hasRole('USER')")
			.antMatchers("/chat").access("hasRole('USER')")
			.antMatchers("/edit").access("hasRole('USER')")
			.anyRequest().permitAll()
			.and()
			.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/")
			.and()
			.formLogin()
				.loginPage("/login")
				.loginProcessingUrl("/loginProcess")				
				.usernameParameter("id")
				.passwordParameter("password")
				.defaultSuccessUrl("/")
				.failureUrl("/login?error")				
				.permitAll()
			.and().csrf().disable();

	    http.headers().frameOptions().disable();
			
	}
}