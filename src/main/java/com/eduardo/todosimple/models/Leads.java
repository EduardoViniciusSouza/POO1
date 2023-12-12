package com.eduardo.todosimple.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = Leads.TABLE_NAME)
public class Leads {
  public static final String TABLE_NAME = "leads";

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false, updatable = false)
  private User user;

  @Column(name = "fone_number", length = 11, nullable = false, unique = true)
  @NotBlank
  private String foneNumber;

  @Column(name = "name", length = 50, nullable = false)
  private String name;

  @Column(name = "description", length = 255, nullable = true)
  @Size(min = 0, max = 255)
  private String description;

  public Leads() {
  }

  public Leads(Long id, User user, String foneNumber, String name, String description) {
    this.id = id;
    this.user = user;
    this.foneNumber = foneNumber;
    this.name = name;
    this.description = description;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public User getUser() {
    return this.user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public String getFoneNumber() {
    return this.foneNumber;
  }

  public void setFoneNumber(String foneNumber) {
    this.foneNumber = foneNumber;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this)
      return true;

    if (obj == null) {
      return false;
    }
    if (!(obj instanceof Leads)) {
      return false;
    }
    Leads other = (Leads) obj;
    if (this.id == null)
      if (other.id == null)
        return false;
      else if (this.id.equals(other.id))
        return false;
    return Objects.equals(this.id, other.id) && Objects.equals(this.user, other.user)
        && Objects.equals(this.foneNumber, other.foneNumber);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (this.id == null ? 0 : this.id.hashCode());
    return result;

  }

}
