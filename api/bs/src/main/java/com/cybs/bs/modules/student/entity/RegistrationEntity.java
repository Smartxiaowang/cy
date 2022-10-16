package com.cybs.bs.modules.student.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import com.cybs.bs.common.utils.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("journey_registration")
public class RegistrationEntity extends BaseEntity {
    private Long uid;
    private String departure;
    private String destination;
    private String departuretime;
    private String destinationtime;
    private String audit;
    private String remake;
    private String name;
    private String depart;
}
