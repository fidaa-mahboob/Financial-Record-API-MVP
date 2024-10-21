package com.fidaamahboob.financeapp.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class FinanceData {

    public FinanceData() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String date;
    private int income;
    private int expense;
    private String description;

    public FinanceData(Long id, String date, int income, int expense, String description) {
        this.id = id;
        this.date = date;
        this.income = income;
        this.expense = expense;
        this.description = description;

    }
   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public int getExpense() {
        return expense;
    }
    
    public void setExpense(int expense) {
        this.expense = expense;
    }

    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    } 
}
