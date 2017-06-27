package sample.dao;

import java.util.List;
import sample.entity.Pizza;

/**
 * Created by ggladko97 on 27.06.17.
 */
public interface PizzaDao {
  void insert(Pizza pizza);
  void updatePizza(Pizza pizza);
  List<Pizza> listPizza();
  Pizza getPizzaById(int id);
  void removePizza(int id);
}
