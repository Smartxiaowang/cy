package com.cybs.bs.modules.student.controller;

import com.cybs.bs.common.utils.PageData;
import com.cybs.bs.common.utils.Result;
import com.cybs.bs.modules.security.user.SecurityUser;
import com.cybs.bs.modules.security.user.UserDetail;
import com.cybs.bs.modules.student.entity.NucleicAcidEntity;
import com.cybs.bs.modules.student.service.NucleicAcidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
public class NucleicAcidController {
    @Autowired
    private NucleicAcidService acidService;
    @GetMapping("/getAcidList")
    //获取体温列表
    public Result<PageData<NucleicAcidEntity>> getBodyTemperatureList(@RequestParam(name = "id" ,required = false) Long id) {
        //登录的用户信息
        UserDetail user = SecurityUser.getUser();
        Map<String, Object> params = new HashMap<>();
        Long uid = user.getId();
        params.put("uid", uid);
        if (id != null )
            params.put("id",id);
        PageData<NucleicAcidEntity> page = acidService.page(params);
        return new Result<PageData<NucleicAcidEntity>>().ok(page);
    }
}
