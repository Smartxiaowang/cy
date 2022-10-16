package com.cybs.bs.modules.student.controller;

import com.cybs.bs.common.Global.R;
import com.cybs.bs.common.dao.BaseDao;
import com.cybs.bs.common.utils.Result;
import com.cybs.bs.modules.job.utils.DateUtils;
import com.cybs.bs.modules.security.user.SecurityUser;
import com.cybs.bs.modules.student.dao.RegistrationDao;
import com.cybs.bs.modules.student.entity.RegistrationEntity;
import com.cybs.bs.modules.student.entity.WarringDto;
import org.apache.poi.hssf.record.DVALRecord;
import org.joda.time.DateTime;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;


@RestController
public class WarringAndCountController {
    @Resource
    private RegistrationDao baseDao;

    @GetMapping("/getWarring")
    public Result getWarring() {
        ArrayList<String> dateList = new ArrayList<>(7);
        Date[] weekStartAndEnd = DateUtils.getWeekStartAndEndAll();
        for (Date date : weekStartAndEnd) {
            dateList.add(DateUtils.format(date));
        }
        List<String> myStuIdList = baseDao.getMyStuIdList(SecurityUser.getUserId());
        List<WarringDto> hashMaps = new ArrayList<>();
        for (int i = 0; i < myStuIdList.size(); i++) {
            HashMap<String, String> userInfo = baseDao.getUserInfo(myStuIdList.get(i));
            List<HashMap<String, String>> warring = baseDao.getWarring(myStuIdList.get(i)
                    , dateList.get(0)
                    , dateList.get(1)
                    , dateList.get(2)
                    , dateList.get(3)
                    , dateList.get(4)
                    , dateList.get(5)
                    , dateList.get(6)
            );
            for (int s = 0; s < warring.size(); s++) {
                HashMap<String, String> data = warring.get(s);
                WarringDto warringDto = new WarringDto();
                warringDto.setRealname(userInfo.get("real_name"));
                warringDto.setCreate_date(data.get("create_date"));
                warringDto.setDepart(userInfo.get("depart"));
                hashMaps.add(warringDto);
            }
        }
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("total", hashMaps.size());
        stringObjectHashMap.put("list", hashMaps);
        return new Result().ok(stringObjectHashMap);
    }


    @GetMapping("/count")
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
        List<HashMap<String, String>> dataList = new ArrayList<>();
        List<HashMap<String, String>> departuretimelist = baseDao.getDeparturetime(myStuIdList, dayOfMonth[0] + "%");
        List<HashMap<String, String>> destinationtimelist = baseDao.getDestinationtime(myStuIdList, dayOfMonth[0] + "%");
        //处理查出来的数据获取数据集
        getDataList(dayOfMonth[0], i, dataList, departuretimelist, destinationtimelist);
        //处理集合变成前端需要的
        ArrayList<ArrayList<HashMap<String, String>>> hashMaps = new ArrayList<>();
        dataList.forEach(x -> {
        //{name: "返校人数", value: 900},
            // {name: "出校人数", value: 1212}
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
        return new Result<>().ok(resData);
    }

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
