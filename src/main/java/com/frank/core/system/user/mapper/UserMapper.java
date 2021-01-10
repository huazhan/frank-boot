package com.frank.core.system.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.frank.core.system.user.entity.User;
import com.frank.core.system.user.entity.UserRole;
import com.frank.framework.security.entity.MyUserDetails;
import com.frank.framework.security.entity.RoleAndPerms;

public interface UserMapper {

	List<User> list(User user);
	
	long insert(User User);

	void update(User user);

	void delete(long id);

	User selectById(long id);

	void batchDelete(List<Long> idList);

	/** 根据deptId查询该部门及其子部门下的所有用户列表**/
	List<User> selectByDeptIdIncludeSub(@Param("deptId") long deptId);

	void insertUserRole(UserRole userRole);

	void deleteUserRoleByUserId(long id);

	MyUserDetails seleteByUserName(@Param("username") String username);

	List<RoleAndPerms> selectRoleAndPermsListByUserName(@Param("username") String username);


}