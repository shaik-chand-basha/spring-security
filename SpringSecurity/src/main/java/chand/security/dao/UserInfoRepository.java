package chand.security.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import chand.security.entity.UserInfo;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {

	@Query("from UserInfo user left outer join fetch user.roles role where user.email=?1")
	List<UserInfo> findByEmail(String email);
}
