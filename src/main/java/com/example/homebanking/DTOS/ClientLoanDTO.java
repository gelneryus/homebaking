package com.example.homebanking.DTOS;

import com.example.homebanking.models.ClientLoans;
import com.example.homebanking.models.Loan;
import com.example.homebanking.models.Transactions;

public class ClientLoanDTO {
    private long id;
    private long LoanId;
    private String name;
    private Double amount;
    private int payments;

    public ClientLoanDTO() {
    }

    public ClientLoanDTO(ClientLoans clientLoans) {
        this.id = clientLoans.getId();
        this.LoanId = clientLoans.getLoan().getId();
        this.name = clientLoans.getLoan().getName();
        this.amount = clientLoans.getAmount();
        this.payments = clientLoans.getPayments();

    }

    public ClientLoanDTO(Loan loan) {
    }


    public long getLoanId() {
        return LoanId;
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

    public int getPayments() {
        return payments;
    }

    public void setPayments(int payments) {
        this.payments = payments;
    }
}
