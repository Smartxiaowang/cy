package com.cybs.bs.modules.student.service;

import com.cybs.bs.common.utils.BaseService;
import com.cybs.bs.common.utils.PageData;
import com.cybs.bs.modules.student.entity.BodyTemperatureEntity;

import java.util.Map;

public interface BodyTemperatureService extends BaseService<BodyTemperatureEntity> {
    PageData<BodyTemperatureEntity> page(Map<String, Object> params);
}
