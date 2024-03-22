package per.me.record.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import per.me.record.entity.DailySummary;
import per.me.record.repository.DailySummaryRepository;

@Controller
@RequestMapping(path = "/daily_summary")
public class DailySummaryController {

    @Autowired
    private DailySummaryRepository dailySummaryRepository;

    @GetMapping(path = "/add")
    @ResponseBody
    public String addATDate(@RequestParam String year, @RequestParam String month, @RequestParam String day) {
        DailySummary dailySummary = new DailySummary(year, month, day);
        dailySummaryRepository.save(dailySummary);
        return "saved";
    }

    @GetMapping(path = "all")
    @ResponseBody
    public Iterable<DailySummary> getAllTDate() {
        return dailySummaryRepository.findAll();
    }

}
