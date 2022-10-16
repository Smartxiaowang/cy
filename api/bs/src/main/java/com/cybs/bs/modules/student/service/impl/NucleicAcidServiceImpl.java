package com.cybs.bs.modules.student.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cybs.bs.common.Global.R;
import com.cybs.bs.common.utils.BaseServiceImpl;
import com.cybs.bs.common.utils.Constant;
import com.cybs.bs.common.utils.ConvertUtils;
import com.cybs.bs.common.utils.PageData;
import com.cybs.bs.modules.job.utils.DateUtils;
import com.cybs.bs.modules.security.user.SecurityUser;
import com.cybs.bs.modules.student.dao.BodyTemperatureDao;
import com.cybs.bs.modules.student.dao.NucleicAcidDao;
import com.cybs.bs.modules.student.entity.BodyTemperatureEntity;
import com.cybs.bs.modules.student.entity.NucleicAcidEntity;
import com.cybs.bs.modules.student.service.BodyTemperatureService;
import com.cybs.bs.modules.student.service.NucleicAcidService;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class NucleicAcidServiceImpl extends BaseServiceImpl<NucleicAcidDao, NucleicAcidEntity> implements NucleicAcidService {
    @Autowired
    private NucleicAcidDao nucleicAcidDao;


    @Override
    public List<HashMap> getAcidAllMyStuList(Map<String, Object> params) {
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
        return nucleicAcidDao.getAcidAllMyStuList(userid,date);
    }

    @Override
    @Transactional
    public void insertEntity(NucleicAcidEntity nucleicAcidEntity) {
        String dname = nucleicAcidDao.getDepartName(SecurityUser.getDeptId());
        nucleicAcidEntity.setDepart(dname);
        nucleicAcidDao.insertEntity(nucleicAcidEntity);
    }

    @Override
    public PageData<NucleicAcidEntity> page(Map<String, Object> params) {
        QueryWrapper<NucleicAcidEntity> objectQueryWrapper = new QueryWrapper<NucleicAcidEntity>();
        objectQueryWrapper.eq("uid",params.get("uid"));
        if (params.get("id")!=null) {
            objectQueryWrapper.eq("id",params.get("id"));
        }
        if (params.get("date") != null) {
            String date = (String) params.get("date");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parse = DateUtils.parse(date, "yyyy-MM-dd");
            date = simpleDateFormat.format(DateUtils.addDateDays(parse, 1));
            objectQueryWrapper.eq("create_date", date);
        }
        IPage<NucleicAcidEntity> page = baseDao.selectPage(
                getPage(params, Constant.CREATE_DATE, false),objectQueryWrapper
        );
        return getPageData(page, NucleicAcidEntity.class);
    }
    protected <T> PageData<T> getPageData(List<?> list, long total, Class<T> target){
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
