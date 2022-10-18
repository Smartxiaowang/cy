package com.cybs.bs.modules.student.controller;

import com.cybs.bs.common.Global.R;
import com.cybs.bs.common.annotation.LogOperation;
import com.cybs.bs.common.utils.PageData;
import com.cybs.bs.common.utils.Result;
import com.cybs.bs.modules.security.user.SecurityUser;
import com.cybs.bs.modules.security.user.UserDetail;
import com.cybs.bs.modules.student.entity.BodyTemperatureEntity;
import com.cybs.bs.modules.student.service.BodyTemperatureService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @ClassName BodyTemperature
 * @Description 体温登记模块
 * @Version 1.0
 **/
@RestController
public class BodyTemperatureController {

    //spring注解注入接口
    @Autowired
    private BodyTemperatureService bodyService;

    @GetMapping("/getBodyTempByUid")
    //获取个人体温列表
    public Result<PageData<BodyTemperatureEntity>> getBodyTemperatureList(@RequestParam(name = "id" ,required = false) Long id,@RequestParam(name = "date" ,required = false) String date) {
        //登录的用户信息
        UserDetail user = SecurityUser.getUser();
        //构造参数用于查询数据库中数据
        Map<String, Object> params = new HashMap<>();
        Long uid = user.getId();
        params.put("uid", uid);
        if (StringUtils.isNotEmpty(date)) {
            params.put("date",date);
        }
        if (id != null )
            params.put("id",id);
        //进行数据查询
        PageData<BodyTemperatureEntity> page = bodyService.page(params);
        return new Result<PageData<BodyTemperatureEntity>>().ok(page);
    }

    @GetMapping("/getBodyTempAllMyStuList")
    //获取我班级学生的体温列表
    public Result<HashMap<String, Object>> getBodyTempAllMyStuList(@RequestParam(name = "date",required = false) String date) {
        //登录的用户信息
        UserDetail user = SecurityUser.getUser();
        //构造参数用于查询数据库中数据
        Map<String, Object> params = new HashMap<>();
        Long uid = user.getId();
        params.put("uid", uid);
        params.put("date", date);
        //进行数据查询
        List<HashMap> dataList = bodyService.getBodyTempAllMyStuList(params);
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        //数据封装用于前端展示
        stringObjectHashMap.put("total",dataList.size());
        stringObjectHashMap.put("list",dataList);
        return new Result<HashMap<String, Object>>().ok(stringObjectHashMap);
    }

    @LogOperation("新增体温登记信息")
    //新增体温登记信息
    @PostMapping("/saveBodyTemperatureInfo")
    public R saveOwnerInfo(@RequestBody BodyTemperatureEntity bodyTemperatureEntity) {
        //前端传递数据进行封装
        Long userId = SecurityUser.getUserId();
        bodyTemperatureEntity.setName(SecurityUser.getUser().getRealName());
        bodyTemperatureEntity.setUid(SecurityUser.getUserId());
        bodyTemperatureEntity.setCreator(userId);
        return  bodyService.insertByEntity(bodyTemperatureEntity);
    }
    @LogOperation("修改体温登记信息")
    @PostMapping("/updBodyTemInfo")
    //修改体温登记信息
    public R updOwnerInfo(@RequestBody BodyTemperatureEntity bodyTemperatureEntity) {
        //看是否能修改数据 成功ok 失败 error
        if (bodyService.updateById(bodyTemperatureEntity)) {
            return R.ok();
        }
        return R.error().message("当日已登记");
    }

    @LogOperation("删除体温登记信息")
    @DeleteMapping("/delBodyTemInfo")
    //删除体温登记信息
    public R delOwnerInfo(@RequestBody List<String> id) {
        //删除使用id参数
        if (bodyService.deleteBatchIds(id)){
            return R.ok();
        }
        return R.error();
    }
}
