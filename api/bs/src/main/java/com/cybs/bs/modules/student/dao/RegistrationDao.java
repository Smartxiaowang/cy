package com.cybs.bs.modules.student.dao;

import com.cybs.bs.common.dao.BaseDao;
import com.cybs.bs.modules.student.entity.RegistrationEntity;
import org.apache.ibatis.annotations.*;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface RegistrationDao extends BaseDao<RegistrationEntity> {


    @Insert("INSERT INTO journey_registration ( id, uid, departure," +
            " destination, departuretime, destinationtime, audit, remake, creator, create_date,name,depart )" +
            "   VALUES (null,#{uid}, #{departure}, #{destination}," +
            " #{departuretime}, #{destinationtime},\"未审核\",#{remake},#{creator},#{createDate},#{name},#{depart})")
    void insertEntity(RegistrationEntity registrationEntity);

    @Select("<script>" +
            "SELECT\n" +
            "\t* \n" +
            "FROM\n" +
            "\tjourney_registration \n" +
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
    List<HashMap> getregistrationList(@Param("userid") Long userid,
                                      @Param("date") String date);

    @Update("update journey_registration set audit = #{audit} where id = #{id}")
    void updateByUserId(@Param("id")Long id,@Param("audit") String audit);
}
