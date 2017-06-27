package sample.dao;

import java.io.File;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import sample.entity.Tokens;

public class TokensDaoImpl implements TokensDao {
  private static SessionFactory factory =
      new Configuration().configure("/conf/mapping.cfg.xml").buildSessionFactory();

  Transaction tx = null;
  public void insert(Tokens token) {

    Session session = factory.openSession();
    tx = session.beginTransaction();
    session.persist(token);
    tx.commit();
  }


  public void updateTokens(Tokens token) {
    Session session = factory.openSession();
    tx = session.beginTransaction();
    session.update(token);
    tx.commit();
  }


  public List<Tokens> listTokens() {
    Session session = factory.openSession();
    Query query = session.createQuery("from sample.entity.Tokens");
    List<Tokens> tokens = query.list();
    return tokens;
  }


  public Tokens getTokensById(int id) {
    Session session = factory.openSession();
    Tokens token = (Tokens) session.get(Tokens.class,id);
    return token;
  }


  public void removeTokens(int id) {
    Session session = factory.openSession();
    Tokens token = (Tokens) session.get(Tokens.class,id);
    if (token != null) {
      session.delete(token);
    }
  }
}
