package com.fidaamahboob.financeapp.api.controller;

import org.springframework.web.bind.annotation.RestController;
import com.fidaamahboob.financeapp.api.model.FinanceData;
import com.fidaamahboob.financeapp.service.FinanceDataService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class FinanceDataController {

    private FinanceDataService financeDataService;

    @Autowired
    public FinanceDataController(FinanceDataService financeDataService){
        this.financeDataService = financeDataService;
    }
    
    @GetMapping(value = "/data")
    public FinanceData getFinanceData(@RequestParam Integer id){
        Optional<FinanceData> financeData = financeDataService.getFinanceData(id);
        if(financeData.isPresent()){
            return (FinanceData) financeData.get();
        }
        return null ;
    }
}
