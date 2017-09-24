package com.spring.practice.jpa.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.spring.practice.jpa.entity.Group;

public interface GroupJpaRepo extends CrudRepository<Group, String> {
	
	public Group findByUserid(@Param("userId") String userId);
	
	@Modifying
	public void updateName(@Param("groupName") String groupName, @Param("groupId") String groupId);
	
	@Modifying
	public void updateDesc(@Param("groupDesc") String groupDesc, @Param("groupId") String groupId);
	
	@Modifying
	public void deleteGroup(@Param("groupId") String groupId);
}
