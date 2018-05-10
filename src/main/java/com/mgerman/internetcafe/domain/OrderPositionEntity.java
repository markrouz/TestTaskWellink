package com.mgerman.internetcafe.domain;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "order_positions", schema = "public", catalog = "internet_cafe")
public class OrderPositionEntity implements DbEntity {
    private int id;
    private int numberOfCups;
    private List<OrderEntity> orders;
    private CoffeeEntity coffee;
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

        OrderPositionEntity that = (OrderPositionEntity) o;

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

    /*@ManyToMany(cascade = { CascadeType.ALL})
    @JoinTable(
            name = "order_position_in_order",
            joinColumns = { @JoinColumn(name = "order_position_id") },
            inverseJoinColumns = { @JoinColumn(name = "order_id") }
    )*/
    @ManyToMany(mappedBy = "orderPositions")
    public List<OrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderEntity> orderPositionInOrders) {
        this.orders = orderPositionInOrders;
    }

    @ManyToOne
    @JoinColumn(name = "coffee_type_id", referencedColumnName = "id", nullable = false)
    public CoffeeEntity getCoffee() {
        return coffee;
    }

    public void setCoffee(CoffeeEntity coffeeTypesByCoffeeTypeId) {
        this.coffee = coffeeTypesByCoffeeTypeId;
    }

    @Transient
    public double getOrderPositionPrice() {
        return orderPositionPrice;
    }

    public void setOrderPositionPrice(double orderPositionPrice) {
        this.orderPositionPrice = orderPositionPrice;
    }
}
