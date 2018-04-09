package com.flatmate.flatmateapi;

import com.flatmate.flatmatepersistence.Account;
import com.flatmate.flatmatepersistence.Transaction;
import com.flatmate.flatmateregistry.TransactionServiceImpl;
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
public class TransactionControllerTest {

    @InjectMocks
    private TransactionController underTest;

    @Mock
    private TransactionServiceImpl transactionService;

    @Mock
    private Transaction transaction;

    @Mock
    private Account account;

    @Test
    public void shouldGetTransaction() {
        when(transactionService.getTransactionById(transaction.getId())).thenReturn(transaction);
        Assert.assertEquals(transaction, underTest.getTransactionById(transaction.getId()));
    }

    @Test
    public void shouldGetAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        when(transactionService.getAllTransactions()).thenReturn(transactions);
        Assert.assertEquals(transactions, underTest.getAllTransactions());
    }

    @Test
    public void shouldCreateValidTransaction() {
        Transaction transaction = new Transaction(account, 10);
        when(transactionService.createTransaction(transaction)).thenReturn(transaction);
        Assert.assertEquals(transaction, underTest.createTransaction(transaction));
    }

    @Test
    public void shouldNotCreateInvalidTransaction() {
    }

    @Test
    public void shouldDeleteValidTransaction() {
    }

    @Test
    public void shouldNotDeleteInvalidTransaction() {
    }


}
