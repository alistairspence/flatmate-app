package com.flatmate.flatmateregistry;

import com.flatmate.flatmatepersistence.Account;
import com.flatmate.flatmatepersistence.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        Account account = accountRepository.findAccountByUsernameAndPassword(transaction.getAccount().getUsername(), transaction.getAccount().getPassword());
        transaction.setAccount(account);
        Transaction pendingTransaction = this.transactionRepository.save(transaction);
        Set<Transaction> transactionSet = new HashSet<>();
        transactionSet.add(pendingTransaction);
        account.setTransactions(transactionSet);
        return pendingTransaction;
    }

    public void deleteTransaction(final Long transactionId) { transactionRepository.delete(transactionId); }

}
