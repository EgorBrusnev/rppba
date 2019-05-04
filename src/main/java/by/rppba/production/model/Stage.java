package by.rppba.production.model;

import by.rppba.production.util.Time;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

import javax.persistence.*;

@JsonAutoDetect
@Entity
public class Stage {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    @Column(name = "detail_qty")
    private int detailQty;
    @Column(name = "unit_qty")
    private String unitQty;
    @Column(name = "stage_time")
    private int stageTime;
    @Enumerated(EnumType.STRING)
    private Time stageTimeUnit;
    private int stageNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDetailQty() {
        return detailQty;
    }

    public void setDetailQty(int detailQty) {
        this.detailQty = detailQty;
    }

    public String getUnitQty() {
        return unitQty;
    }

    public void setUnitQty(String unitQty) {
        this.unitQty = unitQty;
    }

    public int getStageTime() {
        return stageTime;
    }

    public void setStageTime(int stageTime) {
        this.stageTime = stageTime;
    }

    public int getStageNumber() {
        return stageNumber;
    }

    public void setStageNumber(int stageNumber) {
        this.stageNumber = stageNumber;
    }

    public Time getStageTimeUnit() {
        return stageTimeUnit;
    }

    public void setStageTimeUnit(Time stageTimeUnit) {
        this.stageTimeUnit = stageTimeUnit;
    }
}
