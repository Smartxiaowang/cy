
package com.cybs.bs.modules.log.dao;

import com.cybs.bs.common.dao.BaseDao;
import com.cybs.bs.modules.log.entity.SysLogOperationEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 操作日志
 *
 * @since 1.0.0
 */
@Mapper
public interface SysLogOperationDao extends BaseDao<SysLogOperationEntity> {
	
}
