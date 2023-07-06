package chand.security.service.impl;

import java.util.List;
import java.util.Spliterator;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import chand.security.dao.UserInfoDao;
import chand.security.entity.UserInfo;
import chand.security.exception.UserNotFoundException;

@Service
public class UserInfoServiceImpl {

	@Autowired
	private UserInfoDao userInfoDao;

	public List<UserInfo> findAll() {
		Spliterator<UserInfo> usersSpliterator = this.userInfoDao.findAll().spliterator();
		return StreamSupport.stream(usersSpliterator, false).toList();
	}
	
	public UserInfo findUser(Integer userId) {
		return this.userInfoDao.findById(userId).orElseThrow(()->new UserNotFoundException("User not found with id:"+userId));
	}
}
