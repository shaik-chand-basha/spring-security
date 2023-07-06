package chand.security.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import chand.security.entity.UserInfo;

@Repository
public interface UserInfoDao extends CrudRepository<UserInfo, Integer> {

}
