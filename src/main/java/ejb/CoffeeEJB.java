package ejb;

import entities.Coffee;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class CoffeeEJB {

    public void add(String type, Integer price){
        Transaction transaction;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Coffee coffee = new Coffee();
            coffee.setType(type);
            coffee.setPrice(price);
            session.save(coffee);
            transaction.commit();
        }
    }

    public List<Coffee> coffeeList() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("select coffee from Coffee coffee");
            return query.getResultList();
        }
    }

    public List<String> coffeeListNames() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("select coffee from Coffee coffee");
            List<Coffee> coffees = query.getResultList();
            return coffees.stream().map(Coffee::getType).collect(Collectors.toList());
        }
    }

    public Coffee getByType(String type) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.find(Coffee.class, type);
        }
    }

}
