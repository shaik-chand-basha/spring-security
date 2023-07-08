package chand.security.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityWebConfig {

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.csrf().disable();
		http.authorizeHttpRequests(request -> {
			request.antMatchers("/notices", "/contact","/user/**").permitAll()
					.antMatchers("/myLoans", "/myBalance", "/myCards", "/myAccount").authenticated();
		})
		.formLogin().defaultSuccessUrl("/myAccount", false)
		.and()
		.httpBasic();

		http.userDetailsService(userDetailsService);
		return http.build();
	}


	
	

}
