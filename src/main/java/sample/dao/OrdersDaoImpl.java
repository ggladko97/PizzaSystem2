package sample.dao;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import sample.entity.Orders;

/**
 * Created by ggladko97 on 27.06.17.
 */
public class OrdersDaoImpl implements OrdersDao {
  private static SessionFactory factory =
      new Configuration().configure("/conf/mapping.cfg.xml").buildSessionFactory();

  Transaction tx = null;
  public void insert(Orders order) {

    Session session = factory.openSession();
    tx = session.beginTransaction();
    session.persist(order);
    tx.commit();
  }


  public void updateOrders(Orders order) {
    Session session = factory.openSession();
    tx = session.beginTransaction();
    session.update(order);
    tx.commit();
  }


  public List<Orders> listOrders() {
    Session session = factory.openSession();
    Query query = session.createQuery("from sample.entity.Orders");
    List<Orders> orders = query.list();
    return orders;
  }


  public Orders getOrdersById(int id) {
    Session session = factory.openSession();
    Orders order = (Orders) session.get(Orders.class,id);
    return order;
  }


  public void removeOrders(int id) {
    Session session = factory.openSession();
    Orders order = (Orders) session.get(Orders.class,id);
    if (order != null) {
      session.delete(order);
    }
  }
}
