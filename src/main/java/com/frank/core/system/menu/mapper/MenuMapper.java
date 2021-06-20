package com.frank.core.system.menu.mapper;

import java.util.List;

import com.frank.core.system.menu.entity.Menu;
import com.frank.core.system.menu.entity.MenuTree;
import org.apache.ibatis.annotations.Param;

public interface MenuMapper {

	Menu selectById(long id);

	List<Menu> list(Menu menu);

	void insert(Menu menu);

	void update(Menu menu);

	void delete(long id);

	void batchDelete(List<Long> idList);

	List<MenuTree> selectMenuTree(@Param("currentUsername") String currentUsername);

}
