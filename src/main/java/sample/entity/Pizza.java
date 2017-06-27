package sample.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * Created by ggladko97 on 27.06.17.
 */
@Entity public class Pizza implements Serializable {

  private int id;
  private String name;
  private String description;
  private List<Orders> listOrders;
  private double price;
  private int isAval;
  private String message;

  @Id
  @Column(name = "id")
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Basic @Column(name = "name", nullable = false, length = 60)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Basic @Column(name = "description", nullable = false, length = -1)
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Basic @Column(name = "price", nullable = false, precision = 0)
  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  @Basic @Column(name = "isAval", nullable = false) public int getIsAval() {
    return isAval;
  }

  public void setIsAval(int isAval) {
    this.isAval = isAval;
  }

  @Basic @Column(name = "message", nullable = false, length = -1)
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @OneToMany(mappedBy = "pizza")
  public List<Orders> getListOrders() {
    return listOrders;
  }

  public void setListOrders(List<Orders> listOrders) {
    this.listOrders = listOrders;
  }

  @Override public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Pizza pizza = (Pizza) o;

    if (id != pizza.id) return false;
    if (Double.compare(pizza.price, price) != 0) return false;
    if (isAval != pizza.isAval) return false;
    if (name != null ? !name.equals(pizza.name) : pizza.name != null) return false;
    if (description != null ? !description.equals(pizza.description) : pizza.description != null) {
      return false;
    }
    if (message != null ? !message.equals(pizza.message) : pizza.message != null) return false;

    return true;
  }

  @Override public int hashCode() {
    int result;
    long temp;
    result = id;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (description != null ? description.hashCode() : 0);
    temp = Double.doubleToLongBits(price);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    result = 31 * result + isAval;
    result = 31 * result + (message != null ? message.hashCode() : 0);
    return result;
  }
}
