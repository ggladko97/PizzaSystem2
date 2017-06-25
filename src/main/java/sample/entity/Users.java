package sample.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by ggladko97 on 25.06.17.
 */
@Entity public class Users {
  private int id;
  private String name;
  private String email;
  private String phone;
  private String password;
  private byte token;

  @Id @Column(name = "id", nullable = false) public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Basic @Column(name = "name", nullable = false, length = 40)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Basic @Column(name = "email", nullable = false, length = 40)
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Basic @Column(name = "phone", nullable = false, length = 15)
  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  @Basic @Column(name = "password", nullable = false, length = 40)
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Basic @Column(name = "token", nullable = false) public byte getToken() {
    return token;
  }

  public void setToken(byte token) {
    this.token = token;
  }

  @Override public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Users users = (Users) o;

    if (id != users.id) return false;
    if (token != users.token) return false;
    if (name != null ? !name.equals(users.name) : users.name != null) return false;
    if (email != null ? !email.equals(users.email) : users.email != null) return false;
    if (phone != null ? !phone.equals(users.phone) : users.phone != null) return false;
    if (password != null ? !password.equals(users.password) : users.password != null) return false;

    return true;
  }

  @Override public int hashCode() {
    int result = id;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (email != null ? email.hashCode() : 0);
    result = 31 * result + (phone != null ? phone.hashCode() : 0);
    result = 31 * result + (password != null ? password.hashCode() : 0);
    result = 31 * result + (int) token;
    return result;
  }

  @Override public String toString() {
    return id + name + email + phone + password + token;
  }
}
