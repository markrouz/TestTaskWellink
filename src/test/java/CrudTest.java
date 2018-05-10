/*
import com.mgerman.internetcafe.domain.CoffeeType;
import com.mgerman.internetcafe.domain.Order;
import com.mgerman.internetcafe.domain.OrderPosition;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class CrudTest {

    @Test
    @Ignore //todo тестовая база
    public void test() {
        CoffeeType simpleBlackCoffeeType = new CoffeeType();
        simpleBlackCoffeeType.setName("BlackCoffee");
        simpleBlackCoffeeType.setPrice(100.0);
        simpleBlackCoffeeType.setDisabled(false);

        CoffeeType americanoCoffeeType = new CoffeeType();
        americanoCoffeeType.setName("BlackCoffeeWithMilk");
        americanoCoffeeType.setPrice(150.0);
        americanoCoffeeType.setDisabled(false);

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        //EntityDaoHibernateImpl entityDaoHibernate = (EntityDaoHibernateImpl) applicationContext.getBean("entityDaoHibernateImpl");
        DbEntityService dbEntityService = (DbEntityService) applicationContext.getBean("dbEntityService");

        dbEntityService.save(simpleBlackCoffeeType);
        dbEntityService.save(americanoCoffeeType);

        List<DbEntity> coffeeEntities = dbEntityService.getAll("CoffeeType");
        assertNotNull(coffeeEntities);
        assertEquals(4, coffeeEntities.size());
        assertEquals(simpleBlackCoffeeType.getName(), ((CoffeeType) coffeeEntities.get(0)).getName());
        assertEquals(americanoCoffeeType.getName(), ((CoffeeType) coffeeEntities.get(1)).getName());

        OrderPosition orderPosition = new OrderPosition();
        orderPosition.setCoffeeType(americanoCoffeeType);
        orderPosition.setNumberOfCups(2);

        Order order = new Order();
        order.setCustomerName("Mark");
        order.setAddress("Demakova");
        order.setDate(new Date());
        order.setPrice(1000.0);

        List<OrderPosition> orderPositions = new ArrayList<OrderPosition>();
        orderPositions.add(orderPosition);
        order.setOrderPositions(orderPositions);
        dbEntityService.save(order);

        List<DbEntity> orderEntitiesFromDb = dbEntityService.getAll("Order");
        assertEquals(order.getCustomerName(), ((Order)orderEntitiesFromDb.get(0)).getCustomerName());

        List<DbEntity> orderPositionsFromDb = dbEntityService.getAll("OrderPosition");
        assertEquals(order.getOrderPositions().get(0).getCoffeeType().getName(), ((OrderPosition)orderPositionsFromDb.get(0)).getCoffeeType().getName());

        dbEntityService.delete(order);

        dbEntityService.delete(simpleBlackCoffeeType);
        dbEntityService.delete(americanoCoffeeType);
        coffeeEntities = dbEntityService.getAll("CoffeeType");
        assertEquals(0, coffeeEntities.size());

    }


}
*/
