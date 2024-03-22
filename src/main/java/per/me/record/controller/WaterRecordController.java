package per.me.record.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.mysql.cj.xdevapi.JsonString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import per.me.record.annotation.MultiRequestBody;
import per.me.record.entity.DailySummary;
import per.me.record.entity.WaterRecord;
import per.me.record.service.WaterRecordService;

import java.util.HashMap;

@Controller
@RequestMapping(path = "/water_record")
public class WaterRecordController {

    @Autowired
    private WaterRecordService waterRecordService;

    @MultiRequestBody
    @ResponseBody
    @PostMapping(path = "/addOne")
    public Object addOne(String year, String month, String day, WaterRecord waterRecord){
        DailySummary dailySummary = new DailySummary(year, month, day);
        WaterRecord returned = waterRecordService.addOne(dailySummary, waterRecord);
        if(returned != null) {
            return "SUCCEEDED";
        }
        else{
            return "FAILED";
        }
    }

}
