package com.flatmate.registry;

import com.flatmate.persistence.Account;

import java.util.List;

public interface AccountService {

    Account getAccountById(final Long accountId);

    List<Account> getAllAccounts();

    Account createAccount(final Account account);

    Account updateAccount(final Long accountId, final Account account);

    void deleteAccount(final Long accountId);

}
