package com.fidaamahboob.financeapp.api.model;

public class FinanceData {
    private int id;
    private String date;
    private int revenue;
    private int expense;

    public FinanceData(int id, String date, int revenue, int expense) {
        this.id = id;
        this.date = date;
        this.revenue = revenue;
        this.expense = expense;
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
    public int getRevenue() {
        return revenue;
    }
    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }
    public int getExpense() {
        return expense;
    }
    public void setExpense(int expense) {
        this.expense = expense;
    }
}
