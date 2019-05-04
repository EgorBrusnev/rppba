package by.rppba.production.model;

import by.rppba.production.dto.SaveDetailDto;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@JsonAutoDetect
public class Detail {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String material;
    @ManyToOne(targetEntity = Producer.class)
    @JoinColumn(name = "producer")
    private Producer producer;
    private String size;
    @Column(name = "unit_size")
    private String unitSize;
    @Column(name = "price_per_unit")
    private double unitPrice;
    @ManyToOne(targetEntity = Currency.class)
    @JoinColumn(name = "currency_code")
    private Currency currency;
    private int quantity;
    @ManyToOne(targetEntity = UnitQty.class)
    @JoinColumn(name = "unit_qty")
    private UnitQty unitQty;


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

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getUnitSize() {
        return unitSize;
    }

    public void setUnitSize(String unitSize) {
        this.unitSize = unitSize;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public UnitQty getUnitQty() {
        return unitQty;
    }

    public void setUnitQty(UnitQty unitQty) {
        this.unitQty = unitQty;
    }
}
