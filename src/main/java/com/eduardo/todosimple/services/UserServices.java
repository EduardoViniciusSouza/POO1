package com.eduardo.todosimple.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eduardo.todosimple.models.User;
import com.eduardo.todosimple.repositories.UserRepository;

@Service
public class UserServices {

  @Autowired
  private UserRepository userRepository;

  public User findUserById(Long id) { // encontrando usuario por id

    Optional<User> user = this.userRepository.findById(id);

    return user.orElseThrow(
        () -> new RuntimeException("Usuario nao encontrado! ID: " + id + ", Tipo: " + User.class.getName()));

  }

  public User login(String email) {

    Optional<User> user = this.userRepository.findByEmail(email);

    return user.orElseThrow(
        () -> new RuntimeException("Usuario nao encontrado! ID: " + email + ", Tipo: " + User.class.getName()));

  }

  @Transactional // Criando usuario
  public User createUser(User obj) {

    if (obj.getId() == null) {
      obj.setId(null);

      obj = this.userRepository.save(obj);
    }
    return obj;
  }

  @Transactional
  public User updateUser(User obj) {

    User newObj = findUserById(obj.getId());

    newObj.setPassword(obj.getPassword());

    return this.userRepository.save(newObj);

  }

  public void deleteUser(Long id) {

    findUserById(id);

    try {

      this.userRepository.deleteById(id);

    } catch (Exception e) {

      throw new RuntimeException("Nao tem como apagar pq essa entidade esta relacionando com outras!");

    }

  }
}
