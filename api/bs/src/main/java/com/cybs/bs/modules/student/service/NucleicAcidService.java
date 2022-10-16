package com.cybs.bs.modules.student.service;

import com.cybs.bs.common.Global.R;
import com.cybs.bs.common.utils.BaseService;
import com.cybs.bs.common.utils.PageData;
import com.cybs.bs.modules.student.entity.BodyTemperatureEntity;
import com.cybs.bs.modules.student.entity.NucleicAcidEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface NucleicAcidService extends BaseService<NucleicAcidEntity> {
    PageData<NucleicAcidEntity> page(Map<String, Object> params);

    void insertEntity(NucleicAcidEntity nucleicAcidEntity);

    List<HashMap> getAcidAllMyStuList(Map<String, Object> params);
}
