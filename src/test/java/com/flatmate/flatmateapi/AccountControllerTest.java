package com.flatmate.flatmateapi;

import com.flatmate.flatmatepersistence.Account;
import com.flatmate.flatmateregistry.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
    public void shouldGetAccountById() {
        underTest.getAccountById(account.getId());
        verify(accountService, times(1)).getAccountById(account.getId());
    }

    @Test
    public void shouldGetAllAccounts() {
        underTest.getAllAccounts();
        verify(accountService, times(1)).getAllAccounts();
    }

    @Test
    public void shouldCreateValidAccount() {
        final Account validAccount = new Account("test", "test");
        underTest.createAccount(validAccount);
        verify(accountService, times(1)).createAccount(validAccount);
    }

    @Test
    public void shouldUpdateValidAccount() {
        final Account updatedAccount = new Account("test", "update");
        underTest.updateAccount(account.getId(), updatedAccount);
        verify(accountService, times(1)).updateAccount(account.getId(), updatedAccount);
    }

    @Test
    public void shouldDeleteValidAccount() {
        underTest.deleteAccount(account.getId());
        verify(accountService, times(1)).deleteAccount(account.getId());
    }

}
