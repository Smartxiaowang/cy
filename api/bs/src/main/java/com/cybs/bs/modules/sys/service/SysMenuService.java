
package com.cybs.bs.modules.sys.service;

import com.cybs.bs.common.utils.BaseService;
import com.cybs.bs.modules.security.user.UserDetail;
import com.cybs.bs.modules.sys.dto.SysMenuDTO;
import com.cybs.bs.modules.sys.entity.SysMenuEntity;

import java.util.List;


/**
 * 菜单管理
 *
 */
public interface SysMenuService extends BaseService<SysMenuEntity> {

	SysMenuDTO get(Long id);

	void save(SysMenuDTO dto);

	void update(SysMenuDTO dto);

	void delete(Long id);

	/**
	 * 菜单列表
	 *
	 * @param type 菜单类型
	 */
	List<SysMenuDTO> getAllMenuList(Integer type);

	/**
	 * 用户菜单列表
	 *
	 * @param user  用户
	 * @param type 菜单类型
	 */
	List<SysMenuDTO> getUserMenuList(UserDetail user, Integer type);

	/**
	 * 根据父菜单，查询子菜单
	 * @param pid  父菜单ID
	 */
	List<SysMenuDTO> getListPid(Long pid);
}
