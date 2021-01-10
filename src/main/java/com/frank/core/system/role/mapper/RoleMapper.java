package com.frank.core.system.role.mapper;

import java.util.List;

import com.frank.core.system.role.entity.Role;
import com.frank.core.system.role.entity.RoleMenu;

public interface RoleMapper {

	Role selectById(long id);

	List<Role> list(Role role);

	long insert(Role role);

	void update(Role role);

	void delete(long id);

	void batchDelete(List<Long> idList);

	void insertRoleMenu(RoleMenu roleMenu);

	void deleteRoleMenuByRoleId(long roleId);

	void deleteUserRoleByRoleId(long roleId);

}
