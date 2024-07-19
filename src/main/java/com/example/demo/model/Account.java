package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "AccountName")
    private String accountName;

    @Column(name = "AccountNumber")
    private Long accountNumber;

    @Column(name = "Balance")
    private double balance;

    @Column(name = "AccountNumberfrom")
    private Long accountNumberfrom;

    @Column(name = "AccountNumberto")
    private Long accountNumberto;

    @Column(name = "transferamount")
    private int transferamount;

    // Constructors, getters, and setters
    public Account() {
    }

    public Account(String accountName, Long accountNumber, Long balance, Long accountNumberfrom, Long accountNumberto) {
        this.accountName = accountName;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.accountNumberfrom = accountNumberfrom;
        this.accountNumberto = accountNumberto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double d) {
        this.balance = d;
    }

    public Long getAccountNumberfrom() {
        return accountNumberfrom;
    }

    public void setAccountNumberfrom(Long accountNumberfrom) {
        this.accountNumberfrom = accountNumberfrom;
    }

    public Long getAccountNumberto() {
        return accountNumberto;
    }

    public void setAccountNumberto(Long accountNumberto) {
        this.accountNumberto = accountNumberto;
    }

    public int getTransferamount() {
        return transferamount;
    }

    public void setTransferamount(int transferamount) {
        this.transferamount = transferamount;
    }
}
