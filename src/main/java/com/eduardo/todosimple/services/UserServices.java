package com.eduardo.todosimple.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduardo.todosimple.repositories.LeadsRepository;
import com.eduardo.todosimple.repositories.UserRepository;

@Service
public class UserServices {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private LeadsRepository leadsRepository;

}
