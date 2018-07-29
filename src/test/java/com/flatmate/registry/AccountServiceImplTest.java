package com.flatmate.registry;

import com.flatmate.persistence.Account;
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
public class AccountServiceImplTest {

    @InjectMocks
    private AccountServiceImpl underTest;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private Account account;

    @Mock
    private Account account2;

    @Test
    public void shouldGetAccountById() {
        when(accountRepository.findOne(account.getId())).thenReturn(account);
        Assert.assertEquals(account, underTest.getAccountById(account.getId()));
    }

    @Test
    public void shouldGetAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        accounts.add(account);
        when(accountRepository.findAll()).thenReturn(accounts);
        Assert.assertEquals(accounts, underTest.getAllAccounts());
    }

    @Test
    public void shouldCreateValidAccount() {
        when(accountRepository.save(account)).thenReturn(account);
        Assert.assertEquals(account, underTest.createAccount(account));
    }

    @Test
    public void shouldUpdateValidAccount() {
        when(accountRepository.findOne(account.getId())).thenReturn(account);
        when(accountRepository.save(account2)).thenReturn(account2);
        Assert.assertEquals(account2, underTest.updateAccount(account.getId(), account2));
    }

    @Test
    public void shouldDeleteValidAccount() {
    }

}
