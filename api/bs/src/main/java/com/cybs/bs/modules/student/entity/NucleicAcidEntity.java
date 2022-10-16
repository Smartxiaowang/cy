package com.cybs.bs.modules.student.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cybs.bs.common.utils.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("nucleic_acid")
public class NucleicAcidEntity extends BaseEntity {
    private Long uid;
    private String nucleicacid;
    private String isunusual;
    private String remake;
    private String name;
    private String depart;
}
