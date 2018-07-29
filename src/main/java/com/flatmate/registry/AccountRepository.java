package com.flatmate.registry;

import com.flatmate.persistence.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
    Account findAccountByUsernameAndPassword(final String username, final String password);
}