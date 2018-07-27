package com.flatmate.flatmateregistry;

import com.flatmate.flatmatepersistence.Account;
import com.flatmate.flatmatepersistence.Transaction;
import com.google.common.collect.Lists;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

import static com.flatmate.ErrorHandler.handleError;

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
        return Lists.newArrayList(accountRepository.findAll());
    }

    public Account createAccount(final Account account) {
        // TODO(alistair): validation?
        return accountRepository.save(account);
    }

    public Account updateAccount(final Long accountId, final Account account) {
        if (Objects.nonNull(accountRepository.findOne(accountId))) {
            account.setId(accountId);
            return accountRepository.save(account);
        } else {
            // TODO(alistair): implement some sort of error handling here
            handleError("Account not found!");
            return null;
        }
    }

    public void deleteAccount(final Long accountId) {
        final Account accountToDelete = accountRepository.findOne(accountId);
        if (Objects.nonNull(accountToDelete)) {
            final Set<Transaction> transactions = accountToDelete.getTransactions();
            transactions.forEach(transaction ->
                    transactionRepository.delete(transaction.getId()));
            accountRepository.delete(accountId);
        } else {
            handleError("Account not found!");
        }
    }

}
