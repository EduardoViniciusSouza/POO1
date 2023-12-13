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
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity

@Table(name = User.TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
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

}