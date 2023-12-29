package com.fidaamahboob.financeapp.service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.fidaamahboob.financeapp.api.model.FinanceData;

@Service
public class FinanceDataService {

    private List<FinanceData> dummyDataList;

    public FinanceDataService() {
        dummyDataList = new ArrayList<>();

        FinanceData data1 = new FinanceData(1, "06/02/2023", 550.45, 40.45);
        FinanceData data2 = new FinanceData(2, "07/02/2023", 450.45, 32.34);
        FinanceData data3 = new FinanceData(3, "08/02/2023", 600.34, 212.34);
        FinanceData data4 = new FinanceData(4, "09/02/2023", 760.45, 330.23);
    
        dummyDataList.addAll(Arrays.asList(data1, data2, data3, data4));
    }
    

    public Optional<FinanceData> getFinanceData(Integer id) {

        Optional<FinanceData> optional = Optional.empty();
        for (FinanceData financeData : dummyDataList) {
            if(id == financeData.getId()){
                optional = Optional.of(financeData);
                return optional;
            } 
        }

        return optional;
        
    }
    
}
