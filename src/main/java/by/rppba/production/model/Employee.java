package by.rppba.production.model;

import javax.persistence.*;

@Entity
public class Employee {
    @Id
    @GeneratedValue
    private int id;
    private String surname;
    private String name;
    @ManyToOne(targetEntity = Position.class)
    @JoinColumn(name = "position")
    private Position position;
    private int authCode;
    @OneToOne
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getAuthCode() {
        return authCode;
    }

    public void setAuthCode(int authCode) {
        this.authCode = authCode;
    }
}
