package com.eduardo.todosimple.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.eduardo.todosimple.models.User;
import com.eduardo.todosimple.models.User.CreateUser;
import com.eduardo.todosimple.models.User.UpdateUser;
import com.eduardo.todosimple.services.UserServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
@Validated
@CrossOrigin
public class UserController {

  @Autowired
  private UserServices userServices;

  @CrossOrigin
  @GetMapping("/{id}")
  public ResponseEntity<User> findById(@PathVariable Long id) {

    User obj = this.userServices.findUserById(id);

    ResponseEntity resp = ResponseEntity.ok().body(obj);

    return resp;

  }

  @GetMapping("/{email}/{password}")
  public ResponseEntity<User> findByPassword(@PathVariable String email, @PathVariable String password) {

    User obj = this.userServices.login(email);

    String passwordd = obj.getPassword();

    System.out.println(password);

    if (!password.equals(passwordd)) {

      return ResponseEntity.badRequest().body(null);

    }

    ResponseEntity resp = ResponseEntity.ok().body(obj);

    return resp;

  }

  @PostMapping
  @Validated(CreateUser.class)
  public ResponseEntity<User> create(@Valid @RequestBody User obj) {

    User createdUser = this.userServices.createUser(obj);

    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdUser.getId())
        .toUri();

    return ResponseEntity.created(uri).body(createdUser);

  }

  @PutMapping("/{id}")
  @Validated(UpdateUser.class)
  public ResponseEntity<Void> update(@Valid @RequestBody User obj, @PathVariable Long id) {

    obj.setId(id);

    obj = this.userServices.updateUser(obj);

    return ResponseEntity.noContent().build();

  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {

    this.userServices.deleteUser(id);

    return ResponseEntity.noContent().build();

  }

}
