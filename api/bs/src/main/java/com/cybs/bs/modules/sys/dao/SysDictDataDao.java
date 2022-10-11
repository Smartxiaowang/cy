
package com.cybs.bs.modules.sys.dao;

import com.cybs.bs.common.dao.BaseDao;
import com.cybs.bs.modules.sys.entity.DictData;
import com.cybs.bs.modules.sys.entity.SysDictDataEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 字典数
 */
@Mapper
public interface SysDictDataDao extends BaseDao<SysDictDataEntity> {

    /**
     * 字典数据列表
     */
    List<DictData> getDictDataList();
}
