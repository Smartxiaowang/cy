package com.cybs.bs.modules.student.controller;

import com.cybs.bs.common.Global.R;
import com.cybs.bs.common.Global.RedisCache;
import com.cybs.bs.common.Global.ThreadPoolHelp;
import com.cybs.bs.common.utils.PageData;
import com.cybs.bs.common.utils.Result;
import com.cybs.bs.modules.security.user.SecurityUser;
import com.cybs.bs.modules.security.user.UserDetail;
import com.cybs.bs.modules.student.entity.BodyTemperatureEntity;
import com.cybs.bs.modules.student.service.BodyTemperatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @ClassName BodyTemperature
 * @Description 体温登记模块
 * @Version 1.0
 **/
@RestController
@RequestMapping("/bt")
public class BodyTemperatureController {
    @Autowired
    private BodyTemperatureService ownerService;

    @Autowired
    private ThreadPoolHelp threadPoolHelp;
    @Autowired
    private RedisCache redisCache;

    @GetMapping("/getBodyTemperatureListByUid")
    //获取体温列表
    public Result<PageData<BodyTemperatureEntity>> getBodyTemperatureList(@RequestParam Map<String, Object> params) {
        //登录的用户信息
        UserDetail user = SecurityUser.getUser();
        Long id = user.getId();
        params.put("u_id", id);
        System.out.println(user.getDeptId());
        PageData<BodyTemperatureEntity> page = ownerService.page(params);
        return new Result<PageData<BodyTemperatureEntity>>().ok(page);
    }

    @PostMapping("/saveOwnerInfo")
    public R saveOwnerInfo() {
        return R.ok();
    }

    @PostMapping("/updOwnerInfo")
    public R updOwnerInfo() {
        return R.ok();
    }

    @DeleteMapping("/delOwnerInfo")
    public R delOwnerInfo() {
        return R.ok();
    }
}
