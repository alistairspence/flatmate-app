package com.flatmate.flatmateregistry;

import com.flatmate.flatmatepersistence.Account;
import com.flatmate.flatmatepersistence.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Autowired
    public AccountServiceImpl(final AccountRepository accountRepository, final TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public Account getAccountById(final Long accountId) {
        return accountRepository.findOne(accountId);
    }

    public List<Account> getAllAccounts() {
        final List<Account> accounts = new ArrayList<>();
        accountRepository.findAll().forEach(accounts::add);
        return accounts;
    }

    public Account createAccount(final Account account) {
        return accountRepository.save(account);
    }

    public Account updateAccount(final Long accountId, final Account account) {
        return null;
    }

    public void deleteAccount(final Long accountId) {
        final Account accountToDelete = accountRepository.findOne(accountId);
        final Set<Transaction> transactions = accountToDelete.getTransactions();
        transactions.forEach(transaction ->
                transactionRepository.delete(transaction.getId()));
        accountRepository.delete(accountId);
    }

}
