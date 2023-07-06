package chand.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import chand.security.entity.UserInfo;
import chand.security.service.impl.UserInfoServiceImpl;

@RestController
public class UserInfoController {

	@Autowired
	private UserInfoServiceImpl userInfoService;

	@GetMapping("/user/all")
	public ResponseEntity<List<UserInfo>> findAll() {
		List<UserInfo> users = this.userInfoService.findAll();
		return ResponseEntity.ok(users);
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<UserInfo> findUser(@PathVariable(name = "userId", required = true) Integer userId) {
		if (userId == null || userId == 0) {
			return ResponseEntity.badRequest().build();
		}
		UserInfo userInfo = this.userInfoService.findUser(userId);
		return ResponseEntity.ok(userInfo);
	}
}
