package com.eduardo.todosimple.services;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eduardo.todosimple.models.Leads;
import com.eduardo.todosimple.models.User;
import com.eduardo.todosimple.repositories.LeadsRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class LeadsServices {

  @Autowired
  private LeadsRepository leadsRepository;

  @Autowired
  private UserServices userServices;

  public Leads findLeadByIdWithoutUser(Long id) {
    Optional<Leads> leadsOptional = leadsRepository.findById(id);

    return leadsOptional.map(this::mapLeadsWithoutUser).orElse(null);
  }

  private Leads mapLeadsWithoutUser(Leads leads) {
    Leads leadsWithoutUser = new Leads();
    leadsWithoutUser.setId(leads.getId());
    leadsWithoutUser.setFoneNumber(leads.getFoneNumber());
    leadsWithoutUser.setName(leads.getName());
    leadsWithoutUser.setDescription(leads.getDescription());

    return leadsWithoutUser;
  }

  public List<Leads> findAllByUserId(Long userId) {

    List<Leads> leads = this.leadsRepository.findByUser_Id(userId);

    return leads;

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
  public void updateLead(Leads lead) {

    Optional<Leads> existingLead = leadsRepository.findById(lead.getId());

    if (existingLead.isPresent()) {

      Leads updatedLead = existingLead.get();

      updatedLead.setName(lead.getName());

      updatedLead.setFoneNumber(lead.getFoneNumber());

      updatedLead.setDescription(lead.getDescription());

      leadsRepository.save(updatedLead);

    } else {

      throw new EntityNotFoundException("Lead não encontrado com ID: " + lead.getId());

    }
  }

  @Transactional
  public void deleteLead(Long id) {

    findLeadByIdWithoutUser(id);

    try {

      this.leadsRepository.deleteById(id);

    } catch (Exception e) {

      throw new RuntimeException("Nao tem como apagar pq essa entidade esta relacionando com outras!");

    }

  }

}
