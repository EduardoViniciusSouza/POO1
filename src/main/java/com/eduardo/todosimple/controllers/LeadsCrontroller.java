package com.eduardo.todosimple.controllers;

import java.net.URI;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.eduardo.todosimple.models.Leads;
import com.eduardo.todosimple.services.LeadsServices;
import com.eduardo.todosimple.services.UserServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/lead")
@Validated
public class LeadsCrontroller {

  @Autowired
  private LeadsServices leadsServices;

  @Autowired
  private UserServices userServices;

  @GetMapping("/{id}")
  public ResponseEntity<Leads> findById(@PathVariable Long id) {

    Leads obj = this.leadsServices.findLeadById(id);

    return ResponseEntity.ok(obj);

  }

  @GetMapping("/user/{userId}")
  public ResponseEntity<List<Leads>> findAllByUserIdW(@PathVariable Long userId) {

    userServices.findUserById(userId);

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

  @PutMapping("/{id}/name")
  @Validated
  public ResponseEntity<Void> updateName(@Valid @RequestBody Leads obj, @PathVariable Long id) {

    obj.setId(id);

    this.leadsServices.updateName(obj);

    return ResponseEntity.noContent().build();

  }

  @PutMapping("/{id}/fone")
  @Validated
  public ResponseEntity<Void> updateFone(@Valid @RequestBody Leads obj, @PathVariable Long id) {

    obj.setId(id);

    this.leadsServices.updateFone(obj);

    return ResponseEntity.noContent().build();

  }

  @PutMapping("/{id}/description")
  @Validated
  public ResponseEntity<Void> updateDescription(@Valid @RequestBody Leads obj, @PathVariable Long id) {

    obj.setId(id);

    this.leadsServices.updateDescription(obj);

    return ResponseEntity.noContent().build();

  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {

    this.leadsServices.deleteLead(id);

    return ResponseEntity.noContent().build();

  }
}
