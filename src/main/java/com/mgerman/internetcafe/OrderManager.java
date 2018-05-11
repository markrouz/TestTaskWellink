package com.mgerman.internetcafe;

import com.mgerman.internetcafe.dao.OrderDao;
import com.mgerman.internetcafe.domain.CoffeeType;
import com.mgerman.internetcafe.domain.Order;
import com.mgerman.internetcafe.domain.OrderPosition;
import com.mgerman.internetcafe.service.CoffeeTypeService;
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
    CoffeeTypeService coffeeTypeService;
    @Autowired
    OrderDao orderService;
    private Order order;
    private List<OrderPosition> availablePositions;
    private double orderPositionsPrice;
    @Value("${n}")
    private int n;
    @Value("${deliveryPrice}")
    private double deliveryPrice;
    @Value("${x}")
    private double x;

    @PostConstruct
    private void initOrderPositions() {
        availablePositions = new ArrayList<OrderPosition>();
        List<CoffeeType> availableCoffeeTypes = coffeeTypeService.getAllAvailableCoffeeTypes();
        for(CoffeeType coffeeEntity: availableCoffeeTypes) {
            OrderPosition orderPosition = new OrderPosition();
            orderPosition.setCoffeeType(coffeeEntity);
            availablePositions.add(orderPosition);
        }
    }

    public String createOrder() {
        List<OrderPosition> orderPositions = new ArrayList<OrderPosition>();
        for (OrderPosition availablePosition: availablePositions) {
            if (availablePosition.getNumberOfCups() > 0) {
                orderPositions.add(availablePosition);
            }
        }

        if (orderPositions.isEmpty()) {
            return "orderSomethingPlease.xhtml";
        }

        order = new Order();
        order.setOrderPositions(orderPositions);
        order.setPrice(calculateOrderPrice());
        order.setDate(new Date());
        return "orderInfo.xhtml";
    }

    public String saveOrder() {
        orderService.save(order);
        return "confirmOrder";
    }

    private double calculateOrderPrice() {
        calculateOrderPositionsPrice();
        return orderPositionsPrice < x ? orderPositionsPrice + deliveryPrice : orderPositionsPrice;
    }

    private void calculateOrderPositionsPrice() {
        orderPositionsPrice = 0;
        for(OrderPosition orderPosition: order.getOrderPositions()) {
            double coffeePrice = orderPosition.getCoffeeType().getPrice();
            int numberOfCups = orderPosition.getNumberOfCups();
            double positionPrice = numberOfCups * coffeePrice - (numberOfCups / n) * coffeePrice;
            orderPosition.setOrderPositionPrice(positionPrice);
            orderPositionsPrice += positionPrice;
        }
    }

    //getters and setters
    public List<OrderPosition> getAvailablePositions() {
        return availablePositions;
    }

    public void setAvailablePositions(List<OrderPosition> availablePositions) {
        this.availablePositions = availablePositions;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
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
