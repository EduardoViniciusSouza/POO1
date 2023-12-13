package com.eduardo.todosimple.models;

import java.util.ArrayList;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity

@Table(name = User.TABLE_NAME)
public class User {

  public interface CreateUser {
  }

  public interface UpdateUser {
  }

  public static final String TABLE_NAME = "user";

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "id", unique = true)
  private Long id;

  @Column(name = "name", length = 50, nullable = false)
  @NotNull
  @Size(groups = CreateUser.class, min = 10, max = 50)
  private String name;

  @Column(name = "email", length = 100, nullable = false, unique = true)
  @NotBlank(groups = { CreateUser.class, UpdateUser.class })
  private String email;

  @Column(name = "foneNumber", length = 100, nullable = false, unique = true)
  @NotBlank
  private String foneNumber;

  @Column(name = "password", nullable = false)
  @NotNull(groups = { CreateUser.class, UpdateUser.class })
  @JsonProperty(access = Access.WRITE_ONLY)
  private String password;

  @OneToMany(mappedBy = "user")
  @JsonProperty(access = Access.WRITE_ONLY)
  private List<Leads> leads = new ArrayList<>();

  public User() {
  }

  public User(Long id, String name, String email, String foneNumber, String password) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.foneNumber = foneNumber;
    this.password = password;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getFoneNumber() {
    return this.foneNumber;
  }

  public void setFoneNumber(String foneNumber) {
    this.foneNumber = foneNumber;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public List<Leads> getLeads() {
    return this.leads;
  }

  public void setLeads(List<Leads> leads) {
    this.leads = leads;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this)
      return true;

    if (obj == null) {
      return false;
    }
    if (!(obj instanceof User)) {
      return false;
    }
    User other = (User) obj;
    if (this.id == null)
      if (other.id == null)
        return false;
      else if (this.id.equals(other.id))
        return false;
    return Objects.equals(this.id, other.id) && Objects.equals(this.email, other.email)
        && Objects.equals(this.password, other.password);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (this.id == null ? 0 : this.id.hashCode());
    return result;

  }

}
