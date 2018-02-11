package com.flatmate.flatmateapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(final AccountRepository accountRepository, final TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public Transaction getTransactionById(final Long transactionId) { return transactionRepository.findOne(transactionId); }

    public List<Transaction> getTransactions() {
        final List<Transaction> transactions = new ArrayList<>();
        transactionRepository.findAll().forEach(transactions::add);
        return transactions;
    }

    public Transaction createTransaction(final Transaction transaction) {
        return this.transactionRepository.save(transaction);
    }

    public void deleteTransaction(final Long transactionId) { transactionRepository.delete(transactionId); }

}
