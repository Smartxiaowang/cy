
package com.cybs.bs.modules.log.dao;

import com.cybs.bs.common.dao.BaseDao;
import com.cybs.bs.modules.log.entity.SysLogLoginEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 登录日志
 *
 * @since 1.0.0
 */
@Mapper
public interface SysLogLoginDao extends BaseDao<SysLogLoginEntity> {
	
}
