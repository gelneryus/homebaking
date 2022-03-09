package com.example.homebanking.DTOS;

import com.example.homebanking.models.Loan;

import java.util.ArrayList;
import java.util.List;

public class LoanDTO {
    private long id;
    private String name;
    private Double amount;
    private List<Integer> payments=new ArrayList<>();
    private double interes;

    public LoanDTO() {
    }

    public LoanDTO(Loan loan) {
        this.id = loan.getId();
        this.name = loan.getName();
        this.amount = loan.getMaxAmount();
        this.payments = loan.getPayments();
        this.interes=loan.getInteres();

    }



    public double getInteres() {
        return interes;
    }

    public void setInteres(double interes) {
        this.interes = interes;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public List<Integer> getPayments() {
        return payments;
    }

    public void setPayments(List<Integer> payments) {
        this.payments = payments;
    }
}
