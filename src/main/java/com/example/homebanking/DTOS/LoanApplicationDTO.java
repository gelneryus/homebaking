package com.example.homebanking.DTOS;

public class LoanApplicationDTO {
    private String loanTypeId;
    private double amount;
    private Integer payments;
    private String numberAccount;

    public LoanApplicationDTO() {
    }

    public LoanApplicationDTO(String loanTypeId, double amount,Integer  payments, String numberAccount) {
        this.loanTypeId = loanTypeId;
        this.amount = amount;
        this.payments = payments;
        this.numberAccount = numberAccount;
    }

    public String getLoanTypeId() {
        return loanTypeId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer   getPayments() {
        return payments;
    }

    public void setPayments(Integer   payments) {
        this.payments = payments;
    }

    public String getNumberAccount() {
        return numberAccount;
    }

    public void setNumberAccount(String numberAccount) {
        this.numberAccount = numberAccount;
    }
}