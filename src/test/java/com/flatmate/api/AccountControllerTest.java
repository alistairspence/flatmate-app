package com.flatmate.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.flatmate.exceptions.AccountNotFoundException;
import com.flatmate.errorhandling.ErrorHandler;
import com.flatmate.persistence.Account;
import com.flatmate.registry.AccountService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class AccountControllerTest {

    private MockMvc mockMvc;
    private Account account;
    private Account account2;
    private Account account3;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @InjectMocks
    private AccountController underTest;

    @Mock
    private AccountService accountService;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(underTest)
                .setControllerAdvice(new ErrorHandler())
                .build();
        account = new Account("testAccount", "testpass");
        account2 = new Account("testAccount2", "testpass");
        account3 = new Account("testAccount3", "testpass");
    }

    @Test
    public void onGetAccountShouldReturnFoundAccount() throws Exception {
        when(accountService.getAccountById(1L)).thenReturn(account);

        final MvcResult mvcResult = mockMvc.perform(get("/api/accounts/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        final Account account = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Account.class);
        assertThat(account.getUsername(), is("testAccount"));
    }

    @Test
    public void onGetAccountShouldReturnNotFoundIfInvalidId() throws Exception {
        when(accountService.getAccountById(0L))
                .thenThrow(new AccountNotFoundException(HttpStatus.NOT_FOUND, 0L));

        final MvcResult mvcResult = mockMvc.perform(get("/api/accounts/0"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();

        // TODO(alistair): why am i not testing the apierror here?

        assertThat(mvcResult.getResponse().getStatus(), is(HttpStatus.NOT_FOUND.value()));
    }

    @Test
    public void onGetAllAccountsShouldReturnFoundAccounts() throws Exception {
        when(accountService.getAllAccounts()).thenReturn(Arrays.asList(account, account2, account3));

        final MvcResult mvcResult = mockMvc.perform(get("/api/accounts"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        final List<Account> accounts = objectMapper.readValue(mvcResult.getResponse().getContentAsString(),
                TypeFactory.defaultInstance().constructCollectionType(ArrayList.class, Account.class));

        assertThat(accounts, hasSize(3));
    }

    @Test
    public void onGetAllAccountsShouldReturnEmptyListIfNoAccountsExist() throws Exception {
        when(accountService.getAllAccounts()).thenReturn(Collections.emptyList());

        final MvcResult mvcResult = mockMvc.perform(get("/api/accounts"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        final List<Account> accounts = objectMapper.readValue(mvcResult.getResponse().getContentAsString(),
                TypeFactory.defaultInstance().constructCollectionType(ArrayList.class, Account.class));

        assertThat(accounts, empty());
    }

    @Test
    public void onUpdateAccountShouldUpdateIfValidAccount() {
    }

    @Test
    public void onUpdateAccountShouldReturnNotFoundIfInvalidAccountId() {
    }

    @Test
    public void onUpdateAccountShouldReturnBadRequestIfInvalidAccountBody() {
    }

    @Test
    public void onDeleteAccountShouldDeleteIfValidAccount() {
    }

    @Test
    public void onDeleteAccountShouldReturnNotFoundIfInvalidAccountId() {
    }

}
