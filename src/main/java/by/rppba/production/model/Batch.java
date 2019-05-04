package by.rppba.production.model;

import javax.persistence.*;

@Entity
public class Batch {
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "package_id")
    private Package aPackage;
    private int qtyInPackage;

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

    public Package getaPackage() {
        return aPackage;
    }

    public void setaPackage(Package aPackage) {
        this.aPackage = aPackage;
    }

    public int getQtyInPackage() {
        return qtyInPackage;
    }

    public void setQtyInPackage(int qtyInPackage) {
        this.qtyInPackage = qtyInPackage;
    }
}
