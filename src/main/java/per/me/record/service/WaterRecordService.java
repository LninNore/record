package per.me.record.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import per.me.record.entity.DailySummary;
import per.me.record.entity.WaterRecord;
import per.me.record.repository.DailySummaryRepository;
import per.me.record.repository.WaterRecordRepository;

import java.util.ArrayList;

@Service
public class WaterRecordService {

    @Autowired
    private DailySummaryRepository dailySummaryRepository;
    @Autowired
    private WaterRecordRepository waterRecordRepository;

    public WaterRecord addOne(DailySummary dailySummary, WaterRecord waterRecord){
        WaterRecord toBeReturned = null;
        ArrayList<DailySummary> dailySummariesInDB = dailySummaryRepository.findByYearAndMonthAndDay(dailySummary.getYear(), dailySummary.getMonth(), dailySummary.getDay());
        if(dailySummariesInDB.size() == 0){
            dailySummaryRepository.save(dailySummary);
            waterRecord.setDate(dailySummary);
            toBeReturned = waterRecordRepository.save(waterRecord);
        }
        else if(dailySummariesInDB.size() == 1){
            waterRecord.setDate(dailySummariesInDB.get(0));
            toBeReturned = waterRecordRepository.save(waterRecord);
        }
        return toBeReturned;
    }

}
