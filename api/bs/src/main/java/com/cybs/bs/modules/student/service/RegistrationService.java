package com.cybs.bs.modules.student.service;

import com.cybs.bs.common.utils.BaseService;
import com.cybs.bs.common.utils.PageData;
import com.cybs.bs.modules.student.entity.RegistrationEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
//行程报备接口
public interface RegistrationService extends BaseService<RegistrationEntity> {
    PageData<RegistrationEntity> page(Map<String, Object> params);


    void insertEntity(RegistrationEntity registrationEntity);

    List<HashMap> getRegistrationAllMyStuList(Map<String, Object> params);

    void pass(Long id, int type);
}
