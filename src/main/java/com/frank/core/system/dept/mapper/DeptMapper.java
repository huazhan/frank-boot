package com.frank.core.system.dept.mapper;

import java.util.List;

import com.frank.core.system.dept.entity.Dept;

public interface DeptMapper {

	Dept selectById(long id);
	
	List<Dept> selectChildrenListById(long id);

	List<Dept> list(Dept dept);

	void insert(Dept dept);

	void update(Dept dept);

	void delete(long id);

	void updateStatusByParentId(Dept dept);

	long selectMaxId();

}
