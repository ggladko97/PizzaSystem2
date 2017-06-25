package sample.dao;

import java.util.List;
import sample.entity.Users;

public interface UsersDao {
  public void insert(Users user);
  void updateUsers(Users user);
  List<Users> listUsers();
  Users getUsersById(int id);
  void removeUsers(int id);
}
