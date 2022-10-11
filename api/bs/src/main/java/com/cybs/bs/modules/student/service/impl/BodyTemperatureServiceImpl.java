package com.cybs.bs.modules.student.service.impl;

import com.cybs.bs.common.utils.BaseServiceImpl;
import com.cybs.bs.common.utils.Constant;
import com.cybs.bs.common.utils.ConvertUtils;
import com.cybs.bs.common.utils.PageData;
import com.cybs.bs.modules.student.dao.BodyTemperatureDao;
import com.cybs.bs.modules.student.entity.BodyTemperatureEntity;
import com.cybs.bs.modules.student.service.BodyTemperatureService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.List;
import java.util.Map;

/**
 * @ClassName OwnerServiceImpl
 * @Description TODO
 * @Author Dear lin
 * @Date 17:21 2022/7/29
 * @Version 1.0
 **/
@Service
public class BodyTemperatureServiceImpl extends BaseServiceImpl<BodyTemperatureDao, BodyTemperatureEntity> implements BodyTemperatureService {
    @Autowired
    private BodyTemperatureDao bodyTemperatureDao;

    @Override
    public PageData<BodyTemperatureEntity> page(Map<String, Object> params) {
        QueryWrapper<BodyTemperatureEntity> objectQueryWrapper = new QueryWrapper<BodyTemperatureEntity>();
        objectQueryWrapper.eq("u_id",params.get("u_id"));
        IPage<BodyTemperatureEntity> page = baseDao.selectPage(
                getPage(params, Constant.CREATE_DATE, false),objectQueryWrapper
        );
        return getPageData(page, BodyTemperatureEntity.class);
    }
    protected <T> PageData<T> getPageData(List<?> list, long total, Class<T> target){
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization(){
            @Override
            public void afterCommit() {

            }

            @Override
            public void beforeCommit(boolean readOnly) {

            }
        });
        List<T> targetList = ConvertUtils.sourceToTarget(list, target);

        return new PageData<>(targetList, total);
    }

    protected <T> PageData<T> getPageData(IPage page, Class<T> target){
        return getPageData(page.getRecords(), page.getTotal(), target);
    }
    private QueryWrapper<BodyTemperatureEntity> getWrapper(Map<String, Object> params){
        QueryWrapper<BodyTemperatureEntity> wrapper = getBodyTemperatureEntityQueryWrapper();
        return wrapper;
    }

    @NotNull
    private QueryWrapper<BodyTemperatureEntity> getBodyTemperatureEntityQueryWrapper() {
        return new QueryWrapper<>();
    }
}
