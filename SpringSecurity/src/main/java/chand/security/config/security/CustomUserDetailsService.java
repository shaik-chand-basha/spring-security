package chand.security.config.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import chand.security.dao.UserInfoRepository;
import chand.security.entity.UserInfo;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserInfoRepository userInfoRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<UserInfo> users = this.userInfoRepository.findByEmail(username);
		if (users == null || users.isEmpty()) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		UserInfo userInfo = users.iterator().next();
		User user = new org.springframework.security.core.userdetails.User(userInfo.getUsername(), userInfo.getPassword(),userInfo.getEnabled(),userInfo.isAccountNonExpired(),userInfo.isCredentialsNonExpired(),userInfo.isAccountNonLocked(),
				userInfo.getAuthorities());
		
		return user;
	}

}
