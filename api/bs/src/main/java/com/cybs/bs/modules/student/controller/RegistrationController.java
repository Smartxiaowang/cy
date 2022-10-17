package com.cybs.bs.modules.student.controller;

import com.cybs.bs.common.Global.R;
import com.cybs.bs.common.utils.PageData;
import com.cybs.bs.common.utils.Result;
import com.cybs.bs.modules.security.user.SecurityUser;
import com.cybs.bs.modules.security.user.UserDetail;
import com.cybs.bs.modules.student.entity.RegistrationEntity;
import com.cybs.bs.modules.student.service.RegistrationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class RegistrationController {
    @Autowired
    private RegistrationService registrationService;

    @GetMapping("/getRegistrationAllMyStuList")
    public Result<HashMap<String, Object>> getRegistrationAllMyStuList(@RequestParam(name = "date", required = false) String date) {
        //登录的用户信息
        UserDetail user = SecurityUser.getUser();
        Map<String, Object> params = new HashMap<>();
        Long uid = user.getId();
        params.put("uid", uid);
        params.put("date", date);
        //PageData<BodyTemperatureEntity> page = bodyService.page(params);
        List<HashMap> dataList = registrationService.getRegistrationAllMyStuList(params);
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("total", dataList.size());
        stringObjectHashMap.put("list", dataList);
        return new Result<HashMap<String, Object>>().ok(stringObjectHashMap);
    }


    //行程审核
    @GetMapping("/passRegistrationInfo")
    public R passRegistrationInfo(@RequestParam(name = "id", required = false) Long id,
                                  @RequestParam(name = "type", required = false) int type) {
        //登录的用户信息
        registrationService.pass(id, type);
        return R.ok();
    }

    @GetMapping("/getRegistrationList")
    public Result<PageData<RegistrationEntity>> getAcidList(@RequestParam(name = "id", required = false) Long id,
                                                            @RequestParam(name = "date", required = false) String date) {
        //登录的用户信息
        UserDetail user = SecurityUser.getUser();
        Map<String, Object> params = new HashMap<>();
        Long uid = user.getId();
        params.put("uid", uid);
        if (id != null)
            params.put("id", id);
        if (StringUtils.isNotEmpty(date))
            params.put("date", date);
        PageData<RegistrationEntity> page = registrationService.page(params);
        return new Result<PageData<RegistrationEntity>>().ok(page);
    }

    @PostMapping("/saveRegistrationInfo")
    public R saveRegistrationInfo(@RequestBody RegistrationEntity registrationEntity) {
        Long userId = SecurityUser.getUserId();
        registrationEntity.setUid(userId);
        registrationEntity.setCreator(userId);
        registrationEntity.setName(SecurityUser.getUser().getRealName());
        registrationEntity.setCreateDate(new Date());
        registrationService.insertEntity(registrationEntity);
        return R.ok();
    }

    @PostMapping("/updRegistrationInfo")
    public R updRegistrationInfo(@RequestBody RegistrationEntity registrationEntity) {
        Long userId = SecurityUser.getUserId();
        registrationEntity.setUid(userId);
        registrationEntity.setCreator(userId);
        registrationEntity.setCreateDate(new Date());
        if (registrationEntity.getAudit().equals("通过") || registrationEntity.getAudit().equals("驳回")) {
            return R.error().message("已经被审核，无法修改");
        }
        registrationService.updateById(registrationEntity);
        return R.ok();
    }


    @DeleteMapping("/delRegistrationInfo")
    public R delRegistrationInfo(@RequestBody List<String> id) {
        if (registrationService.deleteBatchIds(id)) {
            return R.ok();
        }
        return R.error();
    }
}
