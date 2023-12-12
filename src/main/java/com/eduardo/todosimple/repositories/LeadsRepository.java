package com.eduardo.todosimple.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eduardo.todosimple.models.Leads;

@Repository
public interface LeadsRepository extends JpaRepository<Leads, Long> {

  List<Leads> findByUser_Id(Long id);

}
