package com.example.springmvc.repositories;

import com.example.springmvc.entities.CreditContract;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CreditContractRepository extends CrudRepository<CreditContract, Long>, JpaSpecificationExecutor<CreditContract> {
    @Query("Select c from CreditContract c where c.creditStatus like 'Подписано' ")
    List<CreditContract> getCreditContractsByStatus();
}
