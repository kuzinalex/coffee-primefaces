package ejb;

import entities.Coffee;
import entities.CoffeeOrder;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class CoffeeOrderEJB {

    public List<CoffeeOrder> getOrders() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from CoffeeOrder");
            return query.getResultList();
        }
    }

    public void saveOrUpdate(Integer amount, String delivery, String deliveryTime, Coffee coffee, Long id) {
        Transaction transaction;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            CoffeeOrder coffeeOrder = new CoffeeOrder();
            if (id != null) {
                coffeeOrder.setId(id);
            }
            coffeeOrder.setCoffee(coffee);
            coffeeOrder.setAmount(amount);
            coffeeOrder.setDelivery(delivery);
            coffeeOrder.setDeliveryTime(deliveryTime);
            int cost = amount * coffee.getPrice() / 100;
            if (delivery.equals("Courier delivery")) {
                cost += 100;
            }
            coffeeOrder.setCost(cost);

            session.saveOrUpdate(coffeeOrder);

            transaction.commit();
        }
    }

    public void delete(Long id) {
        Transaction transaction;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

           CoffeeOrder coffeeOrder=session.find(CoffeeOrder.class,id);

           session.delete(coffeeOrder);

            transaction.commit();
        }
    }
}
