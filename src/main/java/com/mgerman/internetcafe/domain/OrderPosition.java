package com.mgerman.internetcafe.domain;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "order_positions", schema = "public", catalog = "internet_cafe")
public class OrderPosition implements DbEntity {
    private int id;
    private int numberOfCups;
    private CoffeeType coffeeType;
    private double orderPositionPrice;


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
    @Column(name = "number_of_cups", nullable = false)
    public int getNumberOfCups() {
        return numberOfCups;
    }

    public void setNumberOfCups(int numberOfCups) {
        this.numberOfCups = numberOfCups;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderPosition that = (OrderPosition) o;

        if (id != that.id) return false;
        if (numberOfCups != that.numberOfCups) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + numberOfCups;
        return result;
    }


    @ManyToOne
    @JoinColumn(name = "coffee_type_id", referencedColumnName = "id", nullable = false)
    public CoffeeType getCoffeeType() {
        return coffeeType;
    }

    public void setCoffeeType(CoffeeType coffeeTypesByCoffeeTypeTypeId) {
        this.coffeeType = coffeeTypesByCoffeeTypeTypeId;
    }

    @Transient
    public double getOrderPositionPrice() {
        return orderPositionPrice;
    }

    public void setOrderPositionPrice(double orderPositionPrice) {
        this.orderPositionPrice = orderPositionPrice;
    }
}
