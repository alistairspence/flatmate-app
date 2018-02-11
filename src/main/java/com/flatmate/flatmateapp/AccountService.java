package com.flatmate.flatmateapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Autowired
    public AccountService(final AccountRepository accountRepository, final TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public Account getAccountById(final Long userId) {
        return accountRepository.findOne(userId);
    }

    public List<Account> getAccounts() {
        final List<Account> accounts = new ArrayList<>();
        accountRepository.findAll().forEach(accounts::add);
        return accounts;
    }

    public Account createAccount(final Account account) {
        return accountRepository.save(account);
    }

    public void deleteAccount(final Long userId) {
        accountRepository.delete(userId);
    }

}
