package per.me.record.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "t_date")
public class TDate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "date_id")
    private Integer dateId;

    private String year;

    private String month;

    private String day;

    @Column(name = "water_summary", columnDefinition = "smallint")
    private Integer waterSummary;

    @Column(name = "futou_summary", columnDefinition = "smallint")
    private Integer futouSummary;

    @Column(name = "date_note", columnDefinition = "text")
    private String dateNote;

    protected TDate(){}

    public TDate(String year, String month, String day){
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public Integer getDateId() {
        return dateId;
    }

    public void setDateId(Integer dateId) {
        this.dateId = dateId;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Integer getWaterSummary() {
        return waterSummary;
    }

    public Integer getFutouSummary() {
        return futouSummary;
    }

}
