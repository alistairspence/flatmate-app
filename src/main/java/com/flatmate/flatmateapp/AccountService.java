package com.flatmate.flatmateapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public Account getAccountById(final Long userId) {
        return accountRepository.findOne(userId);
    }

//    public List<Account> getAccounts() {
//        return accountRepository.findAll();
//    }

    public Account createAccount(final Account account) {
        return accountRepository.save(account);
    }

    public void deleteAccount(final Long userId) {
        accountRepository.delete(userId);
    }

}
