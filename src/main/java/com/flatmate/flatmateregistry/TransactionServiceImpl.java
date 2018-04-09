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
public class TransactionServiceImpl implements TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionServiceImpl(final AccountRepository accountRepository, final TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public Transaction getTransactionById(final Long transactionId) { return transactionRepository.findOne(transactionId); }

    public List<Transaction> getAllTransactions() {
        final List<Transaction> transactions = new ArrayList<>();
        transactionRepository.findAll().forEach(transactions::add);
        return transactions;
    }

    public Transaction createTransaction(final Transaction transaction) {
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
        return null;
    }

    public void deleteTransaction(final Long transactionId) { transactionRepository.delete(transactionId); }

}
