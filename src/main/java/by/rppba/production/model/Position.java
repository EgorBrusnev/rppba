package by.rppba.production.model;

import by.rppba.production.util.EmployeePosition;

import javax.persistence.*;

@Entity
public class Position {
    @Id
    @GeneratedValue
    private int id;
    @Enumerated(value = EnumType.STRING)
    private EmployeePosition name;
    private double mRank;
    @ManyToOne
    @JoinColumn(name = "currency_code")
    private Currency currencyCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EmployeePosition getName() {
        return name;
    }

    public void setName(EmployeePosition name) {
        this.name = name;
    }

    public double getmRank() {
        return mRank;
    }

    public void setmRank(double mRank) {
        this.mRank = mRank;
    }

    public Currency getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(Currency currencyCode) {
        this.currencyCode = currencyCode;
    }
}
