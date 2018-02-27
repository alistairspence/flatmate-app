package com.flatmate.flatmateregistry;

import com.flatmate.flatmatepersistence.Account;
import com.flatmate.flatmatepersistence.Transaction;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class AccountServiceTest {

    @InjectMocks
    private AccountService accountService;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private Account account;

    @Mock
    private Transaction transaction;

    @Test
    public void shouldGetAccountById() {
        when(accountRepository.findOne(account.getId())).thenReturn(account);
        Assert.assertEquals(account, accountService.getAccountById(account.getId()));
    }

    @Test
    public void shouldGetAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        accounts.add(account);
        when(accountRepository.findAll()).thenReturn(accounts);
        Assert.assertEquals(accounts, accountService.getAccounts());
    }

    @Test
    public void shouldCreateValidAccount() {
        when(accountRepository.save(account)).thenReturn(account);
        Assert.assertEquals(account, accountService.createAccount(account));
    }

    // TODO(alistair): how should these be tested?

    @Test
    public void shouldNotCreateInvalidAccount() {
    }

    @Test
    public void shouldDeleteValidAccount() {
    }

    @Test
    public void shouldNotDeleteInvalidAccount() {
    }

}
