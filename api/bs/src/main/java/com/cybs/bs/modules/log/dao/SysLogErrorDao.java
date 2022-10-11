/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.cybs.bs.modules.log.dao;

import com.cybs.bs.common.dao.BaseDao;
import com.cybs.bs.modules.log.entity.SysLogErrorEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 异常日志
 *
 * @since 1.0.0
 */
@Mapper
public interface SysLogErrorDao extends BaseDao<SysLogErrorEntity> {
	
}
