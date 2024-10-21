package com.fidaamahboob.financeapp.api.controller;

import org.springframework.web.bind.annotation.RestController;
import com.fidaamahboob.financeapp.api.model.FinanceData;
import com.fidaamahboob.financeapp.api.service.FinanceDataService;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/finance/v1/data")
public class FinanceDataController {

    private static final Logger logger = LoggerFactory.getLogger(FinanceDataController.class);

    @Autowired
    private FinanceDataService financeDataService;

    @GetMapping("/record/all")
    public List<FinanceData> getAllBooks() {
        return financeDataService.getAllFinanceData();
    }
    
    @GetMapping("/record/{id}")
    public ResponseEntity<FinanceData> getFinanceData(@PathVariable("id") Long id){

        if (id <= 0) {
            return ResponseEntity.badRequest().build(); // Return 400 Bad Request for invalid IDs
        }
        
        FinanceData data = financeDataService.getFinanceDataById(id);
        
        if (data == null) {
            return ResponseEntity.notFound().build(); // Return 404 Not Found if record doesn't exist
        }
    
        return ResponseEntity.ok(data);
    }

    @PostMapping("/record/create")
    public ResponseEntity<FinanceData> createRecFinanceDataRecord(@RequestBody FinanceData record) {
        logger.info("Creating record: {}", record); 
        ResponseEntity<FinanceData> entity = financeDataService.createFinanceDataRecord(record);
        logger.info("Record created successfully with ID: {}", record.getId());
        return entity;
    }

    @PutMapping("/record/update/{id}")
    public ResponseEntity<String> updateBook(@PathVariable("id") Long id, @RequestBody FinanceData record) {
        return financeDataService.updateFinanceDataRecord(id, record);
    }

    @DeleteMapping("/record/delete/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable("id") Long id) {
        financeDataService.deleteFinanceDataRecord(id);
        return ResponseEntity.ok().build();
    }
}
