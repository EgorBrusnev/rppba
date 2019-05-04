package by.rppba.production.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class StageDetailCost {

    @EmbeddedId
    private StageDetailCostID stageDetailCostID;

    private double detailQty;
    private String unit;

    public StageDetailCostID getStageDetailCostID() {
        return stageDetailCostID;
    }

    public void setStageDetailCostID(StageDetailCostID stageDetailCostID) {
        this.stageDetailCostID = stageDetailCostID;
    }

    public double getDetailQty() {
        return detailQty;
    }

    public void setDetailQty(double detailQty) {
        this.detailQty = detailQty;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
