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

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class AccountControllerTest {

    @InjectMocks
    private AccountController accountController;

    @Mock
    private AccountService accountService;

    @Mock
    private Account account;

    @Test
    public void shouldGetValidAccount() {
        when(accountService.getAccountById(account.getId())).thenReturn(account);
        Assert.assertEquals(account, accountController.getAccount(account.getId()));
    }

    //TODO(alistair): what exception is thrown if it's an invalid account? expect it here!
    @Test
    public void shouldNotGetInvalidAccount() {
    }

    @Test
    public void shouldGetAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        when(accountService.getAccounts()).thenReturn(accounts);
        Assert.assertEquals(accounts, accountController.getAccounts());
    }

    @Test
    public void shouldCreateValidAccount() {
        Account account = new Account("test", "test");
        when(accountService.createAccount(account)).thenReturn(account);
        Assert.assertEquals(account, accountController.createAccount(account));
    }

    //TODO(alistair): what exception is thrown if it's an invalid account? expect it here!
    @Test
    public void shouldNotCreateInvalidAccount() {
    }

    // TODO(alistair): how should this be tested?
    @Test
    public void shouldDeleteValidAccount() {
    }

    //TODO(alistair): what exception is thrown if it's an invalid account? expect it here!
    @Test
    public void shouldNotDeleteInvalidAccount() {
    }

}
