/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.cybs.bs.modules.log.service;



import com.baomidou.mybatisplus.extension.service.IService;
import com.cybs.bs.common.utils.PageData;
import com.cybs.bs.modules.log.dto.SysLogOperationDTO;
import com.cybs.bs.modules.log.entity.SysLogOperationEntity;

import java.util.List;
import java.util.Map;

/**
 * 操作日
 * @since 1.0.0
 */
public interface SysLogOperationService extends IService<SysLogOperationEntity> {

    PageData<SysLogOperationDTO> page(Map<String, Object> params);

    List<SysLogOperationDTO> list(Map<String, Object> params);

    boolean save(SysLogOperationEntity entity);
}