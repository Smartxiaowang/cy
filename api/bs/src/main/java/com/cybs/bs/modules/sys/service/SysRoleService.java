
package com.cybs.bs.modules.sys.service;


import com.cybs.bs.common.utils.BaseService;
import com.cybs.bs.common.utils.PageData;
import com.cybs.bs.modules.sys.dto.SysRoleDTO;
import com.cybs.bs.modules.sys.entity.SysRoleEntity;

import java.util.List;
import java.util.Map;


/**
 * 角色
 *
 */
public interface SysRoleService extends BaseService<SysRoleEntity> {

	PageData<SysRoleDTO> page(Map<String, Object> params);

	List<SysRoleDTO> list(Map<String, Object> params);

	SysRoleDTO get(Long id);

	void save(SysRoleDTO dto);

	void update(SysRoleDTO dto);

	void delete(Long[] ids);

}
