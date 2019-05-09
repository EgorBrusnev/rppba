package by.rppba.production.dto;

import by.rppba.production.util.Time;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonAutoDetect
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DetailDto {
    private int id;
    private String name;
    private String material;
    private int totalQty;
    private int qtyForProduct;
    private double pricePerUnit;
    private String stageName;
    private int stageDuration;
    private Time stageDurationUnit;
    private boolean isEnoughQty;

    public DetailDto() {
    }

    public DetailDto(int id, String name, String material, int totalQty, int qtyForProduct, double pricePerUnit, String stageName, int stageDuration, Time stageDurationUnit) {
        this.id = id;
        this.name = name;
        this.material = material;
        this.totalQty = totalQty;
        this.qtyForProduct = qtyForProduct;
        this.pricePerUnit = pricePerUnit;
        this.stageName = stageName;
        this.stageDuration = stageDuration;
        this.stageDurationUnit = stageDurationUnit;
        this.isEnoughQty = qtyForProduct <= totalQty;
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

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public int getTotalQty() {
        return totalQty;
    }

    public void setTotalQty(int totalQty) {
        this.totalQty = totalQty;
    }

    public int getQtyForProduct() {
        return qtyForProduct;
    }

    public void setQtyForProduct(int qtyForProduct) {
        this.qtyForProduct = qtyForProduct;
        updateEnoughQty();
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public int getStageDuration() {
        return stageDuration;
    }

    public void setStageDuration(int stageDuration) {
        this.stageDuration = stageDuration;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public Time getStageDurationUnit() {
        return stageDurationUnit;
    }

    public void setStageDurationUnit(Time stageDurationUnit) {
        this.stageDurationUnit = stageDurationUnit;
    }

    public boolean isEnoughQty() {
        return isEnoughQty;
    }

    public void updateEnoughQty() {
        this.isEnoughQty = this.qtyForProduct <= this.totalQty;
    }
}
