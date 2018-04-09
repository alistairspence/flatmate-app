package com.flatmate.flatmateapi;

import com.flatmate.flatmatepersistence.Account;
import com.flatmate.flatmateregistry.AccountService;
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
public class AccountControllerTest {

    @InjectMocks
    private AccountController underTest;

    @Mock
    private AccountService accountService;

    @Mock
    private Account account;

    @Test
    public void shouldGetAccount() {
        when(accountService.getAccountById(account.getId())).thenReturn(account);
        Assert.assertEquals(account, underTest.getAccountById(account.getId()));
    }

    @Test
    public void shouldGetAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        when(accountService.getAllAccounts()).thenReturn(accounts);
        Assert.assertEquals(accounts, underTest.getAllAccounts());
    }

    @Test
    public void shouldCreateValidAccount() {
        Account account = new Account("test", "test");
        when(accountService.createAccount(account)).thenReturn(account);
        Assert.assertEquals(account, underTest.createAccount(account));
    }

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
