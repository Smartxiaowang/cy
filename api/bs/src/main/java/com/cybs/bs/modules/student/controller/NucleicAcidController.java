package com.cybs.bs.modules.student.controller;

import com.cybs.bs.common.Global.R;
import com.cybs.bs.common.annotation.LogOperation;
import com.cybs.bs.common.utils.PageData;
import com.cybs.bs.common.utils.Result;
import com.cybs.bs.modules.security.user.SecurityUser;
import com.cybs.bs.modules.security.user.UserDetail;
import com.cybs.bs.modules.student.entity.NucleicAcidEntity;
import com.cybs.bs.modules.student.service.NucleicAcidService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName NucleicAcidController
 * @Description 核酸登记模块
 * @Version 1.0
 **/
@RestController
public class NucleicAcidController {
    @Autowired
    private NucleicAcidService acidService;


    @GetMapping("/getAcidList")
    //获取本人核酸列表
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
    //获取学生核酸列表
    public Result<HashMap<String, Object>> getAcidAllMyStuList(@RequestParam(name = "date",required = false) String date) {
        //登录的用户信息
        UserDetail user = SecurityUser.getUser();
        //构造查询参数
        Map<String, Object> params = new HashMap<>();
        Long uid = user.getId();
        params.put("uid", uid);
        params.put("date", date);
        //查询数据
        List<HashMap> dataList = acidService.getAcidAllMyStuList(params);
        //构造数据结构用于返回数据
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("total",dataList.size());
        stringObjectHashMap.put("list",dataList);
        return new Result<HashMap<String, Object>>().ok(stringObjectHashMap);
    }

    @PostMapping("/saveNucleicAcidInfo")
    //保存核酸信息
    public R saveNucleicAcidInfo(@RequestBody NucleicAcidEntity nucleicAcidEntity) {
        //获取当前用户id
        Long userId = SecurityUser.getUserId();
        //保存核酸信息
        nucleicAcidEntity.setName(SecurityUser.getUser().getRealName());
        nucleicAcidEntity.setUid(userId);
        nucleicAcidEntity.setCreator(userId);
        //新增核酸数据
        acidService.insertEntity(nucleicAcidEntity);
        return R.ok();
    }

    @PostMapping("/updNucleicAcidInfo")
    //修改核酸登记信息
    public R updNucleicAcidInfo(@RequestBody NucleicAcidEntity nucleicAcidEntity) {
        //获取当前用户id
        Long userId = SecurityUser.getUserId();
        nucleicAcidEntity.setUid(userId);
        nucleicAcidEntity.setCreator(userId);
        //修改核酸登记信息
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
