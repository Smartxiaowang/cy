package com.cybs.bs.modules.student.controller;

import com.cybs.bs.common.utils.Result;
import com.cybs.bs.modules.job.utils.DateUtils;
import com.cybs.bs.modules.security.user.SecurityUser;
import com.cybs.bs.modules.student.dao.RegistrationDao;
import com.cybs.bs.modules.student.entity.WarringDto;
import org.joda.time.DateTime;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

/**
 * @ClassName WarringAndCountController
 * @Description 统计与告警
 * @Version 1.0
 **/
@RestController
public class WarringAndCountController {
    @Resource
    private RegistrationDao baseDao;

    @GetMapping("/getWarring")
    //获取告警参数
    public Result getWarring() {
        ArrayList<String> dateList = new ArrayList<>(7);
        Date[] weekStartAndEnd = DateUtils.getWeekStartAndEndAll();
        //获取本周的7天的数据例如 2022-10-17~2022-10-23
        for (Date date : weekStartAndEnd) {
            dateList.add(DateUtils.format(date));
            //添加到数据集合中
        }
        List<String> myStuIdList = baseDao.getMyStuIdList(SecurityUser.getUserId());
        //跟据当前登录的用户查询他所有的学生id
        List<WarringDto> dataList = new ArrayList<>();
        for (int i = 0; i < myStuIdList.size(); i++) {
            HashMap<String, String> userInfo = baseDao.getUserInfo(myStuIdList.get(i));
            //查询个人信息
            List<HashMap<String, String>> warring = baseDao.getWarring(myStuIdList.get(i)
                    , dateList.get(0)
                    , dateList.get(1)
                    , dateList.get(2)
                    , dateList.get(3)
                    , dateList.get(4)
                    , dateList.get(5)
                    , dateList.get(6)
            );
            //查询当日没有体温填报的学生
            for (int s = 0; s < warring.size(); s++) {
                HashMap<String, String> data = warring.get(s);
                WarringDto warringDto = new WarringDto();
                warringDto.setRealname(userInfo.get("real_name"));
                warringDto.setCreate_date(data.get("create_date"));
                warringDto.setDepart(userInfo.get("depart"));
                dataList.add(warringDto);
            }
            //数据集合封装
        }
        //数据构造返回
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("total", dataList.size());
        stringObjectHashMap.put("list", dataList);
        return new Result().ok(stringObjectHashMap);
    }


    @GetMapping("/count")
    //统计
    public Result count(@RequestParam(name = "date", required = false) String date) {
        List<String> myStuIdList = baseDao.getMyStuIdList(SecurityUser.getUserId());
        //时间处理
        if (date == null || date.equals("")) {
            int monthOfYear = new DateTime().getMonthOfYear();
            int year = new DateTime().getYear();
            if (monthOfYear < 10) {
                date = year + "-0" + monthOfYear;
            } else date = year + "-" + monthOfYear;
        }
        //获取对应的时间
        String[] dayOfMonth = DateUtils.getDayOfMonth(date);
        int i = Integer.parseInt(dayOfMonth[3]);
        //获取本月天数
        List<HashMap<String, String>> dataList = new ArrayList<>();
        List<HashMap<String, String>> departuretimelist = baseDao.getDeparturetime(myStuIdList, dayOfMonth[0] + "%");
        //获取出行时间及人数
        List<HashMap<String, String>> destinationtimelist = baseDao.getDestinationtime(myStuIdList, dayOfMonth[0] + "%");
        //处理查出来的数据获取数据集=》获取归来时间及人数
        getDataList(dayOfMonth[0], i, dataList, departuretimelist, destinationtimelist);
        //处理集合变成前端需要的
        ArrayList<ArrayList<HashMap<String, String>>> hashMaps = new ArrayList<>();
        dataList.forEach(x -> {
            ArrayList<HashMap<String, String>> hashMaps2 = new ArrayList<>();
            HashMap<String, String> d1 = new HashMap<>();
            HashMap<String, String> d2 = new HashMap<>();
            d1.put("name","出校人数");
            d1.put("value",x.get("gopeople"));
            d2.put("name","入校人数");
            d2.put("value",x.get("inpeople"));
            hashMaps2.add(d1);
            hashMaps2.add(d2);
            hashMaps.add(hashMaps2);
        });
        HashMap<String, Object> resData = new HashMap<>();
        resData.put("list",hashMaps);
        resData.put("bdate",dayOfMonth[1]);
        resData.put("ebdate",dayOfMonth[2]);
        resData.put("rdate",dayOfMonth[0]);
        //处理结束 返回数据
        return new Result<>().ok(resData);
    }
    //处理集合变成前端需要的
    private void getDataList(String s, int i, List<HashMap<String, String>> datassss, List<HashMap<String, String>> departuretimelist, List<HashMap<String, String>> destinationtimelist) {
        for (int x = 1; x <= i; x++) {
            HashMap<String, String> stringObjectHashMap = new HashMap<>();
            String day = x < 10 ? "0" + x : x + "";
            stringObjectHashMap.put("departuretime", s + "-" + day);
            stringObjectHashMap.put("gopeople", "0");
            stringObjectHashMap.put("destinationtime", s + "-" + day);
            stringObjectHashMap.put("inpeople", "0");
            datassss.add(stringObjectHashMap);
        }
        for (int b = 0; b < departuretimelist.size(); b++) {
            HashMap<String, String> stringObjectHashMap = departuretimelist.get(b);
            HashMap<String, String> stringObjectHashMap2 = destinationtimelist.get(b);
            String departuretime = String.valueOf(stringObjectHashMap.get("departuretime"));
            String destinationtime = String.valueOf(stringObjectHashMap2.get("destinationtime"));
            for (HashMap<String, String> next : datassss) {
                String departuretime1 = next.get("departuretime");
                if (departuretime1.equals(departuretime)) {
                    next.put("gopeople", stringObjectHashMap.get("gopeople"));
                }
                String destinationtime1 = next.get("destinationtime");
                if (destinationtime1.equals(destinationtime)) {
                    next.put("inpeople", stringObjectHashMap2.get("inpeople"));
                }
            }
        }
    }
}
