package sample.dao;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import sample.entity.Pizza;

/**
 * Created by ggladko97 on 27.06.17.
 */
public class PizzaDaoImpl implements PizzaDao {
  private static SessionFactory factory =
      new Configuration().configure("/conf/mapping.cfg.xml").buildSessionFactory();

  Transaction tx = null;
  public void insert(Pizza pizza) {

    Session session = factory.openSession();
    tx = session.beginTransaction();
    session.persist(pizza);
    tx.commit();
  }


  public void updatePizza(Pizza pizza) {
    Session session = factory.openSession();
    tx = session.beginTransaction();
    session.update(pizza);
    tx.commit();
  }


  public List<Pizza> listPizza() {
    Session session = factory.openSession();
    Query query = session.createQuery("from sample.entity.Pizza");
    List<Pizza> pizzas = query.list();
    return pizzas;
  }


  public Pizza getPizzaById(int id) {
    Session session = factory.openSession();
    Pizza pizza = (Pizza) session.get(Pizza.class,id);
    return pizza;
  }


  public void removePizza(int id) {
    Session session = factory.openSession();
    Pizza pizza = (Pizza) session.get(Pizza.class,id);
    if (pizza != null) {
      session.delete(pizza);
    }
  }
}
