package com.fidaamahboob.financeapp.api.model;

public class FinanceData {
    private int id;
    private String date;
    private double revenue;
    private double expense;

    public FinanceData(int id, String date, double d, double e) {
        this.id = id;
        this.date = date;
        this.revenue = d;
        this.expense = e;
    }
   
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public double getRevenue() {
        return revenue;
    }
    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }
    public double getExpense() {
        return expense;
    }
    public void setExpense(int expense) {
        this.expense = expense;
    }
}
