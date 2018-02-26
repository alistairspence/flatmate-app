package com.flatmate.flatmateapi;

import com.flatmate.flatmatepersistence.Account;
import com.flatmate.flatmateregistry.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AccountControllerTest {

    @Mock
    private AccountController accountController;

    @Mock
    private AccountService accountService;

    @Mock
    private Account account;

    @Test
    public void shouldGetValidAccount() {

        when(accountService.getAccountById(account.getId())).thenReturn(account);
        accountController.getAccount(account.getId());
        verify(accountService).getAccountById(account.getId());
    }

    @Test
    public void shouldNotGetInvalidAccount() {

    }

    @Test
    public void shouldGetAllAccounts() {

    }

    @Test
    public void shouldCreateValidAccount() {

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
