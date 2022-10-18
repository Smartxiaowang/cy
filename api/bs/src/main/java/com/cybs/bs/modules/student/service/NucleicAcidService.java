package com.cybs.bs.modules.student.service;

import com.cybs.bs.common.utils.BaseService;
import com.cybs.bs.common.utils.PageData;
import com.cybs.bs.modules.student.entity.NucleicAcidEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
//核酸接口
public interface NucleicAcidService extends BaseService<NucleicAcidEntity> {
    PageData<NucleicAcidEntity> page(Map<String, Object> params);

    void insertEntity(NucleicAcidEntity nucleicAcidEntity);

    List<HashMap> getAcidAllMyStuList(Map<String, Object> params);
}
