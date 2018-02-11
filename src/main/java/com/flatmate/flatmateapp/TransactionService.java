package com.flatmate.flatmateapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getTransactions() {
        return this.transactionRepository.findAll();
    }

    public Transaction saveTransaction(final Transaction transaction) {
        return this.transactionRepository.save(transaction);
    }

}
