package by.rppba.production.model;

import javax.persistence.*;

@Entity
public class Package {
    @Id
    @GeneratedValue
    private int id;
    private String material;
    private double price;
    @ManyToOne(targetEntity = Currency.class)
    @JoinColumn(name = "currency_code")
    private Currency currency;
    private int qtyInWarehouse;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public int getQtyInWarehouse() {
        return qtyInWarehouse;
    }

    public void setQtyInWarehouse(int qtyInWarehouse) {
        this.qtyInWarehouse = qtyInWarehouse;
    }
}
