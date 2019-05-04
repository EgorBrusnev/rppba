package by.rppba.production.model;

import by.rppba.production.util.Status;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Entity
public class ExecutableOrder {
    @Id
    @GeneratedValue
    private int id;
    private Date deliveryTime;
    //    private Date launchTime;
    @ManyToOne(targetEntity = Plan.class)
    @JoinColumn
    private Plan plan;
    private int executionTime;
    @Column(name = "qty_product")
    private int productQty;
    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne(targetEntity = Employee.class)
    @JoinColumn(name = "employee_id")
    private Employee employee;
    @ManyToOne(targetEntity = Stage.class)
    @JoinColumn(name = "stage_id")
    private Stage stage;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne(targetEntity = ProductionOrder.class)
    @JoinColumn(name = "production_order_id")
    private ProductionOrder productionOrder;

    public ExecutableOrder() {
    }

    public ExecutableOrder(Date deliveryTime, Plan plan, int executionTime, int productQty, Product product, Employee employee, Status status, ProductionOrder productionOrder) {
        this.deliveryTime = deliveryTime;
//        this.launchTime = launchTime;
        this.plan = plan;
        this.executionTime = executionTime;
        this.productQty = productQty;
        this.product = product;
        this.employee = employee;
        this.status = status;
        this.productionOrder = productionOrder;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

//    public Date getLaunchTime() {
//        return launchTime;
//    }
//
//    public void setLaunchTime(Date launchTime) {
//        this.launchTime = launchTime;
//    }

    public int getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(int executionTime) {
        this.executionTime = executionTime;
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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public ProductionOrder getProductionOrder() {
        return productionOrder;
    }

    public void setProductionOrder(ProductionOrder productionOrder) {
        this.productionOrder = productionOrder;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }
}
