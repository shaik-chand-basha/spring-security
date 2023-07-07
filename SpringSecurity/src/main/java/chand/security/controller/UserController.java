package chand.security.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import chand.security.config.security.CustomUserDetailsService;
import chand.security.payload.request.UserRequest;
import chand.security.payload.request.UserResponse;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRequest userRequest){
	 UserResponse createdUser = this.userDetailsService.createUser(userRequest);
	 return new ResponseEntity<UserResponse>(createdUser,HttpStatus.CREATED);
	}
	
}
