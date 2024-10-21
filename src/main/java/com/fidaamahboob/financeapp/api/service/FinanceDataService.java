package com.fidaamahboob.financeapp.api.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.fidaamahboob.financeapp.api.model.FinanceData;
import com.fidaamahboob.financeapp.api.repository.FinancialDataRepository;

@Service
public class FinanceDataService {

    private static final Logger logger = LoggerFactory.getLogger(FinanceDataService.class);
    
    @Autowired
    private FinancialDataRepository financialDataRepository;

    public FinanceData getFinanceDataById(Long id) {
        return financialDataRepository.findById(id).orElse(null);
    }    

    public List<FinanceData> getAllFinanceData(){
        return financialDataRepository.findAll();
    }

    public ResponseEntity<FinanceData> createFinanceDataRecord(FinanceData record){
        logger.debug("Saving record: {}", record); 
        FinanceData newRecord = financialDataRepository.save(record); 
        logger.debug("Record saved with ID: {}", record.getId());         
        ResponseEntity<FinanceData> entity = ResponseEntity.status(HttpStatus.CREATED).body(newRecord);
        logger.debug("Status code: {}", entity.getStatusCode()); 
        return entity; 
    }

    public ResponseEntity<String> updateFinanceDataRecord(Long id, FinanceData record){
        FinanceData data = financialDataRepository.findById(id).orElseThrow(() -> new RuntimeException("Record Not Found"));
        data.setDate(record.getDate());
        data.setIncome(record.getIncome());
        data.setExpense(record.getExpense());
        data.setDescription(record.getDescription());
        financialDataRepository.save(data);
        return ResponseEntity.status(HttpStatus.CREATED).body("Record updated");  
    }

    public ResponseEntity<String> deleteFinanceDataRecord(Long id){
        FinanceData record = financialDataRepository.findById(id).orElseThrow(() -> new RuntimeException("Record not found"));
        financialDataRepository.delete(record);
        return ResponseEntity.status(HttpStatus.CREATED).body("Record deleted"); 
    }
}
