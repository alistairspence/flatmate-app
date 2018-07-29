package com.flatmate.registry;

import com.flatmate.exceptions.AccountNotFoundException;
import com.flatmate.persistence.Account;
import com.flatmate.persistence.Transaction;
import com.google.common.collect.Lists;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

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
        final Account account = accountRepository.findOne(accountId);
        if (Objects.isNull(account)) {
            throw new AccountNotFoundException(HttpStatus.NOT_FOUND, accountId);
        } else {
            return account;
        }
    }

    public List<Account> getAllAccounts() {
        return Lists.newArrayList(accountRepository.findAll());
    }

    public Account createAccount(final Account account) {
        // TODO(alistair): need to fix creating two accounts with same username/pw - breaks transactions
        return accountRepository.save(account);
    }

    public Account updateAccount(final Long accountId, final Account account) {
        final Account accountToUpdate = accountRepository.findOne(accountId);
        if (Objects.isNull(accountToUpdate)) {
            throw new AccountNotFoundException(HttpStatus.NOT_FOUND, accountId);
        } else {
            account.setId(accountId);
            return accountRepository.save(account);
        }
    }

    public void deleteAccount(final Long accountId) {
        final Account account = accountRepository.findOne(accountId);
        if (Objects.isNull(account)) {
            throw new AccountNotFoundException(HttpStatus.NOT_FOUND, accountId);
        } else {
            final Set<Transaction> transactions = account.getTransactions();
            transactions.forEach(transaction ->
                    transactionRepository.delete(transaction.getId()));
            accountRepository.delete(accountId);
        }
    }

}
