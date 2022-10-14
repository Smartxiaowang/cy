package com.cybs.bs.modules.student.entity;


import com.cybs.bs.common.utils.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("temperature")
public class BodyTemperatureEntity extends BaseEntity {

    private Long uid;
    private String inschool;
    private String symptom;
    private String isconfirmed;
    private String isdanger;
    private String temperature;
    private String place;
    private String remake;
}
