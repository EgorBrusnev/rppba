package by.rppba.production.model;

import javax.persistence.*;

@Entity
public class ProductPlan {
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne(targetEntity = Plan.class)
    @JoinColumn(name = "plan")
    private Plan plan;
    @ManyToOne(targetEntity = Product.class)
    @JoinColumn
    private Product product;
    private int count;
    @ManyToOne(targetEntity = UnitQty.class)
    @JoinColumn
    private UnitQty unit;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public UnitQty getUnit() {
        return unit;
    }

    public void setUnit(UnitQty unit) {
        this.unit = unit;
    }
}
