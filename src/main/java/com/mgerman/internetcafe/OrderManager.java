package com.mgerman.internetcafe;

import com.mgerman.internetcafe.domain.CoffeeEntity;
import com.mgerman.internetcafe.domain.DbEntity;
import com.mgerman.internetcafe.domain.OrderEntity;
import com.mgerman.internetcafe.domain.OrderPositionEntity;
import com.mgerman.internetcafe.service.DbEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


//todo sql скрипт немного переделать (делать схему, а не новую бд)

@Component
@Scope("session")
public class OrderManager {

    @Autowired
    private DbEntityService dbEntityService;
    private OrderEntity order;// например <h:inputText value="#{OrderManger.order.customerName}
    private List<OrderPositionEntity> availablePositions;

    //инициализируем позиции заказа
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
        order = new OrderEntity();
        List<OrderPositionEntity> orderPositions = new ArrayList<OrderPositionEntity>();
        for (OrderPositionEntity availablePosition: availablePositions) {
            if (availablePosition.getNumberOfCups() > 0) {
                orderPositions.add(availablePosition);
            }
        }
        order.setOrderPositions(orderPositions);
        return "orderInfo.xhtml";
    }

    private void showOrderPositions() {

    }


    //getters and setters
    public List<OrderPositionEntity> getAvailablePositions() {
        return availablePositions;
    }

    public void setAvailablePositions(List<OrderPositionEntity> availablePositions) {
        this.availablePositions = availablePositions;
    }
}
