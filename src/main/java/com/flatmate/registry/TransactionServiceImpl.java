package com.flatmate.registry;

import com.flatmate.exceptions.TransactionNotFoundException;
import com.flatmate.persistence.Account;
import com.flatmate.persistence.Transaction;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionServiceImpl(final AccountRepository accountRepository, final TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public Transaction getTransactionById(final Long transactionId) {
        final Transaction transaction = transactionRepository.findOne(transactionId);
        if (Objects.isNull(transaction)) {
            throw new TransactionNotFoundException(HttpStatus.NOT_FOUND, transactionId);
        } else {
            return transaction;
        }
    }

    public List<Transaction> getAllTransactions() {
        // TODO(alistair): why is this different from -
        // return Lists.newArrayList(transactionRepository.findAll());
        final List<Transaction> transactions = new ArrayList<>();
        transactionRepository.findAll().forEach(transactions::add);
        return transactions;
    }

    public Transaction createTransaction(final Transaction transaction) {
        // TODO(alistair): this account stuff is really not good
        final String username = transaction.getAccount().getUsername();
        final String password = transaction.getAccount().getPassword();
        final Account account = accountRepository.findAccountByUsernameAndPassword(username, password);
        transaction.setAccount(account);
        final Transaction pendingTransaction = transactionRepository.save(transaction);
        final Set<Transaction> transactionSet = new HashSet<>();
        transactionSet.add(pendingTransaction);
        account.setTransactions(transactionSet);
        return pendingTransaction;
    }

    public Transaction updateTransaction(final Long transactionId, final Transaction transaction) {
        // TODO(alistair): LIVE - updating a transaction creates a new account - this and below are due to the cascade type
        // TODO(alistair): LOCAL - updating a transaction returns 500 - transientpropertyvalueexception
        final Transaction transactionToUpdate = transactionRepository.findOne(transactionId);
        if (Objects.isNull(transactionToUpdate)) {
            throw new TransactionNotFoundException(HttpStatus.NOT_FOUND, transactionId);
        } else {
            transaction.setId(transactionId);
            return transactionRepository.save(transaction);
        }
    }

    public void deleteTransaction(final Long transactionId) {
        // TODO(alistair): LIVE - deleting a transaction from an account with only one transaction deletes the account
        // TODO(alistair): LOCAL - saw a sqlintegrityconstraintviolationexception when attempting to delete a transaction immediately after a failed update
        final Transaction transaction = transactionRepository.findOne(transactionId);
        if (Objects.isNull(transaction)) {
            throw new TransactionNotFoundException(HttpStatus.NOT_FOUND, transactionId);
        } else {
            transactionRepository.delete(transactionId);
        }
    }

}
