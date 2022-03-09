package com.example.homebanking.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String name;
    private Double maxAmount;
    private double interes;

    public Loan() {
    }

    @ElementCollection
    @Column(name="payment")
    private List<Integer> payments= new ArrayList<>();

    @OneToMany(mappedBy = "loan",fetch = FetchType.EAGER)
    Set<ClientLoans> clientLoans =new HashSet<>();

    public Loan(String name, Double maxAmount, List<Integer> payments) {
        this.name = name;
        this.maxAmount = maxAmount;
        this.payments = payments;
    }

    public Loan(long id, String name, double maxAmount, double interes, List<Integer> payments, Set<ClientLoans> clientLoans) {
        this.id = id;
        this.name = name;
        this.maxAmount = maxAmount;
        this.interes = interes;
        this.payments = payments;
        this.clientLoans = clientLoans;

    }

    public Loan(String name, double maxAmount, List<Integer> payments, double interes) {
    }

    public Double getMaxAmount() {
        return maxAmount;
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



    public void setMaxAmount(Double maxAmount) {
        this.maxAmount = maxAmount;
    }

    public List<Integer> getPayments() {
        return payments;
    }

    public void setPayments(List<Integer> payments) {
        this.payments = payments;
    }

    public Set<ClientLoans> getClientLoans() {
        return clientLoans;
    }

    public void setClientLoans(Set<ClientLoans> clientLoans) {
        this.clientLoans = clientLoans;
    }
}
