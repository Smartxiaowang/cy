package com.cybs.bs.modules.student.service.impl;

import com.cybs.bs.common.Global.R;
import com.cybs.bs.common.utils.BaseServiceImpl;
import com.cybs.bs.common.utils.Constant;
import com.cybs.bs.common.utils.ConvertUtils;
import com.cybs.bs.common.utils.PageData;
import com.cybs.bs.modules.job.utils.DateUtils;
import com.cybs.bs.modules.security.user.SecurityUser;
import com.cybs.bs.modules.student.dao.BodyTemperatureDao;
import com.cybs.bs.modules.student.entity.BodyTemperatureEntity;
import com.cybs.bs.modules.student.service.BodyTemperatureService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.security.Security;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName BodyTemperatureServiceImpl
 * @Description 体温列表实现类
 * @Version 1.0
 **/
@Service
public class BodyTemperatureServiceImpl extends BaseServiceImpl<BodyTemperatureDao, BodyTemperatureEntity> implements BodyTemperatureService {
    @Autowired
    private BodyTemperatureDao bodyTemperatureDao;

    @Override
    //获取我班级学生的体温列表
    public List<HashMap> getBodyTempAllMyStuList(Map<String, Object> params) {
        //构造查询参数
        Long userid = (Long) params.get("uid");
        String date = (String) params.get("date");
        if (StringUtils.isNotEmpty(date)) {
            //格式化时间
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parse = DateUtils.parse(date, "yyyy-MM-dd");
            date = simpleDateFormat.format(DateUtils.addDateDays(parse, 1));
        }else {
            date = null;
        }
        //数据库交互
        List<HashMap> bodyTempAllMyStuList = bodyTemperatureDao.getBodyTempAllMyStuList(userid, date);
        return bodyTempAllMyStuList;
    }

    @Override
    @Transactional
    //插入数据
    public R insertByEntity(BodyTemperatureEntity bodyTemperatureEntity) {
        try {
            //数据库交互
            String dname = bodyTemperatureDao.getDepartName(SecurityUser.getDeptId());
            bodyTemperatureEntity.setDepart(dname);
            bodyTemperatureDao.insertbyEntity(bodyTemperatureEntity);
            return R.ok();
        } catch (Exception e) {
            return R.error().message("当日已登记");
        }
    }

    @Override
    //查询列表数据
    public PageData<BodyTemperatureEntity> page(Map<String, Object> params) {
        //构造参数
        QueryWrapper<BodyTemperatureEntity> objectQueryWrapper = new QueryWrapper<BodyTemperatureEntity>();
        objectQueryWrapper.eq("uid", params.get("uid"));
        if (params.get("id") != null) {
            objectQueryWrapper.eq("id", params.get("id"));
        }
        if (params.get("date") != null) {
            String date = (String) params.get("date");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parse = DateUtils.parse(date, "yyyy-MM-dd");
            date = simpleDateFormat.format(DateUtils.addDateDays(parse, 1));
            objectQueryWrapper.eq("create_date", date);
        }
        //获取结果集
        IPage<BodyTemperatureEntity> page = baseDao.selectPage(
                getPage(params, Constant.CREATE_DATE, false), objectQueryWrapper
        );
        return getPageData(page, BodyTemperatureEntity.class);

    }

    protected <T> PageData<T> getPageData(List<?> list, long total, Class<T> target) {
        List<T> targetList = ConvertUtils.sourceToTarget(list, target);

        return new PageData<>(targetList, total);
    }

    protected <T> PageData<T> getPageData(IPage page, Class<T> target) {
        return getPageData(page.getRecords(), page.getTotal(), target);
    }

    private QueryWrapper<BodyTemperatureEntity> getWrapper(Map<String, Object> params) {
        QueryWrapper<BodyTemperatureEntity> wrapper = getBodyTemperatureEntityQueryWrapper();
        return wrapper;
    }

    @NotNull
    private QueryWrapper<BodyTemperatureEntity> getBodyTemperatureEntityQueryWrapper() {
        return new QueryWrapper<>();
    }
}
