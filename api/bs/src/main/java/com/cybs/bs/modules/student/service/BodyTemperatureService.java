package com.cybs.bs.modules.student.service;

import com.cybs.bs.common.Global.R;
import com.cybs.bs.common.utils.BaseService;
import com.cybs.bs.common.utils.PageData;
import com.cybs.bs.modules.student.entity.BodyTemperatureEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
//体温接口
public interface BodyTemperatureService extends BaseService<BodyTemperatureEntity> {

    PageData<BodyTemperatureEntity> page(Map<String, Object> params);

    R insertByEntity(BodyTemperatureEntity bodyTemperatureEntity);

    List<HashMap> getBodyTempAllMyStuList(Map<String, Object> params);
}
