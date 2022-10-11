
package com.cybs.bs.modules.sys.dao;

import com.cybs.bs.common.dao.BaseDao;
import com.cybs.bs.modules.sys.entity.DictType;
import com.cybs.bs.modules.sys.entity.SysDictTypeEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 字典类型
 *
 */
@Mapper
public interface SysDictTypeDao extends BaseDao<SysDictTypeEntity> {

    /**
     * 字典类型列表
     */
    List<DictType> getDictTypeList();

}
