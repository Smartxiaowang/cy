
package com.cybs.bs.modules.sys.service.impl;

import com.cybs.bs.common.Global.CMSException;
import com.cybs.bs.common.utils.BaseServiceImpl;
import com.cybs.bs.common.utils.Constant;
import com.cybs.bs.common.utils.ConvertUtils;
import com.cybs.bs.common.utils.TreeUtils;
import com.cybs.bs.modules.security.user.UserDetail;
import com.cybs.bs.modules.sys.dao.SysMenuDao;
import com.cybs.bs.modules.sys.dto.SysMenuDTO;
import com.cybs.bs.modules.sys.entity.SysMenuEntity;
import com.cybs.bs.modules.sys.enums.SuperAdminEnum;
import com.cybs.bs.modules.sys.service.SysMenuService;
import com.cybs.bs.modules.sys.service.SysRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenuDao, SysMenuEntity> implements SysMenuService {
	@Autowired
	private SysRoleMenuService sysRoleMenuService;

	@Override
	public SysMenuDTO get(Long id) {
		SysMenuEntity entity = baseDao.getById(id);

		SysMenuDTO dto = ConvertUtils.sourceToTarget(entity, SysMenuDTO.class);

		return dto;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void save(SysMenuDTO dto) {
		SysMenuEntity entity = ConvertUtils.sourceToTarget(dto, SysMenuEntity.class);

		//保存菜单
		insert(entity);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void update(SysMenuDTO dto) {
		SysMenuEntity entity = ConvertUtils.sourceToTarget(dto, SysMenuEntity.class);

		//上级菜单不能为自身
		if(entity.getId().equals(entity.getPid())){
			throw new CMSException(1,"");
		}

		//更新菜单
		updateById(entity);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delete(Long id) {
		//删除菜单
		deleteById(id);

		//删除角色菜单关系
		sysRoleMenuService.deleteByMenuId(id);
	}

	@Override
	public List<SysMenuDTO> getAllMenuList(Integer type) {
		List<SysMenuEntity> menuList = baseDao.getMenuList(type);

		List<SysMenuDTO> dtoList = ConvertUtils.sourceToTarget(menuList, SysMenuDTO.class);

		return TreeUtils.build(dtoList, Constant.MENU_ROOT);
	}

	@Override
	public List<SysMenuDTO> getUserMenuList(UserDetail user, Integer type) {
		List<SysMenuEntity> menuList;

		//系统管理员，拥有最高权限
		if(user.getSuperAdmin() == SuperAdminEnum.YES.value()){
			menuList = baseDao.getMenuList(type);
		}else {
			menuList = baseDao.getUserMenuList(user.getId(), type);
		}

		List<SysMenuDTO> dtoList = ConvertUtils.sourceToTarget(menuList, SysMenuDTO.class);

		return TreeUtils.build(dtoList);
	}

	@Override
	public List<SysMenuDTO> getListPid(Long pid) {
		List<SysMenuEntity> menuList = baseDao.getListPid(pid);

		return ConvertUtils.sourceToTarget(menuList, SysMenuDTO.class);
	}

}