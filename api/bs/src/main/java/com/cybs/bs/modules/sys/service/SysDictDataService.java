
package com.cybs.bs.modules.sys.service;

import com.cybs.bs.common.utils.BaseService;
import com.cybs.bs.common.utils.PageData;
import com.cybs.bs.modules.sys.dto.SysDictDataDTO;
import com.cybs.bs.modules.sys.entity.SysDictDataEntity;

import java.util.Map;

/**
 * 数据字典
 *
 */
public interface SysDictDataService extends BaseService<SysDictDataEntity> {

    PageData<SysDictDataDTO> page(Map<String, Object> params);

    SysDictDataDTO get(Long id);

    void save(SysDictDataDTO dto);

    void update(SysDictDataDTO dto);

    void delete(Long[] ids);

}