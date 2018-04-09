package com.flatmate.flatmateregistry;

import com.flatmate.flatmatepersistence.Transaction;

import java.util.List;

public interface TransactionService {

    Transaction getTransactionById(final Long transactionId);

    List<Transaction> getAllTransactions();

    Transaction createTransaction(final Transaction transaction);

    Transaction updateTransaction(final Long transactionId, final Transaction transaction);

    void deleteTransaction(final Long transactionId);

}
