package by.rppba.production.dto;

import by.rppba.production.util.Time;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class StageDto {
    private int id;
    private String name;
    private int stageNumber;
    private int time;
    private Time timeUnit;
    private int quantity;
    private String unitQty;

    public StageDto() {
    }

    public StageDto(int id, String name, int stageNumber, int time, Time timeUnit, int quantity, String unitQty) {
        this.id = id;
        this.name = name;
        this.stageNumber = stageNumber;
        this.time = time;
        this.timeUnit = timeUnit;
        this.quantity = quantity;
        this.unitQty = unitQty;
    }

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

    public int getStageNumber() {
        return stageNumber;
    }

    public void setStageNumber(int stageNumber) {
        this.stageNumber = stageNumber;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Time getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(Time timeUnit) {
        this.timeUnit = timeUnit;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUnitQty() {
        return unitQty;
    }

    public void setUnitQty(String unitQty) {
        this.unitQty = unitQty;
    }
}
