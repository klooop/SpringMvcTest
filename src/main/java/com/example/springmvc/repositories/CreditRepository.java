package com.example.springmvc.repositories;

import com.example.springmvc.entities.Credit;
import com.example.springmvc.entities.CreditContract;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CreditRepository extends CrudRepository<Credit, Long>, JpaSpecificationExecutor<Credit> {
    Credit findCreditByCreditContract(CreditContract creditContract);
    Credit findByName(String title);
    List<Credit> findCreditsByStatusIsTrue();

}
