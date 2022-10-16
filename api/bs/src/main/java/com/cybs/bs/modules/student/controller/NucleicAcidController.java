package com.cybs.bs.modules.student.controller;

import com.cybs.bs.common.Global.R;
import com.cybs.bs.common.annotation.LogOperation;
import com.cybs.bs.common.utils.PageData;
import com.cybs.bs.common.utils.Result;
import com.cybs.bs.modules.security.user.SecurityUser;
import com.cybs.bs.modules.security.user.UserDetail;
import com.cybs.bs.modules.student.entity.BodyTemperatureEntity;
import com.cybs.bs.modules.student.entity.NucleicAcidEntity;
import com.cybs.bs.modules.student.service.NucleicAcidService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class NucleicAcidController {
    @Autowired
    private NucleicAcidService acidService;
    @GetMapping("/getAcidList")
    //获取体温列表
    public Result<PageData<NucleicAcidEntity>> getAcidList(@RequestParam(name = "id" ,required = false) Long id
            ,@RequestParam(name = "date" ,required = false) String date) {
        //登录的用户信息
        UserDetail user = SecurityUser.getUser();
        Map<String, Object> params = new HashMap<>();
        Long uid = user.getId();
        params.put("uid", uid);
        if (id != null )
            params.put("id",id);
        if (StringUtils.isNotEmpty(date))
            params.put("date",date);
        PageData<NucleicAcidEntity> page = acidService.page(params);
        return new Result<PageData<NucleicAcidEntity>>().ok(page);
    }

    @GetMapping("/getAcidAllMyStuList")
    public Result<HashMap<String, Object>> getAcidAllMyStuList(@RequestParam(name = "date",required = false) String date) {
        //登录的用户信息
        UserDetail user = SecurityUser.getUser();
        Map<String, Object> params = new HashMap<>();
        Long uid = user.getId();
        params.put("uid", uid);
        params.put("date", date);
        //PageData<BodyTemperatureEntity> page = bodyService.page(params);
        List<HashMap> dataList = acidService.getAcidAllMyStuList(params);
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("total",dataList.size());
        stringObjectHashMap.put("list",dataList);
        return new Result<HashMap<String, Object>>().ok(stringObjectHashMap);
    }

    //@LogOperation("新增核酸登记信息")
    @PostMapping("/saveNucleicAcidInfo")
    public R saveNucleicAcidInfo(@RequestBody NucleicAcidEntity nucleicAcidEntity) {
        Long userId = SecurityUser.getUserId();
        nucleicAcidEntity.setName(SecurityUser.getUser().getRealName());
        nucleicAcidEntity.setUid(userId);
        nucleicAcidEntity.setCreator(userId);
        acidService.insertEntity(nucleicAcidEntity);
        return R.ok();
    }
   // @LogOperation("修改核酸登记信息")
    @PostMapping("/updNucleicAcidInfo")
    public R updNucleicAcidInfo(@RequestBody NucleicAcidEntity nucleicAcidEntity) {
        Long userId = SecurityUser.getUserId();
        nucleicAcidEntity.setUid(userId);
        nucleicAcidEntity.setCreator(userId);
        acidService.updateById(nucleicAcidEntity);
        return R.ok();
    }

    @LogOperation("删除核酸登记信息")
    @DeleteMapping("/delNucleicAcidInfo")
    public R delOwnerInfo(@RequestBody List<String> id) {
        if (acidService.deleteBatchIds(id)){
            return R.ok();
        }
        return R.error();
    }
}
