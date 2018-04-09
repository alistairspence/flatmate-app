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
public class TransactionServiceImplTest {

    @InjectMocks
    private TransactionServiceImpl underTest;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private Account account;

    @Mock
    private Transaction transaction;

    @Test
    public void shouldGetTransaction() {
        when(transactionRepository.findOne(transaction.getId())).thenReturn(transaction);
        Assert.assertEquals(transaction, underTest.getTransactionById(transaction.getId()));
    }

    @Test
    public void shouldGetAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);
        when(transactionRepository.findAll()).thenReturn(transactions);
        Assert.assertEquals(transactions, underTest.getAllTransactions());
    }

    @Test
    public void shouldCreateTransaction() {
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
