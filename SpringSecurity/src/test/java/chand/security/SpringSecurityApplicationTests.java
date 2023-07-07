package chand.security;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import chand.security.dao.UserInfoRepository;
import chand.security.entity.UserInfo;
import chand.security.entity.UserRole;

@SpringBootTest
class SpringSecurityApplicationTests {

	@Autowired
	private UserInfoRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Test
	void contextLoads() {
		UserInfo user = new UserInfo();
		user.setEmail("shaikchand@gmail.com");
		user.setUsername("Shaik Chand Basha");
		user.setPassword(passwordEncoder.encode("chand123"));
		UserRole role = new UserRole();
		role.setRole("ADMIN");
		UserRole role2 = new UserRole();
		role2.setRole("ANALYST");
		user.setRoles(Arrays.asList(role,role2));
		UserInfo save = userRepository.save(user);
		assertThat(()->save!=null);
	}

}
