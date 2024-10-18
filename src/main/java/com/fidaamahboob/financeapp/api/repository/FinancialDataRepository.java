package com.fidaamahboob.financeapp.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fidaamahboob.financeapp.api.model.FinanceData;

@Repository
public interface FinancialDataRepository extends JpaRepository<FinanceData, Long> {
    
}
