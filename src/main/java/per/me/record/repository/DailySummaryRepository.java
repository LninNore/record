package per.me.record.repository;

import org.springframework.data.repository.CrudRepository;
import per.me.record.entity.DailySummary;

import java.util.ArrayList;

public interface DailySummaryRepository extends CrudRepository<DailySummary, Integer> {

    public ArrayList<DailySummary> findByYearAndMonthAndDay(String year, String month, String day);

}
