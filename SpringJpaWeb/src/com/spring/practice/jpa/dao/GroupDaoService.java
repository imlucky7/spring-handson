package com.spring.practice.jpa.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.practice.jpa.entity.Group;
import com.spring.practice.jpa.repository.GroupJpaRepo;
import com.spring.practice.jpa.util.DatabaseException;

@Repository
public class GroupDaoService  {
	
	@Autowired
	GroupJpaRepo groupJpaRepo;
	
	@Transactional
	public Group getGroupByUserid(String userid) {
		return groupJpaRepo.findByUserid(userid);
	}
	
	@Transactional(rollbackFor=Exception.class, noRollbackFor=DatabaseException.class)
	public void updateGroup(Group group) throws DatabaseException, Exception {
		try {
			groupJpaRepo.updateName(group.getGroupName(), group.getGroupId());
			if(false) {
				throw new DatabaseException("no rollback test exception"); //no rollback takeplace
				//throw new Exception("rollback test exception"); //rollback takeplace
			}
			groupJpaRepo.updateDesc(group.getDescription(), group.getGroupId());
		} catch(DatabaseException e) {
			e.printStackTrace();
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		} 
	}
	
	@Transactional
	public void deleteGroup(Group group) {
		try {
			//groupJpaRepo.deleteGroup(group.getGroupId());
			Group g = groupJpaRepo.findOne(group.getGroupId());
			groupJpaRepo.delete(g);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
