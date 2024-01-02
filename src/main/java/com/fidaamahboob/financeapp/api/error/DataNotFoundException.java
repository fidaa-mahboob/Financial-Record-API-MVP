package com.fidaamahboob.financeapp.api.error;

public class DataNotFoundException extends RuntimeException {
    private static long serialVersionUID = 1L;

    public DataNotFoundException(int id) {
        super("Error! Finance Record  (id = " + id + ") Not Found");
    }
}
