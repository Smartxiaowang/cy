package com.cybs.bs.modules.student.dao;

import com.cybs.bs.common.dao.BaseDao;
import com.cybs.bs.modules.student.entity.BodyTemperatureEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BodyTemperatureDao extends BaseDao<BodyTemperatureEntity> {
    @Insert("INSERT INTO temperature VALUES  ( null, #{uid},  #{inschool}," +
            "  #{symptom}, #{isconfirmed},  #{isdanger},  #{temperature},  #{place},  #{remake},  #{creator},  #{createDate} )")
    void insertbyEntity(BodyTemperatureEntity bodyTemperatureEntity);
}
