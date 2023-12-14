package com.eduardo.todosimple.controllers;

import java.net.URI;

import java.util.List;

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

import com.eduardo.todosimple.DTO.LeadsResponseDTO;
import com.eduardo.todosimple.models.Leads;
import com.eduardo.todosimple.services.LeadsServices;
import com.eduardo.todosimple.services.UserServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/lead")
@Validated
@CrossOrigin
public class LeadsCrontroller {

  @Autowired
  private LeadsServices leadsServices;

  @Autowired
  private UserServices userServices;

  @GetMapping("/{id}")
  public ResponseEntity<LeadsResponseDTO> findLeadById(@PathVariable Long id) {
    Leads leadsWithoutUser = leadsServices.findLeadByIdWithoutUser(id);

    if (leadsWithoutUser != null) {
      LeadsResponseDTO responseDTO = mapToLeadsResponseDTO(leadsWithoutUser);
      return ResponseEntity.ok(responseDTO);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  private LeadsResponseDTO mapToLeadsResponseDTO(Leads leads) {
    LeadsResponseDTO responseDTO = new LeadsResponseDTO();
    responseDTO.setName(leads.getName());
    responseDTO.setFoneNumber(leads.getFoneNumber());
    responseDTO.setDescription(leads.getDescription());

    return responseDTO;
  }

  @CrossOrigin
  @GetMapping("/user/{userId}")
  public ResponseEntity<List<Leads>> findAllByUserId(@PathVariable Long userId) {

    this.userServices.findUserById(userId);

    List<Leads> objs = this.leadsServices.findAllByUserId(userId);

    return ResponseEntity.ok().body(objs);

  }

  @PostMapping
  @Validated
  public ResponseEntity<Void> create(@Valid @RequestBody Leads obj) {

    this.leadsServices.create(obj);

    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();

    return ResponseEntity.created(uri).build();

  }

  @PutMapping("/{id}")
  @Validated
  public ResponseEntity<Void> updateLead(@Valid @RequestBody Leads obj, @PathVariable Long id) {

    obj.setId(id);

    this.leadsServices.updateLead(obj);

    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {

    this.leadsServices.deleteLead(id);

    return ResponseEntity.noContent().build();

  }
}
