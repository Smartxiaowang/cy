package com.cybs.bs.modules.student.entity;

import com.cybs.bs.common.utils.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName BodyTemperature
 * @Description 体温登记实体类
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_declaration")
public class BodyTemperatureEntity extends BaseEntity {
    
    private String name;
    private String body_temperature;
    private String measure_time;
    private Integer role_id;
    private String administrative_division;
    private Integer u_id;
}
