package by.rppba.production.model;

import javax.persistence.*;

@Entity
public class Producer {
    @Id
    @GeneratedValue
    private int id;
    private String company;
    @ManyToOne
    @JoinColumn(name = "county", referencedColumnName = "id")
    private CountryCode country;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public CountryCode getCountry() {
        return country;
    }

    public void setCountry(CountryCode country) {
        this.country = country;
    }
}
