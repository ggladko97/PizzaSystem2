package sample.dao;

import java.io.File;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import sample.entity.Users;

public class UsersDaoImpl implements UsersDao {

  Transaction tx=null;
  private static SessionFactory factory =
      new Configuration().configure("/conf/mapping.cfg.xml").buildSessionFactory();

  public void insert(Users user) {
    Session session = factory.openSession();

    System.out.println("User to be ins: " + user.toString());
    tx = session.beginTransaction();
    session.persist(user);
    tx.commit();
    System.out.println("user probably inserted");
  }


  public void updateUsers(Users user) {
    Session session = factory.openSession();
    session.update(user);
  }


  public List<Users> listUsers() {
    Session session = factory.openSession();
    System.out.println("Session opened");
    Query query = session.createQuery("from sample.entity.Users");
    List<Users> users = query.list();
    return users;
  }


  public Users getUsersById(int id) {
    Session session = factory.openSession();
    Users user = (Users) session.get(Users.class,id);
    return user;
  }


  public void removeUsers(int id) {
    Session session = factory.openSession();
    Users user = (Users) session.get(Users.class,id);
    if (user != null) {
      session.delete(user);
    }
  }
}
