package sample.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity public class Tokens {
  private int id;
  private String token;

  @Id @Column(name = "id", nullable = false) public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Basic @Column(name = "token", nullable = false, length = 40)
  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  @Override public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Tokens tokens = (Tokens) o;

    if (id != tokens.id) return false;
    if (token != null ? !token.equals(tokens.token) : tokens.token != null) return false;

    return true;
  }

  @Override public int hashCode() {
    int result = id;
    result = 31 * result + (token != null ? token.hashCode() : 0);
    return result;
  }
}
