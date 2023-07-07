package chand.security.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityWebConfig {

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

//		http.httpBasic().authenticationEntryPoint(new AuthenticationEntryPoint() {
//
//			@Override
//			public void commence(HttpServletRequest request, HttpServletResponse response,
//					AuthenticationException authException) throws IOException, ServletException {
//
//				response.sendError(401);
//			}
//		});
//		http.authorizeHttpRequests().anyRequest().authenticated();
		http.authorizeHttpRequests(request -> {
			request.antMatchers("/notices", "/contact").permitAll()
					.antMatchers("/myLoans", "/myBalance", "/myCards", "/myAccount").authenticated();
		}).
		formLogin().defaultSuccessUrl("/myAccount", false)
		.and().httpBasic();
//		UserDetails userDetails = User.withDefaultPasswordEncoder().username("chand").password("chand")
//				.authorities("User").build();

		http.userDetailsService(userDetailsService);
		return http.build();
	}

//	public InMemoryUserDetailsManager getInMemoryUserDetailsManager() {
//		InMemoryUserDetailsManager detailsManager = new InMemoryUserDetailsManager();
//		UserDetails userDetails = User.withUsername("chand").password(passwordEncoder().encode("chand"))
//				.username("chand").authorities("USER").build();
//		detailsManager.createUser(userDetails);
//		return detailsManager;
//	}

//	@Bean
//	 UserDetailsManager users(DataSource dataSource) {
//		JdbcUserDetailsManager detailsManager = new JdbcUserDetailsManager(dataSource);
//		detailsManager
//				.setUsersByUsernameQuery("select username,password, enabled from user_info where username = ?");
//		detailsManager.setAuthoritiesByUsernameQuery("select u.username,role.role from user_info u inner join user_role role on role.fk_user_id=u.id where u.username = ?");
//		return detailsManager;
//	}

//	@Bean
//	UserDetailsManager users(DataSource dataSource) {
//
//		JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
//		users.setUsersByUsernameQuery("select username,password, enabled from user_info where username = ?");
//		users.setAuthoritiesByUsernameQuery(
//				"select u.username,role.role from user_info u inner join user_role role on role.fk_user_id=u.id where u.username = ?");
//		return users;
//	}

//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

}
