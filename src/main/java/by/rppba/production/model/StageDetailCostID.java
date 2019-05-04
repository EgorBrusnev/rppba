package by.rppba.production.model;

import org.hibernate.annotations.Target;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class StageDetailCostID implements Serializable {
    @ManyToOne(targetEntity = ExecutableOrder.class)
    @JoinColumn(name = "executable_order_id")
    private ExecutableOrder executableOrder;
    @ManyToOne(targetEntity = Detail.class)
    @JoinColumn(name = "detail_id")
    private Detail detail;

    public ExecutableOrder getExecutableOrder() {
        return executableOrder;
    }

    public void setExecutableOrder(ExecutableOrder executableOrder) {
        this.executableOrder = executableOrder;
    }

    public Detail getDetail() {
        return detail;
    }

    public void setDetail(Detail detail) {
        this.detail = detail;
    }
}
