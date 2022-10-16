package com.cybs.bs.modules.student.dao;

import com.cybs.bs.common.dao.BaseDao;
import com.cybs.bs.modules.student.entity.BodyTemperatureEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface BodyTemperatureDao extends BaseDao<BodyTemperatureEntity> {
    @Insert("INSERT INTO temperature VALUES  ( null, #{uid},  #{inschool}," +
            "  #{symptom}, #{isconfirmed},  #{isdanger},  #{temperature},  #{place},  #{remake},  #{creator},  #{createDate}" +
            ",  #{name},  #{depart} )")
    void insertbyEntity(BodyTemperatureEntity bodyTemperatureEntity);

    @Select("<script>" +
            "SELECT\n" +
            "\t* \n" +
            "FROM\n" +
            "\ttemperature \n" +
            "WHERE\n" +
            "\tuid IN (\n" +
            "\tSELECT\n" +
            "\t\tid \n" +
            "\tFROM\n" +
            "\t\tsys_user \n" +
            "\tWHERE\n" +
            "\tdept_id IN ( SELECT sys_role_data_scope.dept_id FROM sys_role_user INNER JOIN sys_role_data_scope ON sys_role_user.role_id = sys_role_data_scope.role_id " +
            "WHERE sys_role_user.user_id = #{userid} ) \n" +
            "\t) <if test=\"date != null \">\n" +
            "    AND create_date = #{date}\n" +
            "  </if> \n" +
            "\tORDER BY create_date desc LIMIT 0,10" +
            "</script>")
    List<HashMap> getBodyTempAllMyStuList(@Param("userid") Long userid,
                                          @Param("date") String date);



}
