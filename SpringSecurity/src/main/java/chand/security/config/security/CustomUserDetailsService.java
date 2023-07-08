package chand.security.config.security;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import chand.security.dao.UserInfoRepository;
import chand.security.entity.UserInfo;
import chand.security.entity.UserRole;
import chand.security.exception.FailedToSaveException;
import chand.security.exception.UserAlreadyExistedException;
import chand.security.payload.request.UserRequest;
import chand.security.payload.request.UserResponse;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserInfoRepository userInfoRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<UserInfo> users = this.userInfoRepository.findByEmail(username);
		if (users == null || users.isEmpty()) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		UserInfo userInfo = users.iterator().next();
		User user = new org.springframework.security.core.userdetails.User(userInfo.getUsername(),
				userInfo.getPassword(), userInfo.getEnabled(), userInfo.isAccountNonExpired(),
				userInfo.isCredentialsNonExpired(), userInfo.isAccountNonLocked(), userInfo.getAuthorities());
		System.out.println(user.getAuthorities().size());

		return user;
	}

	public UserResponse createUser(UserRequest userRequest) {
		if (userRequest == null) {
			return null;
		}
		String email = userRequest.getEmail().trim();
		List<UserInfo> users = this.userInfoRepository.findByEmail(userRequest.getEmail());
		if (users.size() > 0) {
			throw new UserAlreadyExistedException("User already existed with the current email: " + userRequest.getEmail());
		}
		String username = userRequest.getUsername().trim();
		String password = userRequest.getPassword();
		password = passwordEncoder.encode(password);

		UserInfo user = new UserInfo();
		user.setEmail(email);
		user.setUsername(username);
		user.setEnabled(userRequest.isEnabled());
		user.setPassword(password);
		List<UserRole> roles = userRequest.getRoles().stream().map(x -> x.toUpperCase().trim())
				.map(x -> new UserRole(x,user)).collect(Collectors.toList());
		user.setRoles(roles);
		
		UserInfo savedUser = this.userInfoRepository.save(user);
		if (savedUser == null) {
			throw new FailedToSaveException("Unable to create user please try again!");
		}
		
		UserResponse userResponse = new UserResponse();
		userResponse.setEmail(savedUser.getEmail());
		userResponse.setRoles(savedUser.getRoles().stream().map(UserRole::getRole).collect(Collectors.toSet()));
		userResponse.setUsername(savedUser.getUsername());
		return userResponse;
	}

}
