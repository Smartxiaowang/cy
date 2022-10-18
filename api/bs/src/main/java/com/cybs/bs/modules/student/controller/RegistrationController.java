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

/**
 * @ClassName RegistrationController
 * @Description 行程登记模块
 * @Version 1.0
 **/
@RestController
public class RegistrationController {
    @Autowired
    private RegistrationService registrationService;

    @GetMapping("/getRegistrationAllMyStuList")
    //查询班级内学生的信息
    public Result<HashMap<String, Object>> getRegistrationAllMyStuList(@RequestParam(name = "date", required = false) String date) {
        //登录的用户信息
        UserDetail user = SecurityUser.getUser();
        //构造查询参数
        Map<String, Object> params = new HashMap<>();
        Long uid = user.getId();
        params.put("uid", uid);
        params.put("date", date);
        //数据查询
        List<HashMap> dataList = registrationService.getRegistrationAllMyStuList(params);
        //构造数据就够用于返回数据
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("total", dataList.size());
        stringObjectHashMap.put("list", dataList);
        return new Result<HashMap<String, Object>>().ok(stringObjectHashMap);
    }



    @GetMapping("/passRegistrationInfo")
    //行程审核
    public R passRegistrationInfo(@RequestParam(name = "id", required = false) Long id,
                                  @RequestParam(name = "type", required = false) int type) {
        //type代表通过与驳回两种状态
        registrationService.pass(id, type);
        return R.ok();
    }

    @GetMapping("/getRegistrationList")
    //获取个人行程信息
    public Result<PageData<RegistrationEntity>> getAcidList(@RequestParam(name = "id", required = false) Long id, @RequestParam(name = "date", required = false) String date) {
        //登录的用户信息
        UserDetail user = SecurityUser.getUser();
        //构造查询参数
        Map<String, Object> params = new HashMap<>();
        Long uid = user.getId();
        params.put("uid", uid);
        if (id != null)
            params.put("id", id);
        if (StringUtils.isNotEmpty(date))
            params.put("date", date);
        //数据查询
        PageData<RegistrationEntity> page = registrationService.page(params);
        return new Result<PageData<RegistrationEntity>>().ok(page);
    }

    @PostMapping("/saveRegistrationInfo")
    //保存行程信息
    public R saveRegistrationInfo(@RequestBody RegistrationEntity registrationEntity) {
        //登录的用户信息
        Long userId = SecurityUser.getUserId();
        //构造参数
        registrationEntity.setUid(userId);
        registrationEntity.setCreator(userId);
        registrationEntity.setName(SecurityUser.getUser().getRealName());
        registrationEntity.setCreateDate(new Date());
        registrationService.insertEntity(registrationEntity);
        return R.ok();
    }

    @PostMapping("/updRegistrationInfo")
    //修改行程信息
    public R updRegistrationInfo(@RequestBody RegistrationEntity registrationEntity) {
        //获取用户信息
        Long userId = SecurityUser.getUserId();
        //构造参数
        registrationEntity.setUid(userId);
        registrationEntity.setCreator(userId);
        registrationEntity.setCreateDate(new Date());
        //如果已经被审核那么无法修改 返回error message
        if (registrationEntity.getAudit().equals("通过") || registrationEntity.getAudit().equals("驳回")) {
            return R.error().message("已经被审核，无法修改");
        }
        registrationService.updateById(registrationEntity);
        return R.ok();
    }


    @DeleteMapping("/delRegistrationInfo")
    //删除行程信息
    public R delRegistrationInfo(@RequestBody List<String> id) {
        if (registrationService.deleteBatchIds(id)) {
            return R.ok();
        }
        return R.error();
    }
}
