package per.me.record.entity;

import jakarta.persistence.*;

import java.sql.Time;

@Entity
@Table(name = "t_water_record")
public class TWaterRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "water_record_id")
    private Integer waterRecordId;

    @Column(name = "water_record_time")
    private Time waterRecorddTime;

    @Column(name = "in_or_out")
    private String inOrOut;

    @Column(name = "how_many", columnDefinition = "smallint")
    private Integer howMany;

    @Column(name = "water_record_note", columnDefinition = "text")
    private String waterRecordNote;

    protected TWaterRecord() {}

    public TWaterRecord(Time waterRecorddTime, String inOrOut, Integer howMany) {
        this.waterRecorddTime = waterRecorddTime;
        this.inOrOut = inOrOut;
        this.howMany = howMany;
    }

    public Integer getWaterRecordId() {
        return waterRecordId;
    }

    public void setWaterRecordId(Integer waterRecordId) {
        this.waterRecordId = waterRecordId;
    }

    public Time getWaterRecorddTime() {
        return waterRecorddTime;
    }

    public void setWaterRecorddTime(Time waterRecorddTime) {
        this.waterRecorddTime = waterRecorddTime;
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
    private TDate dateId;

}
