package per.me.record.entity;

import jakarta.persistence.*;

import java.sql.Time;

@Entity
@Table(name = "t_water_record")
public class WaterRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "water_record_id")
    private Integer waterRecordId;

    @Column(name = "water_record_time")
    private Time waterRecordTime;

    @Column(name = "in_or_out")
    private String inOrOut;

    @Column(name = "how_many", columnDefinition = "smallint")
    private Integer howMany;

    @Column(name = "water_record_note", columnDefinition = "text")
    private String waterRecordNote;

    protected WaterRecord() {}

    public WaterRecord(Time waterRecordTime, String inOrOut, Integer howMany, String waterRecordNote) {
        this.waterRecordTime = waterRecordTime;
        this.inOrOut = inOrOut;
        this.howMany = howMany;
        this.waterRecordNote = waterRecordNote;
    }

    @Override
    public String toString() {
        return "WaterRecord{" +
                "waterRecordId=" + waterRecordId +
                ", waterRecordTime=" + waterRecordTime +
                ", inOrOut='" + inOrOut + '\'' +
                ", howMany=" + howMany +
                ", waterRecordNote='" + waterRecordNote + '\'' +
                ", dailySummary=" + dailySummary +
                '}';
    }

    public Integer getWaterRecordId() {
        return waterRecordId;
    }

    public void setWaterRecordId(Integer waterRecordId) {
        this.waterRecordId = waterRecordId;
    }

    public Time getWaterRecorddTime() {
        return waterRecordTime;
    }

    public void setWaterRecordTime(Time waterRecorddTime) {
        this.waterRecordTime = waterRecorddTime;
    }

    public String getInOrOut() {
        return inOrOut;
    }

    public void setInOrOut(String inOrOut) {
        this.inOrOut = inOrOut;
    }

    public Integer getHowMany() {
        return howMany;
    }

    public void setHowMany(Integer howMany) {
        this.howMany = howMany;
    }

    public String getWaterRecordNote() {
        return waterRecordNote;
    }

    public void setWaterRecordNote(String waterRecordNote) {
        this.waterRecordNote = waterRecordNote;
    }

    @ManyToOne
    @JoinColumn(name = "date_id")
    private DailySummary dailySummary;

    public void setDate(DailySummary dailySummary) {
        this.dailySummary = dailySummary;
    }
}
