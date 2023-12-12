package com.eduardo.todosimple.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eduardo.todosimple.models.Leads;
import com.eduardo.todosimple.models.User;
import com.eduardo.todosimple.repositories.LeadsRepository;

@Service
public class LeadsServices {

  @Autowired
  private LeadsRepository leadsRepository;

  @Autowired
  private UserServices userServices;

  public Leads findLeadById(long id) {

    Optional<Leads> leads = this.leadsRepository.findById(id);

    return leads.orElseThrow(
        () -> new RuntimeException("Contato nao encontrado! Id: " + id + ", Tip: " + Leads.class.getName()));

  }

  @Transactional
  public Leads create(Leads obj) {

    User user = this.userServices.findUserById(obj.getUser().getId());

    obj.setId(null);

    obj.setUser(user);

    obj = this.leadsRepository.save(obj);

    return obj;

  }

  @Transactional
  public Leads updateDescription(Leads obj) {

    Leads newObj = findLeadById(obj.getId());

    newObj.setDescription(obj.getDescription());

    return this.leadsRepository.save(newObj);
  }

  @Transactional
  public Leads updateFone(Leads obj) {

    Leads newObj = findLeadById(obj.getId());

    newObj.setFoneNumber(obj.getFoneNumber());

    return this.leadsRepository.save(newObj);
  }

  @Transactional
  public Leads updateName(Leads obj) {

    Leads newObj = findLeadById(obj.getId());

    newObj.setName(obj.getName());

    return this.leadsRepository.save(newObj);
  }

  @Transactional
  public void deleteLead(Long id) {

    findLeadById(id);

    try {

      this.leadsRepository.deleteById(id);

    } catch (Exception e) {

      throw new RuntimeException("Nao tem como apagar pq essa entidade esta relacionando com outras!");

    }

  }

}
