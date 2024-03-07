package per.me.record.entity;

import jakarta.persistence.*;

import java.sql.Time;

@Entity
@Table(name = "t_futou_record")
public class TFutouRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "futou_record_id")
    private Integer futouRecordId;

    @Column(name = "which_one", columnDefinition = "tinyint")
    private Integer whichOne;

    @Column(name = "futou_record_time")
    private Time futouRecordTime;

    private String density;

    @Column(name = "how_many_in", columnDefinition = "smallint")
    private Integer howManyIn;

    @Column(name = "how_many_out", columnDefinition = "smallint")
    private Integer howManyOut;

    @Column(name = "futou_record_note", columnDefinition = "text")
    private String futouRecordNote;

    protected TFutouRecord() {}

    public TFutouRecord(Integer whichOne, Time futouRecordTime, String density, Integer howManyIn, Integer howManyOut) {
        this.whichOne = whichOne;
        this.futouRecordTime = futouRecordTime;
        this.density = density;
        this.howManyIn = howManyIn;
        this.howManyOut = howManyOut;
    }

    public Integer getFutouRecordId() {
        return futouRecordId;
    }

    public void setFutouRecordId(Integer futouRecordId) {
        this.futouRecordId = futouRecordId;
    }

    public Integer getWhichOne() {
        return whichOne;
    }

    public void setWhichOne(Integer whichOne) {
        this.whichOne = whichOne;
    }

    public Time getFutouRecordTime() {
        return futouRecordTime;
    }

    public void setFutouRecordTime(Time futouRecordTime) {
        this.futouRecordTime = futouRecordTime;
    }

    public String getDensity() {
        return density;
    }

    public void setDensity(String density) {
        this.density = density;
    }

    public Integer getHowManyIn() {
        return howManyIn;
    }

    public void setHowManyIn(Integer howManyIn) {
        this.howManyIn = howManyIn;
    }

    public Integer getHowManyOut() {
        return howManyOut;
    }

    public void setHowManyOut(Integer howManyOut) {
        this.howManyOut = howManyOut;
    }

    public String getFutouRecordNote() {
        return futouRecordNote;
    }

    public void setFutouRecordNote(String futouRecordNote) {
        this.futouRecordNote = futouRecordNote;
    }

    @ManyToOne
    @JoinColumn(name = "date_id")
    private TDate dateId;

}
