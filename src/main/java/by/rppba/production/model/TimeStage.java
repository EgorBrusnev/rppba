package by.rppba.production.model;

import javax.persistence.*;

@Entity
public class TimeStage {
    @EmbeddedId
    private TimeStageID id;
    private double time;
    private String unit;

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
