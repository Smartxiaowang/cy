package com.cybs.bs.modules.student.controller;

import com.cybs.bs.common.Global.R;
import com.cybs.bs.common.Global.RedisCache;
import com.cybs.bs.common.Global.ThreadPoolHelp;
import com.cybs.bs.common.utils.PageData;
import com.cybs.bs.common.utils.Result;
import com.cybs.bs.modules.student.entity.BodyTemperatureEntity;
import com.cybs.bs.modules.student.service.BodyTemperatureService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @ClassName OwnerController
 * @Description 业主信息登记
 * @Author Dear lin
 * @Date 16:07 2022/7/29
 * @Version 1.0
 **/
@RestController
@RequestMapping("/owner")
@Api(tags = "业主信息登记")
public class OwnerController {
    @Autowired
    private BodyTemperatureService ownerService;

    @Autowired
    private ThreadPoolHelp threadPoolHelp;
    @Autowired
    private RedisCache redisCache;

    @GetMapping("/getOwnerList")
    public Result<PageData<BodyTemperatureEntity>> getOwnerList(@RequestParam Map<String, Object> params) {
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
