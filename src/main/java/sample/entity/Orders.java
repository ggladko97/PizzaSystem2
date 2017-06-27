package sample.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.jws.soap.SOAPBinding;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * Created by ggladko97 on 27.06.17.
 */
@Entity public class Orders implements Serializable {
  private int id;
  private Timestamp time;
  private Pizza pizza;
  private double price;
  private Integer isToken;
  private String message;
  private Integer doCall;
  private int phone;

  @Id @Column(name = "id", nullable = false) public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Basic @Column(name = "time", nullable = false) public Timestamp getTime() {
    return time;
  }

  public void setTime(Timestamp time) {
    this.time = time;
  }

  @Basic @Column(name = "price", nullable = false, precision = 0)
  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  @Basic @Column(name = "isToken", nullable = true) public Integer getIsToken() {
    return isToken;
  }

  public void setIsToken(Integer isToken) {
    this.isToken = isToken;
  }

  @Basic @Column(name = "message", nullable = true, length = -1)
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Basic @Column(name = "doCall", nullable = true) public Integer getDoCall() {
    return doCall;
  }

  public void setDoCall(Integer doCall) {
    this.doCall = doCall;
  }

  @Basic @Column(name = "phone", nullable = false) public int getPhone() {
    return phone;
  }

  public void setPhone(int phone) {
    this.phone = phone;
  }



  @ManyToOne(targetEntity = Pizza.class)
  @JoinColumn(name = "pizza")
  public Pizza getPizza() {
    return pizza;
  }

  public void setPizza(Pizza pizza) {
    this.pizza = pizza;
  }



  @Override public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Orders orders = (Orders) o;

    if (id != orders.id) return false;
    if (Double.compare(orders.price, price) != 0) return false;
    if (phone != orders.phone) return false;
    if (time != null ? !time.equals(orders.time) : orders.time != null) return false;
    if (isToken != null ? !isToken.equals(orders.isToken) : orders.isToken != null) return false;
    if (message != null ? !message.equals(orders.message) : orders.message != null) return false;
    if (doCall != null ? !doCall.equals(orders.doCall) : orders.doCall != null) return false;

    return true;
  }

  @Override public int hashCode() {
    int result;
    long temp;
    result = id;
    result = 31 * result + (time != null ? time.hashCode() : 0);
    temp = Double.doubleToLongBits(price);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    result = 31 * result + (isToken != null ? isToken.hashCode() : 0);
    result = 31 * result + (message != null ? message.hashCode() : 0);
    result = 31 * result + (doCall != null ? doCall.hashCode() : 0);
    result = 31 * result + phone;
    return result;
  }
}
