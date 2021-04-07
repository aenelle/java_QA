package ru.stqa.pft.mantis.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

  @Entity
  @Table(name = "mantis_user_table")


  public class Users {
    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    public int getId() {
      return id;
    }

    public String getUsername() {
      return username;
    }

    public String getEmail() {
      return email;
    }

    public String getPassword() {
      return password;
    }

    @Override
    public String toString() {
      return "Users{" +
              "id=" + id +
              ", username='" + username + '\'' +
              ", email='" + email + '\'' +
              ", password='" + password + '\'' +
              '}';
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Users)) return false;
      Users users = (Users) o;
      return getId() == users.getId() && Objects.equals(getUsername(), users.getUsername()) && Objects.equals(getEmail(), users.getEmail());
    }

    @Override
    public int hashCode() {
      return Objects.hash(getId(), getUsername(), getEmail());
    }
}
