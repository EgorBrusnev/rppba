package by.rppba.production.model;

import by.rppba.production.util.Status;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Plan {
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne(targetEntity = Employee.class)
    @JoinColumn(name = "employee")
    private Employee employee;
    private Date requestDate;
    private Date endDate;
    @OneToMany(mappedBy = "plan")
    private List<ProductPlan> plannedProducts;
    @Transient
    private List<ExecutableOrder> doneOrders;
    @Transient
    private List<ExecutableOrder> inProgressOrders;
    @Transient
    private List<ExecutableOrder> plannedOrders;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<ProductPlan> getPlannedProducts() {
        return plannedProducts;
    }

    public void setPlannedProducts(List<ProductPlan> plannedProducts) {
        this.plannedProducts = plannedProducts;
    }

    public List<ExecutableOrder> getDoneOrders() {
        return doneOrders;
    }

    public void setDoneOrders(List<ExecutableOrder> doneOrders) {
        this.doneOrders = doneOrders;
    }

    public boolean isReady() {
        return !plannedProducts.stream()
                .map(productPlan -> {
                    int sum = doneOrders.stream().filter(it -> it.getProduct().getId() == productPlan.getProduct().getId()).mapToInt(ExecutableOrder::getProductQty).sum();
                    return sum >= productPlan.getCount();
                })
                .collect(Collectors.toList()).contains(false);
    }

    public List<ExecutableOrder> getInProgressOrders() {
        return inProgressOrders;
    }

    public void setInProgressOrders(List<ExecutableOrder> inProgressOrders) {
        this.inProgressOrders = inProgressOrders;
    }

    public List<ExecutableOrder> getPlannedOrders() {
        return plannedOrders;
    }

    public void setPlannedOrders(List<ExecutableOrder> plannedOrders) {
        this.plannedOrders = plannedOrders;
    }
}
