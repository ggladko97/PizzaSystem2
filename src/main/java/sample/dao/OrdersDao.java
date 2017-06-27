package sample.dao;

import java.util.List;
import sample.entity.Orders;

/**
 * Created by ggladko97 on 27.06.17.
 */
public interface OrdersDao {
  void insert(Orders orders);
  void updateOrders(Orders orders);
  List<Orders> listOrders();
  Orders getOrdersById(int id);
  void removeOrders(int id);
}
