package com.mgerman.internetcafe.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "coffee_types", schema = "public", catalog = "internet_cafe")
public class CoffeeEntity implements DbEntity {
    private int id;
    private String name;
    private Double price;
    private boolean disabled;
    private List<OrderPositionEntity> orderPositionsInCoffee;

    //todo non-argument constructor??
    //todo serializable???

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = -1)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "price", nullable = false, precision = 2)
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "disabled", nullable = false)
    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CoffeeEntity that = (CoffeeEntity) o;

        if (id != that.id) return false;
        if (disabled != that.disabled) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (disabled ? 1 : 0);
        return result;
    }

    @OneToMany(mappedBy = "coffee")
    public List<OrderPositionEntity> getOrderPositionsInCoffee() {
        return orderPositionsInCoffee;
    }

    public void setOrderPositionsInCoffee(List<OrderPositionEntity> orderPositionsById) {
        this.orderPositionsInCoffee = orderPositionsById;
    }
}