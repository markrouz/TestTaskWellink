package com.mgerman.domain;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "order_positions", schema = "public", catalog = "internet_cafe")
public class OrderPositionEntity {
    private int id;
    private int numberOfCups;
    private Collection<OrderEntity> orders;
    private CoffeeEntity coffee;

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

    @ManyToMany(mappedBy = "orderPositions") //так-то наверн надо ManyToOne ???
    public Collection<OrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(Collection<OrderEntity> orderPositionInOrders) {
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
}
