package by.rppba.production.model;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class TimeStageID implements Serializable {
    @ManyToOne(targetEntity = ExecutableOrder.class)
    @JoinColumn(name = "executable_order_id")
    private ExecutableOrder order;

    @ManyToOne(targetEntity = Stage.class)
    @JoinColumn(name = "stage_id")
    private Stage stage;

    public ExecutableOrder getOrder() {
        return order;
    }

    public void setOrder(ExecutableOrder order) {
        this.order = order;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
