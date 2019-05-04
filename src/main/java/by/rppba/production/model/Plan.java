package by.rppba.production.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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
}
