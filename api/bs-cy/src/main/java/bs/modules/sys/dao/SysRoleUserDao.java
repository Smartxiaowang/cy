/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package bs.modules.sys.dao;

import bs.modules.sys.entity.SysRoleUserEntity;
import bs.common.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 角色用户关系
 *
 * @since 1.0.0
 */
@Mapper
public interface SysRoleUserDao extends BaseDao<SysRoleUserEntity> {

    /**
     * 根据角色ids，删除角色用户关系
     * @param roleIds 角色ids
     */
    void deleteByRoleIds(Long[] roleIds);

    /**
     * 根据用户id，删除角色用户关系
     * @param userIds 用户ids
     */
    void deleteByUserIds(Long[] userIds);

    /**
     * 角色ID列表
     * @param userId  用户ID
     *
     * @return
     */
    List<Long> getRoleIdList(Long userId);
}