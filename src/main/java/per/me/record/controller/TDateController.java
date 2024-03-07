package per.me.record.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import per.me.record.entity.TDate;
import per.me.record.repository.TDateRepository;

@Controller
@RequestMapping(path = "/t_date")
public class TDateController {

    @Autowired
    private TDateRepository tDateRepository;

    @GetMapping(path = "/add")
    @ResponseBody
    public String addATDate(@RequestParam String year, @RequestParam String month, @RequestParam String day) {
        TDate tDate = new TDate(year, month, day);
        tDateRepository.save(tDate);
        return "saved";
    }

    @GetMapping(path = "all")
    @ResponseBody
    public Iterable<TDate> getAllTDate() {
        return tDateRepository.findAll();
    }

}
