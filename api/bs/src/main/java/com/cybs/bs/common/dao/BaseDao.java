
package com.cybs.bs.common.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cybs.bs.modules.student.entity.WarringDto;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;

/**
 * 基础Dao
 *
 * @since 1.0.0
 */
public interface BaseDao<T> extends BaseMapper<T> {

    @Select("select name from sys_dept where id = #{deptId}")
    String getDepartName(Long deptId);
    @Select("SELECT\n" +
            "\tid \n" +
            "FROM\n" +
            "\tsys_user \n" +
            "WHERE\n" +
            "\tdept_id IN (\n" +
            "\tSELECT\n" +
            "\t\tsys_role_data_scope.dept_id \n" +
            "\tFROM\n" +
            "\t\tsys_role_user\n" +
            "\tINNER JOIN sys_role_data_scope ON sys_role_user.role_id = sys_role_data_scope.role_id \n" +
            "\tWHERE sys_role_user.user_id = #{userid} " +
            ") ")
    List<String> getMyStuIdList(@Param("userid") Long deptId);

    @Select("" +
            "select * from\n" +
            "(select create_date from temperature WHERE id = -1 union select '${day1}' union select '${day2}' union select '${day3}' union select '${day4}' union select '${day5}' union select '${day6}' union select '${day7}'" +
            ") t where not exists(select create_date from" +
            " temperature u where t.create_date = u.create_date and u.uid = #{uid} );" +
            "")
    List<HashMap<String,String>> getWarring(@Param("uid") String uid,
                                @Param("day1") String day1,
                                @Param("day2") String day2,
                                @Param("day3") String day3,
                                @Param("day4") String day4,
                                @Param("day5") String day5,
                                @Param("day6") String day6,
                                @Param("day7") String day7
    );

    @Select("SELECT\n" +
            "\tsys_dept.`name` as depart, \n" +
            "\tsys_user.real_name\n" +
            "FROM\n" +
            "\tsys_user\n" +
            "\tINNER JOIN\n" +
            "\tsys_dept\n" +
            "\tON \n" +
            "\t\tsys_user.dept_id = sys_dept.id\n" +
            "WHERE\n" +
            "\tsys_user.id = #{s} ")
    HashMap<String, String> getUserInfo(String s);

    @Select("<script>" +
            "SELECT\n" +
            "\tj.departuretime,\n" +
            "\tCOUNT( 1 ) as gopeople\n" +
            "FROM\n" +
            "\tjourney_registration AS j \n" +
            "WHERE\n" +
            "\tj.uid IN " +
            "<foreach item=\"item\" index=\"index\" collection=\"list\"\n" +
            "      open=\"(\" separator=\",\" close=\")\">\n" +
            "        #{item}\n" +
            "  </foreach>" +
            " and j.departuretime like \"${date}\" and audit = \"通过\" \n" +
            "GROUP BY\n" +
            "\tj.departuretime" +
            "</script>")
    List<HashMap<String, String>> getDeparturetime(@Param("list") List<String> myStuIdList,@Param("date") String date);

    @Select("<script>" +
            "SELECT\n" +
            "\tj.destinationtime,\n" +
            "\tCOUNT( 1 ) as inpeople\n" +
            "FROM\n" +
            "\tjourney_registration AS j \n" +
            "WHERE\n" +
            "\tj.uid IN " +
            "<foreach item=\"item\" index=\"index\" collection=\"list\"\n" +
            "      open=\"(\" separator=\",\" close=\")\">\n" +
            "        #{item}\n" +
            "  </foreach>" +
            " and j.departuretime like \"${date}\" and audit = \"通过\"\n" +
            "GROUP BY\n" +
            "\tj.destinationtime" +
            "</script>")
    List<HashMap<String, String>> getDestinationtime(@Param("list") List<String> myStuIdList,@Param("date") String date);
}
