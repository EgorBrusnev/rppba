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
    private Integer totalQty;
    private Integer qtyForProduct;
    private Double pricePerUnit;
    private String stageName;
    private Integer stageDuration;
    private Time stageDurationUnit;
    private boolean isEnoughQty;
    private boolean isEmptyStage;

    public DetailDto() {
    }

    public DetailDto(Integer id, String name, String material, Integer totalQty, Integer qtyForProduct, Double pricePerUnit, String stageName, Integer stageDuration, Time stageDurationUnit) {
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
        isEmptyStage = false;
    }

    public DetailDto(Integer qtyForProduct, String stageName, Integer stageDuration, Time stageDurationUnit) {
        this.qtyForProduct = qtyForProduct;
        this.stageName = stageName;
        this.stageDuration = stageDuration;
        this.stageDurationUnit = stageDurationUnit;
        this.totalQty = 0;
        isEmptyStage = true;
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

    public boolean isEmptyStage() {
        return isEmptyStage;
    }

    public void setEmptyStage(boolean emptyStage) {
        isEmptyStage = emptyStage;
    }
}
