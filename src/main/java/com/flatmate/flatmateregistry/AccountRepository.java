package com.flatmate.flatmateregistry;


import com.flatmate.flatmatepersistence.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
}