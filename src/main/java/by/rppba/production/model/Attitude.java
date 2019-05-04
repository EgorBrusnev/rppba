package by.rppba.production.model;

import by.rppba.production.util.DetailStatus;

import javax.persistence.*;

@Entity
public class Attitude {
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne(targetEntity = Detail.class)
    @JoinColumn
    private Detail detail;
    @Enumerated(EnumType.STRING)
    private DetailStatus status;
    private int quantity;
    private String unit;

    public Attitude(Detail detail, DetailStatus status, int quantity, String unit) {
        this.detail = detail;
        this.status = status;
        this.quantity = quantity;
        this.unit = unit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Detail getDetail() {
        return detail;
    }

    public void setDetail(Detail detail) {
        this.detail = detail;
    }

    public DetailStatus getStatus() {
        return status;
    }

    public void setStatus(DetailStatus status) {
        this.status = status;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
