package by.rppba.production.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Request {
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne(targetEntity = Attitude.class)
    @JoinColumn
    private Attitude attitude;
    private Date date;

    public Request() {
    }

    public Request(Attitude attitude, Date date) {
        this.attitude = attitude;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Attitude getAttitude() {
        return attitude;
    }

    public void setAttitude(Attitude attitude) {
        this.attitude = attitude;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
