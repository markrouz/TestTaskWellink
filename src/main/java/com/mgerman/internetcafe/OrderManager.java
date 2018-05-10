package com.mgerman.internetcafe;

import com.mgerman.internetcafe.domain.CoffeeEntity;
import com.mgerman.internetcafe.domain.DbEntity;
import com.mgerman.internetcafe.domain.OrderEntity;
import com.mgerman.internetcafe.domain.OrderPositionEntity;
import com.mgerman.internetcafe.service.DbEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;



@Component
@Scope("session")
public class OrderManager {

    @Autowired
    private DbEntityService dbEntityService;
    private OrderEntity order;
    private List<OrderPositionEntity> availablePositions;
    private double orderPositionsPrice;
    @Value("${n}")
    private int n;
    @Value("${deliveryPrice}")
    private double deliveryPrice;
    @Value("${x}")
    private double x;

    @PostConstruct
    private void initOrderPositions() {
        availablePositions = new ArrayList<OrderPositionEntity>();
        List<DbEntity> coffeeEntitiesFromBb = dbEntityService.getAll("CoffeeEntity");
        for(DbEntity coffeeEntity: coffeeEntitiesFromBb) {
            if (((CoffeeEntity)coffeeEntity).isDisabled()) {
                continue;
            }
            OrderPositionEntity orderPositionEntity = new OrderPositionEntity();
            orderPositionEntity.setCoffee((CoffeeEntity) coffeeEntity);
            availablePositions.add(orderPositionEntity);
        }
    }

    public String createOrder() {
        List<OrderPositionEntity> orderPositions = new ArrayList<OrderPositionEntity>();
        for (OrderPositionEntity availablePosition: availablePositions) {
            if (availablePosition.getNumberOfCups() > 0) {
                orderPositions.add(availablePosition);
            }
        }

        if (orderPositions.size() == 0) {
            return "orderSomethingPlease.xhtml";
        }

        order = new OrderEntity();
        order.setOrderPositions(orderPositions);
        order.setPrice(calculateOrderPrice());
        order.setDate(new Date());
        return "orderInfo.xhtml";
    }

    public String saveOrder() {
        dbEntityService.save(order);
        return "confirmOrder";
    }

    private double calculateOrderPrice() {
        calculateOrderPositionsPrice();
        return orderPositionsPrice < x ? orderPositionsPrice + deliveryPrice : orderPositionsPrice;
    }

    private void calculateOrderPositionsPrice() {
        orderPositionsPrice = 0;
        for(OrderPositionEntity orderPosition: order.getOrderPositions()) {
            double coffeePrice = orderPosition.getCoffee().getPrice();
            int numberOfCups = orderPosition.getNumberOfCups();
            double positionPrice = numberOfCups * coffeePrice - (numberOfCups / n) * coffeePrice;
            orderPosition.setOrderPositionPrice(positionPrice);
            orderPositionsPrice += positionPrice;
        }
    }

    //getters and setters
    public List<OrderPositionEntity> getAvailablePositions() {
        return availablePositions;
    }

    public void setAvailablePositions(List<OrderPositionEntity> availablePositions) {
        this.availablePositions = availablePositions;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }


    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public double getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(double deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getOrderPositionsPrice() {
        return orderPositionsPrice;
    }

    public void setOrderPositionsPrice(double orderPositionsPrice) {
        this.orderPositionsPrice = orderPositionsPrice;
    }
}
