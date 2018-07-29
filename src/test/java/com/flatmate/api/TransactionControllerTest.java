package com.flatmate.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.flatmate.errorhandling.ErrorHandler;
import com.flatmate.exceptions.TransactionNotFoundException;
import com.flatmate.persistence.Account;
import com.flatmate.persistence.Transaction;
import com.flatmate.registry.TransactionService;
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
public class TransactionControllerTest {

    private MockMvc mockMvc;
    private Transaction transaction;
    private Transaction transaction2;
    private Transaction transaction3;
    private Account account;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @InjectMocks
    private TransactionController underTest;

    @Mock
    private TransactionService transactionService;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(underTest)
                .setControllerAdvice(new ErrorHandler())
                .build();
        account = new Account("testAccount", "testpass");
        transaction = new Transaction(account, 1);
        transaction2 = new Transaction(account, 2);
        transaction3 = new Transaction(account, 3);
    }

    @Test
    public void onGetTransactionShouldReturnFoundTransaction() throws Exception {
        when(transactionService.getTransactionById(1L)).thenReturn(transaction);

        final MvcResult mvcResult = mockMvc.perform(get("/api/transactions/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        final Transaction transaction = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Transaction.class);
        assertThat(transaction.getAmount(), is(1));
    }

    @Test
    public void onGetTransactionShouldReturnNotFoundIfInvalidId() throws Exception {
        when(transactionService.getTransactionById(0L))
                .thenThrow(new TransactionNotFoundException(HttpStatus.NOT_FOUND, 0L));

        final MvcResult mvcResult = mockMvc.perform(get("/api/transactions/0"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();

        assertThat(mvcResult.getResponse().getStatus(), is(HttpStatus.NOT_FOUND.value()));
    }

    @Test
    public void onGetAllTransactionsShouldReturnFoundTransactions() throws Exception {
        when(transactionService.getAllTransactions()).thenReturn(Arrays.asList(transaction, transaction2, transaction3));

        final MvcResult mvcResult = mockMvc.perform(get("/api/transactions"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        final List<Transaction> transactions = objectMapper.readValue(mvcResult.getResponse().getContentAsString(),
                TypeFactory.defaultInstance().constructCollectionType(ArrayList.class, Transaction.class));

        assertThat(transactions, hasSize(3));
    }

    @Test
    public void onGetAllTransactionsShouldReturnEmptyListIfNoTransactionsExist() throws Exception {
        when(transactionService.getAllTransactions()).thenReturn(Collections.emptyList());

        final MvcResult mvcResult = mockMvc.perform(get("/api/transactions"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        final List<Transaction> transactions = objectMapper.readValue(mvcResult.getResponse().getContentAsString(),
                TypeFactory.defaultInstance().constructCollectionType(ArrayList.class, Transaction.class));

        assertThat(transactions, empty());
    }

    @Test
    public void onUpdateTransactionShouldUpdateIfValidTransaction() {
    }

    @Test
    public void onUpdateTransactionShouldReturnNotFoundIfInvalidTransactionId() {
    }

    @Test
    public void onUpdateTransactionShouldReturnBadRequestIfInvalidTransactionBody() {
    }

    @Test
    public void onDeleteTransactionShouldDeleteIfValidTransaction() {
    }

    @Test
    public void onDeleteTransactionShouldReturnNotFoundIfInvalidTransactionId() {
    }

}
