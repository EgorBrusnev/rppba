package by.rppba.production.model;

import by.rppba.production.dto.DetailDto;

import javax.persistence.*;

@Entity
public class ProductDetail {
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name = "product_id")
    private Product product;
    private int detailQty;
    @ManyToOne(targetEntity = Stage.class)
    @JoinColumn(name = "stage_id", referencedColumnName = "id")
    private Stage stage;
    @ManyToOne(targetEntity = Detail.class)
    @JoinColumn(name = "detail_id")
    private Detail detail;
    private String unit;

    public ProductDetail() {
    }

    public ProductDetail(Product product, int detailQty, Stage stage, Detail detail, String unit) {
        this.product = product;
        this.detailQty = detailQty;
        this.stage = stage;
        this.detail = detail;
        this.unit = unit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getDetailQty() {
        return detailQty;
    }

    public void setDetailQty(int detailQty) {
        this.detailQty = detailQty;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Detail getDetail() {
        return detail;
    }

    public void setDetail(Detail detail) {
        this.detail = detail;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public DetailDto toDto() {
        return new DetailDto(detail.getId(), detail.getName(), detail.getMaterial(), detail.getQuantity(), stage.getDetailQty(), detail.getUnitPrice(), stage.getName(), stage.getStageTime(), stage.getStageTimeUnit());
    }
}
