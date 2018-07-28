package com.flatmate.flatmateapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flatmate.ErrorHandler;
import com.flatmate.flatmatepersistence.Account;
import com.flatmate.flatmateregistry.AccountService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class AccountControllerTest {

    private MockMvc mockMvc;
    private Account account;
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
    public void onGetAccountShouldReturnNotFoundIfInvalidId() {
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
    public void shouldNotCreateInvalidAccount() {
        final Account invalidAccount = new Account("", "test");
        underTest.createAccount(invalidAccount);
        verify(accountService, times(1)).createAccount(invalidAccount);
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
