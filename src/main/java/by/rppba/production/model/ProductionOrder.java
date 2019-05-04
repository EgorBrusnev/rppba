package by.rppba.production.model;

import by.rppba.production.util.Status;

import javax.persistence.*;

@Entity
public class ProductionOrder {
    @Id
    @GeneratedValue
    private int id;
    @Column(name = "qty_product")
    private int productQty;
    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name = "product_id")
    private Product product;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String unit;

    public ProductionOrder() {
    }

    public ProductionOrder(int productQty, Product product, Status status, String unit) {
        this.productQty = productQty;
        this.product = product;
        this.status = status;
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductQty() {
        return productQty;
    }

    public void setProductQty(int productQty) {
        this.productQty = productQty;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
