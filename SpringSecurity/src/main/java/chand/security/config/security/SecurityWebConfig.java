package chand.security.config.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityWebConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.formLogin();
		http.httpBasic();
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		http.httpBasic().authenticationEntryPoint(new AuthenticationEntryPoint() {

			@Override
			public void commence(HttpServletRequest request, HttpServletResponse response,
					AuthenticationException authException) throws IOException, ServletException {

				response.sendError(401);
			}
		});
//		http.authorizeHttpRequests().anyRequest().authenticated();
		http.authorizeHttpRequests(request -> {
			request.antMatchers("/notices", "/contact").permitAll()
					.antMatchers("/myLoans", "/myBalance", "/myCards", "/myAccount").authenticated();
		});
//		UserDetails userDetails = User.withDefaultPasswordEncoder().username("chand").password("chand")
//				.authorities("User").build();
		http.userDetailsService(getInMemoryUserDetailsManager());
		return http.build();
	}

	@Bean
	public InMemoryUserDetailsManager getInMemoryUserDetailsManager() {
		InMemoryUserDetailsManager detailsManager = new InMemoryUserDetailsManager();
		UserDetails userDetails = User.withUsername("chand").password(passwordEncoder().encode("chand")).username("chand").authorities("USER").build();
		detailsManager.createUser(userDetails);
		return detailsManager;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
